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

    private static String toString(Object o) {
        if (o.getClass().isArray()) {
            if (o instanceof int[]) {
                return Arrays.toString((int[]) o);
            } else if (o instanceof float[]) {
                return Arrays.toString((float[]) o);
            } else if (o instanceof double[]) {
                return Arrays.toString((double[]) o);
            } else if (o instanceof long[]) {
                return Arrays.toString((long[]) o);
            } else if (o instanceof short[]) {
                return Arrays.toString((short[]) o);
            } else if (o instanceof boolean[]) {
                return Arrays.toString((boolean[]) o);
            } else if (o instanceof char[]) {
                return Arrays.toString((char[]) o);
            } else if (o instanceof byte[]){
                return Arrays.toString((byte[]) o);
            }else {
                return Arrays.toString((Object[]) o);
            }
        } else {
            return o.toString();
        }

    }

    private static String getMessage(String functionName, Object o, Object output, Object expected, Object... input) {
        return "Called function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected " +
                toString(expected) + " but got " + toString(output);
    }

    private static String getUnequalMessage(String functionName, Object o, Object output, Object expected, Object... input) {
        return "Called function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected to NOT get " +
                toString(expected) + " but got " + toString(output);
    }

    private static String getEqualsAnyMessage(String functionName, Object o, Object output, Object[] expected, Object[] input) {
        return "Called function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected to get any value within " +
                toString(expected) + " but got " + toString(output);
    }

    private static String getFieldEqualsMessage(String fieldName, Object o, Object value, Object expected) {
        return "Found field " + fieldName + " in " + o.toString() + " with value " + toString(value) + " but expected" + toString(expected);
    }


}
