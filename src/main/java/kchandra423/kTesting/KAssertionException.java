package kchandra423.kTesting;

import java.util.Arrays;

/**
 * The exception thrown when any assertion fails. Will also give information about what the expected and given values were,
 * as well as the name of the method called if applicable.
 *
 * @author Kumar Chandra
 */
public class KAssertionException extends KException {
    KAssertionException(String functionName, Object o, Object output, Object expected, boolean equal, Object[] input) {
        super(equal ? getMessage(functionName, o, output, expected, input) : getUnequalMessage(functionName, o, expected, input));
    }

    KAssertionException(String functionName, Object o, Object output, Object[] expected, Object[] input) {
        super(getEqualsAnyMessage(functionName, o, output, expected, input));
    }

    KAssertionException(String fieldName, Object o, Object value, Object expected) {
        super(getFieldEqualsMessage(fieldName, o, value, expected));
    }

    private static String getMessage(String functionName, Object o, Object output, Object expected, Object... input) {
        return "Called function " + functionName + " on " + KUtils.toString(o) + " with argument(s) " + Arrays.toString(input) + " and expected " +
                KUtils.toString(expected) + " but got " + KUtils.toString(output);
    }

    private static String getUnequalMessage(String functionName, Object o, Object output, Object expected, Object... input) {
        return "Called function " + functionName + " on " + KUtils.toString(o) + " with argument(s) " + Arrays.toString(input) + " and expected to NOT get " +
                KUtils.toString(expected) + " but got " + KUtils.toString(output);
    }

    private static String getEqualsAnyMessage(String functionName, Object o, Object output, Object[] expected, Object[] input) {
        return "Called function " + functionName + " on " + KUtils.toString(o) + " with argument(s) " + Arrays.toString(input) + " and expected to get any value within " +
                KUtils.toString(expected) + " but got " + KUtils.toString(output);
    }

    private static String getFieldEqualsMessage(String fieldName, Object o, Object value, Object expected) {
        return "Found field " + fieldName + " in " + KUtils.toString(o) + " with value " + KUtils.toString(value) + " but expected" + KUtils.toString(expected);
    }


}
