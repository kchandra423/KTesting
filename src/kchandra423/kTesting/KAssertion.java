package kchandra423.kTesting;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class KAssertion {
    public static void kAssertTrue(String functionName, Object o, Object... input)  {
        kAssertEquals(functionName, o, true, input);
    }

    public static void kAssertFalse(String functionName, Object o, Object... input)  {
        kAssertEquals(functionName, o, false, input);
    }

    public static void kAssertEquals(String functionName, Object o, Object expected, Object... input)  {
        Object val = null;
        try {
            val = getMethod(functionName, o, input).invoke(o, input);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (!val.equals(expected)) {
            throw new KException(functionName, o, val, expected, true, input);
        } else {
            System.out.println(getSuccessMessage(functionName, o, expected, input));
        }


    }

    public static void kAssertNotEquals(String functionName, Object o, Object expected, Object... input)  {
        Object val = null;
        try {
            val = getMethod(functionName, o, input).invoke(o, input);
        } catch (IllegalAccessException | InvocationTargetException e) {
          e.printStackTrace();
        }
        if (val.equals(expected)) {
            throw new KException(functionName, o, val, expected, false, input);
        } else {
            System.out.println(getSuccessMessage(functionName, o, Boolean.TRUE, input));
        }
    }
    public static void kAssert(String functionName, Object o, Object... input){
        getMethod(functionName, o, input);
    }

    private static Method getMethod(String methodName, Object obj, Object... input)  {
        Class[] params = new Class[input.length];
        for (int i = 0; i < params.length; i++) {
            params[i] = input[i].getClass();
        }
        for (Method m :
                obj.getClass().getMethods()) {
            if (m.getName().equals(methodName) && Arrays.equals(m.getParameterTypes(), params) && m.isAccessible()) {
                return m;
            }
        }
        throw new KException(methodName, obj, input);
//        throw new NoSuchMethodException("Method " + methodName + " not found in" + obj.getClass().toString());
    }

    private static String getSuccessMessage(String methodName, Object o, Object expected, Object... input) {
        return "Called " + methodName + " on " + o.toString() + " with arguments " + Arrays.toString(input) + " and successfully got " + expected.toString();
    }
}
