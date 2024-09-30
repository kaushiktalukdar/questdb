#      ___                  _   ____  ____
#     / _ \ _   _  ___  ___| |_|  _ \| __ )
#    | | | | | | |/ _ \/ __| __| | | |  _ \
#    | |_| | |_| |  __/\__ \ |_| |_| | |_) |
#     \__\_\\__,_|\___||___/\__|____/|____/
#
#   Copyright (c) 2014-2019 Appsicle
#   Copyright (c) 2019-2024 QuestDB
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.
#

import psycopg
import re
import sys
from psycopg import Connection, Cursor
from common import *


def adjust_placeholder_syntax(query):
    # Replace $[n] with %s
    return re.sub(r'\$\[\d+\]', '%s', query)


def execute_query(cursor: Cursor, query, parameters):
    if parameters:
        cursor.execute(query, parameters)
    else:
        cursor.execute(query)
    try:
        if cursor.description:
            return cursor.fetchall()
        else:
            if cursor.rowcount == -1:
                return None
            return [(cursor.rowcount,)]
    except psycopg.errors.ProgrammingError:
        return cursor.statusmessage


def execute_steps(steps, variables, cursor: Cursor, connection: Connection):
    for step in steps:
        if 'loop' in step:
            execute_loop(step['loop'], variables, cursor, connection)
        else:
            execute_step(step, variables, cursor, connection)


def execute_loop(loop_def, variables, cursor: Cursor, connection: Connection):
    loop_var_name = loop_def['as']
    loop_variables = variables.copy()

    if 'over' in loop_def:
        iterable = loop_def['over']
    elif 'range' in loop_def:
        start = loop_def['range']['start']
        end = loop_def['range']['end']
        iterable = range(start, end + 1)
    else:
        raise ValueError("Loop must have 'over' or 'range' defined.")

    for item in iterable:
        loop_variables[loop_var_name] = item
        execute_steps(loop_def['steps'], loop_variables, cursor, connection)


def execute_step(step, variables, cursor: Cursor, connection: Connection):
    action = step['action']
    query_template = step.get('query')
    parameters = step.get('parameters', [])
    expect = step.get('expect', {})

    # Substitute variables in query
    query_with_vars = substitute_variables(query_template, variables)

    # Replace parameter placeholders in query
    query = adjust_placeholder_syntax(query_with_vars)

    resolved_parameters = extract_parameters(parameters, variables)
    result = execute_query(cursor, query, resolved_parameters)
    connection.commit()

    # Assert result
    if expect:
        assert_result(expect, result)


def run_test(test, global_variables, connection):
    variables = global_variables.copy()
    variables.update(test.get('variables', {}))

    cursor = connection.cursor()

    test_failed = False
    try:
        # Prepare phase
        prepare_steps = test.get('prepare', [])
        execute_steps(prepare_steps, variables, cursor, connection)

        # Test steps
        test_steps = test.get('steps', [])
        execute_steps(test_steps, variables, cursor, connection)

        print(f"Test '{test['name']}' passed.")

        test_failed = False

    except Exception as e:
        print(f"Test '{test['name']}' failed: {str(e)}")
        test_failed = True

    finally:
        # Teardown phase should run regardless of test outcome
        teardown_steps = test.get('teardown', [])
        try:
            execute_steps(teardown_steps, variables, cursor, connection)
        except Exception as teardown_exception:
            print(f"Teardown for test '{test['name']}' failed: {str(teardown_exception)}")
        cursor.close()
        if test_failed:
            sys.exit(1)


def main(yaml_file):
    data = load_yaml(yaml_file)
    global_variables = data.get('variables', {})
    tests = data.get('tests', [])

    for test in tests:
        iterations = test.get('iterations', 50)
        for i in range(iterations):
            connection = psycopg.connect(
                host='localhost',
                port=8812,
                user='admin',
                password='quest',
                dbname='qdb'
            )
            run_test(test, global_variables, connection)
            connection.close()


if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Usage: python runner.py <test_file.yaml>")
        sys.exit(1)
    yaml_file = sys.argv[1]
    main(yaml_file)
