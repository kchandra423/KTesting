package kchandra423.kTesting.kAssertions;

import java.util.Arrays;

interface Assertion {
    static void kAssert(Object expected, Assertion assertion) {
        Object output = assertion.getOutput();
        if (!Assertion.equals(expected, output)) {
            assertion.throwKAssertionException(output, expected);
        } else if (KAssert.successMessagesEnabled()) {
            System.out.print(assertion.getSuccessMessage(expected));
        }
    }

    static void kAssertAny(Object[] expected, Assertion assertion) {
        Object output = assertion.getOutput();
        for (Object val : expected) {
            if (equals(val, output)) {
                if (KAssert.successMessagesEnabled()) {
                    System.out.print(assertion.getSuccessMessage(expected));
                }
                return;
            }
        }
        assertion.throwKAssertionException(output, expected);
    }

    static boolean equals(Object expected, Object output) {
        if (expected.getClass().isArray()) {
            if (expected instanceof int[]) {
                return output instanceof int[] && Arrays.equals((int[]) expected, (int[]) output);
            } else if (expected instanceof float[]) {
                return output instanceof float[] && Arrays.equals((float[]) expected, (float[]) output);
            } else if (expected instanceof double[]) {
                return output instanceof double[] && Arrays.equals((double[]) expected, (double[]) output);
            } else if (expected instanceof long[]) {
                return output instanceof long[] && Arrays.equals((long[]) expected, (long[]) output);
            } else if (expected instanceof short[]) {
                return output instanceof short[] && Arrays.equals((short[]) expected, (short[]) output);
            } else if (expected instanceof boolean[]) {
                return output instanceof boolean[] && Arrays.equals((boolean[]) expected, (boolean[]) output);
            } else if (expected instanceof char[]) {
                return output instanceof char[] && Arrays.equals((char[]) expected, (char[]) output);
            } else if (expected instanceof byte[]) {
                return output instanceof byte[] && Arrays.equals((byte[]) expected, (byte[]) output);
            } else if (expected instanceof Object[]) {
                return output instanceof Object[] && Arrays.deepEquals((Object[]) expected, (Object[]) output);
            } else {
                return false;
            }
        } else {
            return output.equals(expected);
        }
    }

    Object getOutput();

    void throwKAssertionException(Object output, Object expected);

    String getSuccessMessage(Object expected);
}
