package kchandra423.kTesting.kAssertions;

import kchandra423.kTesting.kExceptions.KAssertionException;
import kchandra423.kTesting.kExceptions.KExistenceException;

/**
 * Handles basic testing. Can be used to assert the outputs of certain methods,
 * the values of certain fields,
 * or verify the existence of certain methods and constructors within a class
 * <p>
 * Note that all values and methods will be looked at regardless of whether they are private
 *
 * @author Kumar Chandra
 * @see KAssertionException
 * @see KExistenceException
 */
public class KAssert {
    private static boolean successMessages = true;

    /**
     * Specify whether you would like successful assertions to print to the console
     *
     * @param flag True for messages, false for none
     */
    public static void enableSuccessMessages(boolean flag) {
        successMessages = flag;
    }

    public static boolean successMessagesEnabled() {
        return successMessages;
    }

    /**
     * Asserts that a given object will print the specified output with the specified function name and parameters.
     * Not safe to multi-thread!!
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected value to be printed to the console (or whatever the current system output is)
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return true
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertConsoleEquals(String functionName, Object o, String expected, Object... input) {
        kAssertConsoleEquals(functionName, o, new String[]{"\n", "\t", " "}, expected, input);
    }

    /**
     * Asserts that a given object will print the specified output with the specified function name and parameters.
     * Not safe to multi-thread!!
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected value to be printed to the console (or whatever the current system output is)
     * @param input        The parameters being called on the object. *Order matters!*
     * @param trim         The set of strings that should be trimmed from the string
     * @throws KAssertionException Throws this exception if the method does not return true
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertConsoleEquals(String functionName, Object o, String[] trim, String expected, Object... input) {
        Assertion.kAssert(expected, new ConsoleAssertion(functionName, o, trim, input));
    }

    public static void kAssertConsoleEqualsAny(String functionName, String[] expected, Object o, Object... input) {
        kAssertConsoleEqualsAny(functionName, new String[]{"\n", "\t", " "}, expected, o, input);
    }

    public static void kAssertConsoleEqualsAny(String functionName, String[] trim, String[] expected, Object o, Object... input) {
        Assertion.kAssertAny(expected, new ConsoleAssertion(functionName, o, trim, input));
    }


    /**
     * Asserts that a given object will return true with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return true
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertTrue(String functionName, Object o, Object... input) {
        kAssertEquals(functionName, o, true, input);
    }

    /**
     * Asserts that a given object will return false with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return false
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertFalse(String functionName, Object o, Object... input) {
        kAssertEquals(functionName, o, false, input);
    }

    /**
     * Asserts that a given object will return the expected output with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected value to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertEquals(String functionName, Object o, Object expected, Object... input) {
        Assertion.kAssert(expected, new MethodAssertion(functionName, o, input));
    }

    /**
     * Asserts that a given object will return the any of the expected values with the specified function name and parameters.
     *
     * @param functionName    The name of the method being called on the object. *Case-sensitive!*
     * @param o               The object being used
     * @param expectedOutputs The values expected to be returned
     * @param input           The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return a value in the expected outputs
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertEqualsAny(String functionName, Object o, Object[] expectedOutputs, Object... input) {
        Assertion.kAssertAny(expectedOutputs, new MethodAssertion(functionName, o, input));
    }

    /**
     * Asserts that a given object will have a field with the expected value
     *
     * @param fieldName The name of the field to be looked at
     * @param o         The object being used
     * @param expected  The expected value of the field
     * @throws KAssertionException Throws this exception if the field does not have the expected value
     * @throws KExistenceException Throws this exception if the field is not found
     */
    public static void kAssertFieldEquals(String fieldName, Object o, Object expected) {
        Assertion.kAssert(expected, new FieldAssertion(fieldName, o));
    }

    /**
     * Asserts that a given object will have a field with the expected value
     *
     * @param fieldName The name of the field to be looked at
     * @param o         The object being used
     * @param expected  The expected value of the field
     * @throws KAssertionException Throws this exception if the field does not have the expected value
     * @throws KExistenceException Throws this exception if the field is not found
     */
    public static void kAssertFieldEqualsAny(String fieldName, Object o, Object[] expected) {
        Assertion.kAssertAny(expected, new FieldAssertion(fieldName, o));
    }


}
