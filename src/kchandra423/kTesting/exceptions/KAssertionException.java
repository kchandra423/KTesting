package kchandra423.kTesting.exceptions;


import java.util.Arrays;
//why is my head detached
public class KAssertionException extends KException {
    public KAssertionException(String functionName, Object o, Object output, Object expected, boolean equal, Object[] input) {
        super(equal ? getMessage(functionName, o, output, expected, input) : getUnequalMessage(functionName, o, output, input));
    }

    public KAssertionException(String functionName, Object o, Object output, Object[] expected, Object[] input) {
        super(getEqualsAnyMessage(functionName, o, output, expected, input));
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
}
