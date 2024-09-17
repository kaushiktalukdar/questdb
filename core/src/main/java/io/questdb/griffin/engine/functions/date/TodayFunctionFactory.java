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

package io.questdb.griffin.engine.functions.date;

import io.questdb.cairo.CairoConfiguration;
import io.questdb.cairo.sql.Function;
import io.questdb.cairo.sql.Record;
import io.questdb.cairo.sql.SymbolTableSource;
import io.questdb.griffin.FunctionFactory;
import io.questdb.griffin.PlanSink;
import io.questdb.griffin.SqlExecutionContext;
import io.questdb.griffin.engine.functions.IntervalFunction;
import io.questdb.griffin.engine.functions.constants.TimestampConstant;
import io.questdb.std.IntList;
import io.questdb.std.Interval;
import io.questdb.std.ObjList;
import io.questdb.std.datetime.microtime.Timestamps;

public class TodayFunctionFactory implements FunctionFactory {
    private static final String SIGNATURE = "today()";

    @Override
    public String getSignature() {
        return SIGNATURE;
    }

    @Override
    public Function newInstance(int position, ObjList<Function> args, IntList argPositions, CairoConfiguration configuration, SqlExecutionContext sqlExecutionContext) {
        return new TodayFunction();
    }

    private static class TodayFunction extends IntervalFunction implements Function {
        private final Interval interval = new Interval();
        private SqlExecutionContext context;

        @Override
        public Interval getInterval(Record rec) {
            long today = Timestamps.floorDD(context.getNow());
            long tomorrow = Timestamps.floorDD(Timestamps.addDays(context.getNow(), 1));
            interval.of(
                    today,
                    tomorrow - 1
            );
            return interval;
        }
//        {"query":"select today()","columns":[{"name":"today","type":"unknown"}],"timestamp":-1,"dataset":[[('2024-09-17T00:00:00.000Z', '2024-09-17T23:59:59.999Z')]],"count":1}

        @Override
        public Function getLeft() {
            return new TimestampConstant(interval.getLo());
        }

        @Override
        public Function getRight() {
            return new TimestampConstant(interval.getHi());
        }

        @Override
        public void init(SymbolTableSource symbolTableSource, SqlExecutionContext executionContext) {
            executionContext.initNow();
            context = executionContext;
        }

        @Override
        public boolean isReadThreadSafe() {
            return true;
        }

        @Override
        public boolean isRuntimeConstant() {
            return true;
        }

        @Override
        public void toPlan(PlanSink sink) {
            sink.val(SIGNATURE);
        }
    }
}