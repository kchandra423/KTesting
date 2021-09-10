package kchandra423.kTesting;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
    @Deprecated
    public static void kAssert(String functionName, Object o, Object... input){
        getMethod(functionName, o, input);
    }
    public static void kAssertMethodExists(String functionName, Object o, Class... input){
        findMethod(functionName, o, input);
    }
    public static void kAssertConstructorExists(Class c, Class... input){
        findConstructor(c, input);
    }
    private static void findConstructor(Class c, Class... input){
        try {
            c.getConstructor(input);
        } catch (NoSuchMethodException e) {
            throw new KException(c, input);
        }
    }
    private static void findMethod(String functionName, Object o, Class... input){

        try {
            o.getClass().getMethod(functionName, input);
        } catch (NoSuchMethodException e) {
            throw new KException(functionName, o, input);
        }
    }

    private static Method getMethod(String methodName, Object obj, Object... input)  {
        Class[] params = new Class[input.length];
        for (int i = 0; i < params.length; i++) {
            params[i] = input[i].getClass();
        }
        Method[] methods = obj.getClass().getMethods();
        for (Method m :
                methods) {
            boolean namesMatch = m.getName().equals(methodName);
            Class[] wrapped = m.getParameterTypes();
            convertToWrappers(wrapped);
            boolean paramsMatch = Arrays.equals(wrapped, params);
            if (namesMatch && paramsMatch) {
                return m;
            }
        }
        return null;
    }

    private static void convertToWrappers(Class[] params){
        for (int i = 0; i < params.length; i++) {
            if(params[i].equals(boolean.class)){
                params[i] = Boolean.class;
            }else if(params[i].equals(char.class)){
                params[i] = Character.class;
            }else if(params[i].equals(byte.class)){
                params[i] = Byte.class;
            }else if(params[i].equals(short.class)){
                params[i] = Short.class;
            }else if(params[i].equals(int.class)){
                params[i] = Integer.class;
            }else if(params[i].equals(long.class)){
                params[i] = Long.class;
            }else if(params[i].equals(float.class)){
                params[i] = Float.class;
            }else if(params[i].equals(double.class)){
                params[i] = Double.class;
            }
        }
    }

    private static String getSuccessMessage(String methodName, Object o, Object expected, Object... input) {
        return "Called " + methodName + " on " + o.toString() + " with arguments " + Arrays.toString(input) + " and successfully got " + expected.toString();
    }
}
