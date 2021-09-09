package kchandra423.kTesting;

import java.util.Arrays;

public class KException extends RuntimeException {
    public KException(String functionName, Object o, Object output, Object expected, boolean equal, Object[] input) {
        super(equal ? getMessage(functionName, o, output, expected, input) : getUnequalMessage(functionName, o, output, input));
    }

    public KException(String functionName, Object o, Object... parameters) {
        super(getFunctionNotFoundMessage(functionName, o, parameters));
    }

    public KException(String message) {
        super(message);
    }

    private static String getUnequalMessage(String functionName, Object o, Object output, Object[] input) {
        return "\n\n\n***************\nYour code failed! :(" +
                "\nCalled function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected to NOT get " +
                output.toString() + " but got " + output + "\n***************\n\n\n";

    }

    private static String getFunctionNotFoundMessage(String functionName, Object o, Object... parameters) {
        Class[] params = new Class[parameters.length];
        for (int i = 0; i < params.length; i++) {
            params[i] = parameters[i].getClass();
        }
        return "Could not find method/constructor " + functionName + " with parameters " + Arrays.toString(params) + " in " + o.getClass().toString();
    }

    private static String getMessage(String functionName, Object o, Object output, Object expected, Object[] input) {
        return "\n\n\n***************\nYour code failed! :(" +
                "\nCalled function " + functionName + " on " + o.toString() + " with argument(s) " + Arrays.toString(input) + " and expected " +
                expected.toString() + " but got " + output.toString() + "\n***************\n\n\n";
    }

}
