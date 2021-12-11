package kchandra423.kTesting.kExceptions;

import kchandra423.kTesting.KUtils;

import java.util.Arrays;

/**
 * The exception thrown when any assertion fails. Will also give information about what the expected and given values were,
 * as well as the name of the method called if applicable.
 *
 * @author Kumar Chandra
 */
public class KAssertionException extends KException {
    /**
     * Throws an exception for a function not returning the correct output.
     * @param functionName The function that failed
     * @param o The object used
     * @param output The output of the function
     * @param expected The expected output
     * @param input The inputs given
     */
    public KAssertionException(String functionName, Object o, Object output, Object expected, Object[] input) {
        super(getMessage(functionName, o, output, expected, input));
    }
    /**
     * Throws an exception for a function not returning the one of the correct outputs.
     * @param functionName The function that failed
     * @param o The object used
     * @param output All of the possible outputs
     * @param expected The expected output
     * @param input The inputs given
     */
    public KAssertionException(String functionName, Object o, Object output, Object[] expected, Object[] input) {
        super(getEqualsAnyMessage(functionName, o, output, expected, input));
    }

    /**
     * Throws an exception for a field not holding the correct value.
     * @param fieldName The field being looked at
     * @param o The object used
     * @param value The actual value
     * @param expected The expected value
     */
    public KAssertionException(String fieldName, Object o, Object value, Object expected) {
        super(getFieldEqualsMessage(fieldName, o, value, expected));
    }


    private static String getMessage(String functionName, Object o, Object output, Object expected, Object[] input) {
        return "Called function/field " + functionName + " on " + KUtils.toString(o) + " with argument(s) " + Arrays.toString(input) + " and expected " +
                KUtils.toString(expected) + " but got " + KUtils.toString(output);
    }

    private static String getEqualsAnyMessage(String functionName, Object o, Object output, Object[] expected, Object[] input) {
        return "Called function " + functionName + " on " + KUtils.toString(o) + " with argument(s) " + Arrays.toString(input) + " and expected to get any value within " +
                KUtils.toString(expected) + " but got " + KUtils.toString(output);
    }

    private static String getFieldEqualsMessage(String fieldName, Object o, Object value, Object expected) {
        return "Found field " + fieldName + " in " + o.toString() + " with value " + KUtils.toString(value) + " but expected" + KUtils.toString(expected);
    }


}
