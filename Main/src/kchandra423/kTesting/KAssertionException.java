package kchandra423.kTesting;


import java.util.Arrays;


class KAssertionException extends KException {
    KAssertionException(String functionName, Object o, Object output, Object expected, boolean equal, Object[] input) {
        super(equal ? getMessage(functionName, o, output, expected, input) : getUnequalMessage(functionName, o, output, input));
    }

    KAssertionException(String functionName, Object o, Object output, Object[] expected, Object[] input) {
        super(getEqualsAnyMessage(functionName, o, output, expected, input));
    }

    KAssertionException(String fieldName, Object o, Object value, Object expected) {
        super(getFieldEqualsMessage(fieldName, o, value, expected));
    }

    private static String getUnequalMessage(String functionName, Object o, Object output, Object[] input) {
        return "Called function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected to NOT get " +
                output.toString() + " but got " + output;
    }

    private static String getMessage(String functionName, Object o, Object output, Object expected, Object[] input) {
        return "Called function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected " +
                expected.toString() + " but got " + output.toString();
    }

    private static String getEqualsAnyMessage(String functionName, Object o, Object output, Object[] expected, Object[] input) {
        return "Called function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected to get any value within " +
                Arrays.toString(expected) + " but got " + output.toString();
    }

    private static String getFieldEqualsMessage(String fieldName, Object o, Object value, Object expected) {
        return "Found field " + fieldName + " in " + o.toString() + " with value " + value.toString() + " but expected" + expected.toString();
    }
}
