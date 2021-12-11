package kchandra423.kTesting.kAssertions;

import kchandra423.kTesting.KUtils;
import kchandra423.kTesting.kExceptions.KAssertionException;
import kchandra423.kTesting.kExceptions.KExistenceException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

class MethodAssertion implements Assertion {
    protected final String functionName;
    protected final Object o;
    protected final Object[] input;

    public MethodAssertion(String functionName, Object o, Object[] input) {
        this.functionName = functionName;
        this.o = o;
        this.input = input;
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

    @Override
    public Object getOutput() {
        Class<?> c = o.getClass();
        Class<?>[] params = toClassArray(input);
        Method[] methods;
        Method foundMethod = null;
        try {
            methods = c.getMethods();
        } catch (Exception e) {

            throw new KExistenceException(functionName, c, params);
        }
        for (Method m :
                methods) {
            m.setAccessible(true);
            boolean namesMatch = m.getName().equals(functionName);
            Class<?>[] wrapped = convertToWrappers(m.getParameterTypes());
            boolean paramsMatch = isAcceptableParameters(wrapped, params);
            if (namesMatch && paramsMatch) {
                foundMethod = m;
                break;
            }
        }
        if (foundMethod == null) {
            throw new KExistenceException(functionName, c, params);
        }
        try {
            return foundMethod.invoke(o, input);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void throwKAssertionException(Object output, Object expected) {
        throw new KAssertionException(functionName, o, output, expected, input);
    }

    @Override
    public String getSuccessMessage(Object expected) {
        return "Called " + functionName + " on " + KUtils.toString(o) + " with arguments " + Arrays.toString(input) + " and successfully got " + KUtils.toString(expected);
    }
}
