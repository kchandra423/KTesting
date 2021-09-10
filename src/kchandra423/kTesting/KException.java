package kchandra423.kTesting;

import java.util.Arrays;

public class KException extends RuntimeException {
    public KException(String functionName, Object o, Object output, Object expected, boolean equal, Object[] input) {
        super(equal ? getMessage(functionName, o, output, expected, input) : getUnequalMessage(functionName, o, output, input));
    }

    public KException(String functionName, Object o, Class... parameters) {
        super(getFunctionNotFoundMessage(functionName, o, parameters));
    }

    public KException(Class base, Class... params) {
        super(getConstructorNotFoundMessage(base, params));
    }

    public KException(String className) {
        super(getClassNotFoundMessage(className));
    }


    private static String getUnequalMessage(String functionName, Object o, Object output, Object[] input) {
        return "\n\n\n***************\nYour code failed! :(" +
                "\nCalled function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected to NOT get " +
                output.toString() + " but got " + output + "\n***************\n\n\n";

    }

    private static String getFunctionNotFoundMessage(String functionName, Object o, Class... parameters) {
        return "\n\n\n***************\nCould not find method " + functionName + " with parameters " + Arrays.toString(parameters) + " in " + o.getClass().toString() + "\n***************\n\n\n";
    }

    private static String getConstructorNotFoundMessage(Class c, Class... parameters) {
        return "\n\n\n***************\nCould not find constructor with parameters " + Arrays.toString(parameters) + " in " + c.toString() + "\n***************\n\n\n";
    }

    private static String getClassNotFoundMessage(String className) {
        return "\n\n\n***************\nCould not find class with name " + className + "\n***************\n\n\n";
    }

    private static String getMessage(String functionName, Object o, Object output, Object expected, Object[] input) {
        return "\n\n\n***************\nYour code failed! :(" +
                "\nCalled function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected " +
                expected.toString() + " but got " + output.toString() + "\n***************\n\n\n";
    }

}
