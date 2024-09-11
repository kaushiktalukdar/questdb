/*******************************************************************************
 *     ___                  _   ____  ____
 *    / _ \ _   _  ___  ___| |_|  _ \| __ )
 *   | | | | | | |/ _ \/ __| __| | | |  _ \
 *   | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *    \__\_\\__,_|\___||___/\__|____/|____/
 *
 *  Copyright (c) 2014-2019 Appsicle
 *  Copyright (c) 2019-2024 QuestDB
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 ******************************************************************************/

package io.questdb.test.cairo.mv;

import io.questdb.cairo.TableToken;
import io.questdb.cairo.mv.MaterializedViewDefinition;
import io.questdb.cairo.mv.MaterializedViewRefreshJob;
import io.questdb.std.datetime.microtime.Timestamps;
import io.questdb.test.AbstractCairoTest;
import org.junit.Test;

public class MaterializedViewTest extends AbstractCairoTest {
    @Test
    public void testViewManualRefresh() throws Exception {
        assertMemoryLeak(() -> {
            ddl("create table base_price (" +
                    "sym varchar, price double, ts timestamp" +
                    ") timestamp(ts) partition by DAY WAL"
            );

            ddl("create table price_1h (" +
                    "sym varchar, price double, ts timestamp" +
                    ") timestamp(ts) partition by DAY WAL dedup upsert keys(ts, sym)"
            );

            TableToken baseToken = engine.verifyTableName("base_price");
            MaterializedViewDefinition viewDefinition = new MaterializedViewDefinition();

            viewDefinition.setParentTableName(baseToken.getTableName());
            viewDefinition.setViewSql("insert into price_1h " +
                    "select sym, last(price), ts from base_price " +
                    "where ts >= :from and ts <= :to " +
                    "sample by 1h");
            viewDefinition.setSampleByPeriodMicros(Timestamps.toMicros(0, 1, 1, 1, 0));
            viewDefinition.setTableToken(engine.verifyTableName("price_1h"));

            engine.getMaterializedViewGraph().upsertView(baseToken, viewDefinition);
            insert("insert into base_price values('gbpusd', 1.320, '2024-09-10T12:01')" +
                    ",('gbpusd', 1.323, '2024-09-10T12:02')" +
                    ",('jpyusd', 103.21, '2024-09-10T12:02')" +
                    ",('gbpusd', 1.321, '2024-09-10T13:02')"
            );
            drainWalQueue();

            MaterializedViewRefreshJob refreshJob = new MaterializedViewRefreshJob(engine);
            refreshJob.run(0);

            assertSql("sym\tprice\tts\n" +
                            "gbpusd\t1.323\t2024-09-10T12:00:00.000000Z\n" +
                            "jpyusd\t103.21\t2024-09-10T12:00:00.000000Z\n" +
                            "gbpusd\t1.321\t2024-09-10T13:00:00.000000Z\n",
                    "price_1h order by ts, sym"
            );

            insert("insert into base_price values('gbpusd', 1.319, '2024-09-10T12:05')" +
                    ",('gbpusd', 1.325, '2024-09-10T13:03')"
            );
            drainWalQueue();

            refreshJob.run(0);
            String expected = "sym\tprice\tts\n" +
                    "gbpusd\t1.319\t2024-09-10T12:00:00.000000Z\n" +
                    "jpyusd\t103.21\t2024-09-10T12:00:00.000000Z\n" +
                    "gbpusd\t1.325\t2024-09-10T13:00:00.000000Z\n";

            assertSql(expected, "select sym, last(price) as price, ts from base_price sample by 1h order by ts, sym");
            assertSql(expected, "price_1h order by ts, sym");
        });
    }
}
