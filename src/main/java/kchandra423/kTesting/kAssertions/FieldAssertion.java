package kchandra423.kTesting.kAssertions;

import kchandra423.kTesting.KUtils;
import kchandra423.kTesting.kExceptions.KAssertionException;
import kchandra423.kTesting.kExceptions.KExistenceException;

import java.lang.reflect.Field;

class FieldAssertion implements Assertion {
    private final String fieldName;
    private final Object o;

    protected FieldAssertion(String fieldName, Object o) {
        this.fieldName = fieldName;
        this.o = o;
    }


    @Override
    public Object getOutput() {
        Class<?> c = o.getClass();
        Field f;
        try {
            f = c.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new KExistenceException(c, fieldName);
        }
        f.setAccessible(true);
        try {
            return f.get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void throwKAssertionException(Object output, Object expected) {
        throw new KAssertionException(fieldName, o, output, expected);
    }

    @Override
    public String getSuccessMessage(Object expected) {
        return "Accessed " + fieldName + " in " + KUtils.toString(o) + " and successfully got " + KUtils.toString(expected);
    }
}
