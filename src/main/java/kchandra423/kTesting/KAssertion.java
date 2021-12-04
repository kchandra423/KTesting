package kchandra423.kTesting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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
public class KAssertion {
    private static boolean successMessages = true;

    /**
     * Specify whether you would like successful assertions to print to the console
     *
     * @param flag True for messages, false for none
     */
    public static void enableSuccessMessages(boolean flag) {
        successMessages = flag;
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
        StringBuilder sb = new StringBuilder();
        File output = new File("KTestingOutput.txt");
        PrintStream og = System.out;
        try {
            System.setOut(new PrintStream(output));
        } catch (FileNotFoundException e) {
            throw new KAssertionException(functionName, o, output.toString(), expected, true, input);
        }
        getValue(functionName, o, input);
        Scanner s = null;
        try {
            s = new Scanner(output);
        } catch (FileNotFoundException e) {
            throw new KAssertionException(functionName, o, output.toString(), expected, true, input);
        }
        while (s.hasNextLine()) {
            sb.append(s.nextLine());
            sb.append('\n');
        }
        String result = sb.toString();
        System.setOut(og);
        s.close();
        output.delete();
        if (!result.equals(expected)) {
            throw new KAssertionException(functionName, o, result, expected, true, input);
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
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
     * @param expected     The expected values to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertArrayEquals(String functionName, Object o, int[] expected, Object... input) {
        Object val = getValue(functionName, o, input);
        if (!(val instanceof int[]) || !Arrays.equals(expected, (int[]) val)) {
            throw new KAssertionException(functionName, o, val, expected, true, input);
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
    }

    /**
     * Asserts that a given object will return the expected output with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected values to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertArrayEquals(String functionName, Object o, double[] expected, Object... input) {
        Object val = getValue(functionName, o, input);
        if (!(val instanceof double[]) || !Arrays.equals(expected, (double[]) val)) {
            throw new KAssertionException(functionName, o, val, expected, true, input);
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
    }

    /**
     * Asserts that a given object will return the expected output with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected values to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertArrayEquals(String functionName, Object o, float[] expected, Object... input) {
        Object val = getValue(functionName, o, input);
        if (!(val instanceof float[]) || !Arrays.equals(expected, (float[]) val)) {
            throw new KAssertionException(functionName, o, val, expected, true, input);
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
    }


    /**
     * Asserts that a given object will return the expected output with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected values to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertArrayEquals(String functionName, Object o, boolean[] expected, Object... input) {
        Object val = getValue(functionName, o, input);
        if (!(val instanceof boolean[]) || !Arrays.equals(expected, (boolean[]) val)) {
            throw new KAssertionException(functionName, o, val, expected, true, input);
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
    }

    /**
     * Asserts that a given object will return the expected output with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected values to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertArrayEquals(String functionName, Object o, char[] expected, Object... input) {
        Object val = getValue(functionName, o, input);
        if (!(val instanceof char[]) || !Arrays.equals(expected, (char[]) val)) {
            throw new KAssertionException(functionName, o, val, expected, true, input);
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
    }

    /**
     * Asserts that a given object will return the expected output with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected values to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertArrayEquals(String functionName, Object o, byte[] expected, Object... input) {
        Object val = getValue(functionName, o, input);
        if (!(val instanceof byte[]) || !Arrays.equals(expected, (byte[]) val)) {
            throw new KAssertionException(functionName, o, val, expected, true, input);
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
    }

    /**
     * Asserts that a given object will return the expected output with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected values to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertArrayEquals(String functionName, Object o, short[] expected, Object... input) {
        Object val = getValue(functionName, o, input);
        if (!(val instanceof short[]) || !Arrays.equals(expected, (short[]) val)) {
            throw new KAssertionException(functionName, o, val, expected, true, input);
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
    }

    /**
     * Asserts that a given object will return the expected output with the specified function name and parameters.
     *
     * @param functionName The name of the method being called on the object. *Case-sensitive!*
     * @param o            The object being used
     * @param expected     The expected values to be returned
     * @param input        The parameters being called on the object. *Order matters!*
     * @throws KAssertionException Throws this exception if the method does not return the expected value
     * @throws KExistenceException Throws this exception if the method is not found
     */
    public static void kAssertArrayEquals(String functionName, Object o, long[] expected, Object... input) {
        Object val = getValue(functionName, o, input);
        if (!(val instanceof long[]) || !Arrays.equals(expected, (long[]) val)) {
            throw new KAssertionException(functionName, o, val, expected, true, input);
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
        }
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
        } else if (successMessages) {
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
        } else if (successMessages) {
            System.out.println(getAssertionSuccessMessage(functionName, o, Boolean.TRUE, input));
        }
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
        Field f = getField(o.getClass(), fieldName);
        f.setAccessible(true);
        try {
            Object val = f.get(o);
            if (!expected.equals(val)) {
                throw new KAssertionException(fieldName, o, val, expected);
            } else if (successMessages) {
                System.out.println(getFieldAssertionSuccessMessage(fieldName, o, expected));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
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
                if (successMessages) {
                    System.out.println(getAssertionSuccessMessage(functionName, o, expected, input));
                }
                return;
            }
        }
        throw new KAssertionException(functionName, o, output, expectedOutputs, input);
    }

    /**
     * Asserts that a method with the given parameters exists within a class
     *
     * @param functionName The name of the function
     * @param c            The Class being looked at
     * @param input        All parameters being given to the method
     * @throws KExistenceException Throws this exception if the method is not found
     */
    @Deprecated
    private static void kAssertMethodExists(String functionName, Class<?> c, Class<?>... input) {
        findMethod(functionName, c, input);
        if (successMessages)
            System.out.println(getMethodExistenceSuccessMessage(functionName, c, input));
    }

    /**
     * Asserts that a constructor with the given parameters exists within a class
     *
     * @param c     The Class being looked at
     * @param input All parameters being given to the constructor
     * @throws KExistenceException Throws this exception if the constructor is not found
     */
    @Deprecated
    private static void kAssertConstructorExists(Class<?> c, Class<?>... input) {
        findConstructor(c, input);
        if (successMessages)
            System.out.println(getConstructorExistenceSuccessMessage(c, input));
    }

    /**
     * Asserts that a field with the given name exists within a class
     *
     * @param c         The Class being looked at
     * @param fieldName The name of the field
     * @throws KExistenceException Throws this exception if the field is not found
     */
    @Deprecated
    private static void kAssertFieldExists(Class<?> c, String fieldName) {
        getField(c, fieldName);
        if (successMessages)
            System.out.println(getFieldExistenceSuccessMessage(fieldName, c));
    }


    private static Field getField(Class<?> c, String fieldName) {
        try {
            return c.getDeclaredField(fieldName);
        } catch (Exception e) {
            throw new KExistenceException(c, fieldName);
        }
    }

    @Deprecated
    private static void findConstructor(Class<?> c, Class<?>... input) {
        try {
            c.getConstructor(input);
        } catch (NoSuchMethodException e) {
            throw new KExistenceException(c, input);
        }
    }


    private static Object getValue(String functionName, Object o, Object... input) {
        try {
            return getMethod(functionName, o.getClass(), input).invoke(o, input);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    private static void findMethod(String methodName, Class<?> c, Class<?>... input) {
        input = convertToWrappers(input);
        Method[] methods;
        try {
            methods = c.getDeclaredMethods();
        } catch (Exception e) {

            throw new KExistenceException(methodName, c, input);
        }
        for (Method m :
                methods) {
            m.setAccessible(true);
            boolean namesMatch = m.getName().equals(methodName);
            Class<?>[] wrapped = convertToWrappers(m.getParameterTypes());
            boolean paramsMatch = isAcceptableParameters(wrapped, input);
            if (namesMatch && paramsMatch) {
                return;
            }
        }


        throw new KExistenceException(methodName, c, input);
    }

    private static Method getMethod(String methodName, Class<?> c, Object... input) {
        Class<?>[] params = toClassArray(input);
        Method[] methods;
        try {
            methods = c.getMethods();
        } catch (Exception e) {

            throw new KExistenceException(methodName, c, params);
        }
        for (Method m :
                methods) {
            m.setAccessible(true);
            boolean namesMatch = m.getName().equals(methodName);
            Class<?>[] wrapped = convertToWrappers(m.getParameterTypes());
            boolean paramsMatch = isAcceptableParameters(wrapped, params);
            if (namesMatch && paramsMatch) {
                return m;
            }
        }
        throw new KExistenceException(methodName, c, params);
    }

    private static Class<?>[] convertToWrappers(Class<?>[] params) {
        HashMap<Class<?>, Class<?>> map = new HashMap<>();
        map.put(boolean.class, Boolean.class);
        map.put(char.class, Character.class);
        map.put(byte.class, Byte.class);
        map.put(short.class, Short.class);
        map.put(int.class, Integer.class);
        map.put(long.class, Long.class);
        map.put(float.class, Float.class);
        map.put(double.class, Double.class);
        return castToClassArray(Arrays.stream(params).map(param -> map.getOrDefault(param, param)).toArray());

    }

    private static Class<?>[] castToClassArray(Object[] params) {
        Class<?>[] classes = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            classes[i] = (Class<?>) params[i];
        }
        return classes;
    }

    private static Class<?>[] toClassArray(Object[] params) {
        Class<?>[] classes = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            classes[i] = params[i].getClass();
        }
        return classes;
    }

    private static boolean isAcceptableParameters(Class<?>[] arguments, Class<?>[] input) {
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

    private static String getFieldAssertionSuccessMessage(String fieldName, Object o, Object expected) {
        return "Accessed " + fieldName + " in " + o.toString() + " and successfully got " + expected.toString();
    }

    private static String getAssertionSuccessMessage(String methodName, Object o, Object expected, Object... input) {
        return "Called " + methodName + " on " + o.toString() + " with arguments " + Arrays.toString(input) + " and successfully got " + expected.toString();
    }

    private static String getMethodExistenceSuccessMessage(String functionName, Class<?> c, Class<?>... parameters) {
        return "Successfully found function " + functionName + " in class " + c.toString() + " with parameters " + Arrays.toString(parameters);
    }

    private static String getConstructorExistenceSuccessMessage(Class<?> c, Class<?>... parameters) {
        return "Successfully found constructor with parameters " + Arrays.toString(parameters) + " in class " + c.toString();
    }

    private static String getFieldExistenceSuccessMessage(String fieldName, Class<?> c) {
        return "Successfully found field " + fieldName + " in class " + c.toString();
    }
}
