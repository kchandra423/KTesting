package kchandra423.kTesting.kExceptions;

import java.util.Arrays;

/**
 * The exception thrown when an assertion of whether an attribute of a class (constructor, method, field) exists.
 * Contain details about what was expected to exist, but not found.
 *
 * @author Kumar Chandra
 */
public class KExistenceException extends KException {
    /**
     * Throws an exception for a method with the given parameters not existing
     * @param functionName The name of the function
     * @param c The class being used
     * @param parameters The parameter types expected
     */
    public KExistenceException(String functionName, Class<?> c, Class<?>... parameters) {
        super(getFunctionNotFoundMessage(functionName, c, parameters));
    }

    /**
     * Throws an exception for a constructor within a class not existing
     * @param c The class being used
     * @param parameters The parameters of the constructor that could not be found
     */
    public KExistenceException(Class<?> c, Class<?>... parameters) {
        super(getConstructorNotFoundMessage(c, parameters));
    }

    /**
     * Throws ane exception for a field not being found within a class
     * @param c The class being looked at
     * @param fieldName The name of the field
     */
    public KExistenceException(Class<?> c, String fieldName) {
        super(getFieldNotFoundMessage(fieldName, c));
    }

    /**
     * Throws an exception for a class not being able to be loaded
     * @param className The fully qualified name of the class
     */
    public KExistenceException(String className) {
        super(getClassNotFoundMessage(className));
    }

    private static String getFunctionNotFoundMessage(String functionName, Class<?> c, Class<?>... parameters) {
        return "Could not find method " + functionName + " with parameters " + Arrays.toString(parameters) + " in " + c.toString();
    }

    private static String getFieldNotFoundMessage(String fieldName, Class<?> c) {
        return "Could not find field " + fieldName + " in " + c.toString();
    }

    private static String getConstructorNotFoundMessage(Class<?> c, Class<?>... parameters) {
        return "Could not find constructor with parameters " + Arrays.toString(parameters) + " in " + c.toString();
    }

    private static String getClassNotFoundMessage(String className) {
        return "Could not find class with name " + className;
    }
}
