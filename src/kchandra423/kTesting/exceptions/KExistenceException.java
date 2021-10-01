package kchandra423.kTesting.exceptions;


import java.util.Arrays;

public class KExistenceException extends KException {

    public KExistenceException(String functionName, Object o, Class... parameters) {
        super(getFunctionNotFoundMessage(functionName, o.getClass(), parameters));
    }

    public KExistenceException(Class c, Class... parameters) {
        super(getConstructorNotFoundMessage(c, parameters));
    }

    public KExistenceException(Class c, String fieldName) {
        super(getFieldNotFoundMessage(fieldName, c));
    }

    public KExistenceException(String className) {
        super(getClassNotFoundMessage(className));
    }

    private static String getFunctionNotFoundMessage(String functionName, Class c, Class... parameters) {
        return "Could not find method " + functionName + " with parameters " + Arrays.toString(parameters) + " in " + c.toString();
    }

    private static String getFieldNotFoundMessage(String fieldName, Class c) {
        return "Could not find field " + fieldName + " in " + c.toString();
    }

    private static String getConstructorNotFoundMessage(Class c, Class... parameters) {
        return "Could not find constructor with parameters " + Arrays.toString(parameters) + " in " + c.toString();
    }

    private static String getClassNotFoundMessage(String className) {
        return "Could not find class with name " + className;
    }
}
