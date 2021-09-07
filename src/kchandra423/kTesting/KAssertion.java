package kchandra423.kTesting;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class KAssertion {
    public static void kAssertTrue(String functionName, Object o, Object... input) {
        try {
            Object val = getMethod(functionName, o).invoke(o, input);

        if (val.equals(Boolean.FALSE)) {
                throw new KException(functionName, o, val, Boolean.TRUE, true, input);
            } else {
                System.out.println(getSuccessMessage(functionName, o, Boolean.TRUE, input));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void kAssertFalse(String functionName, Object o, Object... input) {
        try {
            Object val = getMethod(functionName, o).invoke(o, input);
            if (val.equals(Boolean.TRUE)) {
                throw new KException(functionName, o, val, Boolean.FALSE, true, input);
            } else {
                System.out.println(getSuccessMessage(functionName, o, Boolean.FALSE, input));
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void kAssertEquals(String functionName, Object o, Object expected, Object... input) {
        try {
            Object val = getMethod(functionName, o).invoke(o, input);
            if (!val.equals(expected)) {
                throw new KException(functionName, o, val, expected, true, input);
            } else {
                System.out.println(getSuccessMessage(functionName, o, expected, input));
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void kAssertNotEquals(String functionName, Object o, Object expected, Object... input) {
        try {
            Object val = getMethod(functionName, o).invoke(o, input);
            if (val.equals(expected)) {
                throw new KException(functionName, o, val, expected, false, input);
            } else {
                System.out.println(getSuccessMessage(functionName, o, Boolean.TRUE, input));
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static Method getMethod(String methodName, Object obj) throws NoSuchMethodException {
        for (Method m :
                obj.getClass().getMethods()) {
            if (m.getName().equals(methodName)) {
                return m;
            }
        }
        throw new NoSuchMethodException("Method " + methodName + " not found in" + obj.getClass().toGenericString());
    }

    private static String getSuccessMessage(String methodName, Object o, Object expected, Object... input) {
        return "Called " + methodName + " on " + o.toString() + " with arguments " + Arrays.toString(input) + " and successfully got " + expected.toString();
    }
}
