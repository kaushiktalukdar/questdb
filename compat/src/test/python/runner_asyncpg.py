import asyncio
import asyncpg
import datetime
import re
import sys
import yaml
from asyncpg import Connection
from string import Template


def load_yaml(file_path):
    with open(file_path, 'r') as file:
        return yaml.safe_load(file)


def substitute_variables(text, variables):
    if text is None:
        return None
    template = Template(str(text))
    return template.safe_substitute(variables)


def adjust_placeholder_syntax(query):
    # Replace $[n] with $n for asyncpg
    # Matches $[1], $[2], etc.
    return re.sub(r'\$\[(\d+)\]', r'$\1', query)


def resolve_parameters(typed_parameters, variables):
    resolved_parameters = []
    for typed_param in typed_parameters:
        type_ = typed_param.get('type').lower()
        value = typed_param.get('value')

        if isinstance(value, str):
            resolved_str_value = substitute_variables(value, variables)
            convert_and_append_parameters(resolved_str_value, type_, resolved_parameters)
        else:
            convert_and_append_parameters(value, type_, resolved_parameters)
    return resolved_parameters


def convert_and_append_parameters(value, type, resolved_parameters):
    if type == 'int4' or type == 'int8':
        resolved_parameters.append(int(value))
    elif type == 'float4' or type == 'float8':
        resolved_parameters.append(float(value))
    elif type == 'boolean':
        resolved_parameters.append(bool(value))
    elif type == 'varchar':
        resolved_parameters.append(str(value))
    elif type == 'timestamp':
        parsed_value = datetime.datetime.strptime(value, '%Y-%m-%dT%H:%M:%S.%fZ')
        resolved_parameters.append(parsed_value)
    else:
        resolved_parameters.append(value)


async def execute_query(connection: Connection, query, parameters):
    query_type = query.strip().split()[0].lower()
    if query_type == 'select':
        return await connection.fetch(query, *parameters)

    status = await connection.execute(query, *parameters)
    # parse status string to update count (if any) as a result
    status_parts = status.split()
    if status_parts[0] == 'INSERT' or status_parts[0] == 'UPDATE':
        row_count = int(status_parts[-1])
        return [{'count': row_count}]

    return None


def assert_result(expect, actual):
    if 'result' in expect:
        expected_result = expect['result']
        if isinstance(expected_result, list):
            if isinstance(actual, str):
                # If actual is a status string, cannot compare to expected list
                raise AssertionError(f"Expected result {expected_result}, got status '{actual}'")
            actual_converted = [list(record.values()) for record in actual]
            # Convert timestamps to strings for comparison, format: '2021-09-01T12:34:56.123456Z'
            for row in actual_converted:
                for i, value in enumerate(row):
                    if isinstance(value, datetime.datetime):
                        row[i] = value.strftime('%Y-%m-%dT%H:%M:%S.%fZ')

            assert actual_converted == expected_result, f"Expected result {expected_result}, got {actual_converted}"
        else:
            # For non-list expected results, compare as strings
            assert str(actual) == str(expected_result), f"Expected result '{expected_result}', got '{actual}'"
    elif 'result_contains' in expect:
        if isinstance(actual, str):
            # If actual is a status string, cannot compare to expected results
            raise AssertionError(f"Expected result containing {expect['result_contains']}, got status '{actual}'")
        actual_converted = [list(record.values()) for record in actual]
        # Convert timestamps to strings for comparison, format: '2021-09-01T12:34:56.123456Z'
        for row in actual_converted:
            for i, value in enumerate(row):
                if isinstance(value, datetime.datetime):
                    row[i] = value.strftime('%Y-%m-%dT%H:%M:%S.%fZ')
        for expected_row in expect['result_contains']:
            assert expected_row in actual_converted, f"Expected row {expected_row} not found in actual results."


async def execute_steps(steps, variables, connection):
    for step in steps:
        if 'loop' in step:
            await execute_loop(step['loop'], variables, connection)
        else:
            await execute_step(step, variables, connection)


async def execute_loop(loop_def, variables, connection):
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
        await execute_steps(loop_def['steps'], loop_variables, connection)


async def execute_step(step, variables, connection):
    action = step.get('action')
    query_template = step.get('query')
    types_parameters = step.get('parameters', [])
    expect = step.get('expect', {})

    with_vars_substituted = substitute_variables(query_template, variables)
    query = adjust_placeholder_syntax(with_vars_substituted)

    resolved_parameters = resolve_parameters(types_parameters, variables)
    result = await execute_query(connection, query, resolved_parameters)

    # Assert result
    if expect:
        assert_result(expect, result)


async def run_test(test, global_variables):
    variables = global_variables.copy()
    variables.update(test.get('variables', {}))

    connection = await asyncpg.connect(
        host='localhost',
        port=8812,
        user='admin',
        password='quest',
        database='qdb'
    )

    test_failed = False
    try:
        # Prepare phase
        prepare_steps = test.get('prepare', [])
        await execute_steps(prepare_steps, variables, connection)

        # Test steps
        test_steps = test.get('steps', [])
        await execute_steps(test_steps, variables, connection)

        print(f"Test '{test['name']}' passed.")

        test_failed = False

    except Exception as e:
        print(f"Test '{test['name']}' failed: {str(e)}")
        test_failed = True

    finally:
        # Teardown phase should run regardless of test outcome
        teardown_steps = test.get('teardown', [])
        try:
            await execute_steps(teardown_steps, variables, connection)
        except Exception as teardown_exception:
            print(f"Teardown for test '{test['name']}' failed: {str(teardown_exception)}")
        await connection.close()
        if test_failed:
            sys.exit(1)


async def main(yaml_file):
    data = load_yaml(yaml_file)
    global_variables = data.get('variables', {})
    tests = data.get('tests', [])

    for test in tests:
        iterations = test.get('iterations', 50)
        for _ in range(iterations):
            await run_test(test, global_variables)


if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Usage: python runner.py <test_file.yaml>")
        sys.exit(1)
    yaml_file = sys.argv[1]
    asyncio.run(main(yaml_file))
