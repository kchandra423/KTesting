package kchandra423.kTesting;


import kchandra423.kTesting.exceptions.KAssertionException;
import kchandra423.kTesting.exceptions.KExistenceException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Handles basic testing. Can be used to assert the outputs of certain methods,
 * or verify the existence of certain methods and objects within a class
 * @author Kumar Chandra
 * @see KAssertionException
 * @see KExistenceException
 */
public class KAssertion {
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
        Object val = getValue(functionName, o, input);
        if (!val.equals(expected)) {
            throw new KAssertionException(functionName, o, val, expected, true, input);
        } else {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
    }

    /**
     * Asserts that a given object will *NOT* return the expected output with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The value not expected to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method returns the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertNotEquals(String functionName, Object o, Object expected, Object... input) {
        Object val = getValue(functionName, o, input);
        if (val.equals(expected)) {
            throw new KAssertionException(functionName, o, val, expected, false, input);
        } else {
            System.out.println(getAssertionSuccessMessage(functionName, o, Boolean.TRUE, input));
        }
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
        Object output = getValue(functionName, o, input);
        for (Object expected :
                expectedOutputs) {
            if (output.equals(expected)) {
                System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
                return;
            }
        }
        throw new KAssertionException(functionName, o, output, expectedOutputs, input);
    }


    @Deprecated
    public static void kAssert(String functionName, Object o, Object... input) {
        getMethod(functionName, o, input);
    }

    /**
     * Asserts that a method with the given parameters exists within a class
     *
     * @param functionName The name of the function
     * @param c            The Class being looked at
     * @param input        All parameters being given to the method
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertMethodExists(String functionName, Class c, Class... input) {
        findMethod(functionName, c, input);
        System.out.println(getMethodExistenceSuccessMessage(functionName, c, input));
    }

    /**
     * Asserts that a constructor with the given parameters exists within a class
     *
     * @param c     The Class being looked at
     * @param input All parameters being given to the constructor
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertConstructorExists(Class c, Class... input) {
        findConstructor(c, input);
        System.out.println(getConstructorExistenceSuccessMessage(c, input));
    }

    private static void findConstructor(Class c, Class... input) {
        try {
            c.getConstructor(input);
        } catch (NoSuchMethodException e) {
            throw new KExistenceException(c, input);
        }
    }

    private static Object getValue(String functionName, Object o, Object... input) {
        try {
            return getMethod(functionName, o, input).invoke(o, input);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void findMethod(String functionName, Class c, Class... input) {
        try {
            c.getMethod(functionName, input);
        } catch (NoSuchMethodException e) {
            throw new KExistenceException(functionName, c, input);
        }
    }

    private static Method getMethod(String methodName, Object obj, Object... input) {
        Class[] params = toClassArray(input);
        Method[] methods = obj.getClass().getMethods();
        for (Method m :
                methods) {
            boolean namesMatch = m.getName().equals(methodName);
            Class[] wrapped = m.getParameterTypes();
            convertToWrappers(wrapped);
            boolean paramsMatch = isAcceptableParameters(wrapped, params);
            if (namesMatch && paramsMatch) {
                return m;
            }
        }
        throw new KExistenceException(methodName, obj, params);
    }

    private static void convertToWrappers(Class[] params) {
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals(boolean.class)) {
                params[i] = Boolean.class;
            } else if (params[i].equals(char.class)) {
                params[i] = Character.class;
            } else if (params[i].equals(byte.class)) {
                params[i] = Byte.class;
            } else if (params[i].equals(short.class)) {
                params[i] = Short.class;
            } else if (params[i].equals(int.class)) {
                params[i] = Integer.class;
            } else if (params[i].equals(long.class)) {
                params[i] = Long.class;
            } else if (params[i].equals(float.class)) {
                params[i] = Float.class;
            } else if (params[i].equals(double.class)) {
                params[i] = Double.class;
            }
        }
    }

    private static Class[] toClassArray(Object[] params) {
        Class[] classes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            classes[i] = params[i].getClass();
        }
        return classes;
    }

    private static boolean isAcceptableParameters(Class[] arguments, Class[] input) {
        if (arguments.length != input.length) {
            return false;
        }
        for (int i = 0; i < arguments.length; i++) {
            if (!arguments[i].isAssignableFrom(input[i])) {
                return false;
            }
        }
        return true;
    }

    private static String getAssertionSuccessMessage(String methodName, Object o, Object expected, Object... input) {
        return "Called " + methodName + " on " + o.toString() + " with arguments " + Arrays.toString(input) + " and successfully got " + expected.toString();
    }

    private static String getMethodExistenceSuccessMessage(String functionName, Class c, Class... parameters) {
        return "Successfully found function " + functionName + " in class " + c.toString() + " with parameters " + Arrays.toString(parameters);
    }

    private static String getConstructorExistenceSuccessMessage(Class c, Class... parameters) {
        return "Successfully found constructor with parameters " + Arrays.toString(parameters) + " in class " + c.toString();
    }
}
