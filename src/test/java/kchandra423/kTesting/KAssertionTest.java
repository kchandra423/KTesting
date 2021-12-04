package kchandra423.kTesting;

import org.junit.jupiter.api.Test;
import utils.Tester;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KAssertionTest {
    private static final Tester t = new Tester();

    @Test
    void kAssertTrue() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertTrue("no", t));
        KAssertion.kAssertTrue("yes", t);
    }

    @Test
    void kAssertFalse() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertFalse("yes", t));
        KAssertion.kAssertFalse("no", t);
    }

    @Test
    void kAssertArrayEqualsInt() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnInts", t, new int[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnInts", t, new int[]{5, 6, 3}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnFloats", t, new int[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnInts", t, new int[]{}));
        KAssertion.kAssertArrayEquals("returnInts", t, new int[]{5, 6});
    }

    @Test
    void kAssertArrayEqualsDouble() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnDoubles", t, new double[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnDoubles", t, new double[]{5.5, 6.2, 3}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnFloats", t, new double[]{5.5, 6.2}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnDoubles", t, new double[]{}));
        KAssertion.kAssertArrayEquals("returnDoubles", t, new double[]{5.5, 6.2});
    }

    @Test
    void kAssertArrayEqualsFloat() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnFloats", t, new float[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnFloats", t, new float[]{5.5f, 6.2f, 3}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnDoubles", t, new float[]{5.5f, 6.2f}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnFloats", t, new float[]{}));
        KAssertion.kAssertArrayEquals("returnFloats", t, new float[]{5.5f, 6.2f});
    }

    @Test
    void kAssertArrayEqualsBool() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnBools", t, new boolean[]{false, true}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnBools", t, new boolean[]{true, false, true}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnShorts", t, new boolean[]{true, false}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnBools", t, new boolean[]{}));
        KAssertion.kAssertArrayEquals("returnBools", t, new boolean[]{true, false});
    }

    @Test
    void kAssertArrayEqualsChar() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnChars", t, new char[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnChars", t, new char[]{5, 6, 3}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnInts", t, new char[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnChars", t, new char[]{}));
        KAssertion.kAssertArrayEquals("returnChars", t, new char[]{5, 6});
    }

    @Test
    void kAssertArrayEqualsByte() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnBytes", t, new byte[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnBytes", t, new byte[]{5, 6, 3}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnInts", t, new byte[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnBytes", t, new byte[]{}));
        KAssertion.kAssertArrayEquals("returnBytes", t, new byte[]{5, 6});
    }

    @Test
    void kAssertArrayEqualsShort() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnShorts", t, new short[]{1, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnShorts", t, new short[]{1, 0, 3}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnInts", t, new short[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnShorts", t, new short[]{}));
        KAssertion.kAssertArrayEquals("returnShorts", t, new short[]{1, 0});
    }

    @Test
    void kAssertArrayEqualsLong() {
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnLongs", t, new long[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnLongs", t, new long[]{5, 6, 3}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnInts", t, new long[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertArrayEquals("returnLongs", t, new long[]{}));
        KAssertion.kAssertArrayEquals("returnLongs", t, new long[]{5, 6});
    }

    @Test
    void kAssertEquals() {
        Double d1 = 5.;
        KAssertion.kAssertEquals("toString", d1, "5.0");
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertEquals("toString", d1, "5"));

        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        KAssertion.kAssertEquals("get", lists, "hi!", 0);

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        Integer integer = 0;
        KAssertion.kAssertEquals("get", ints, 1, integer);
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertEquals("get", lists, 2, integer));

        KAssertion.kAssertEquals("remove", ints, true, integer + 1);
        KAssertion.kAssertEquals("size", ints, 0);

    }

    @Test
    void kAssertNotEquals() {
        Double d1 = 5.;
        KAssertion.kAssertNotEquals("toString", d1, "5");
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertNotEquals("toString", d1, "5.0"));

        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertNotEquals("get", lists, "hi!", 0));

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        Integer integer = 0;
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertNotEquals("get", ints, 1, integer));
        KAssertion.kAssertNotEquals("get", lists, 2, integer);

        assertThrows(KAssertionException.class, () -> KAssertion.kAssertNotEquals("remove", ints, true, integer + 1));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertNotEquals("size", ints, 0));
    }

    @Test
    void kAssertFieldEquals() {
        KAssertion.kAssertFieldEquals("noPeeking", t, 0);
        KAssertion.kAssertFieldEquals("noLooking", t, 5);

        assertThrows(KAssertionException.class, () -> KAssertion.kAssertFieldEquals("noPeeking", t, 4));
        t.changeValue();
        KAssertion.kAssertFieldEquals("noPeeking", t, 4);
        KAssertion.kAssertFieldEquals("message", t, "hello!!");
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertFieldEquals("noPeeking", t, 5));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertFieldEquals("message", t, "wrong"));
    }

    @Test
    void kAssertEqualsAny() {
        KAssertion.kAssertEqualsAny("yes", t, new Object[]{true, false, 5.0});
        KAssertion.kAssertEqualsAny("gimme", t, new Object[]{8L, 5, 4.6f, 8});
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertEqualsAny("yes", t, new Object[]{6, false, 5.0}));
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertEqualsAny("gimme", t, new Object[]{8L, 5, 4.6f, 8d}));

        Double d1 = 5.;
        KAssertion.kAssertEqualsAny("toString", d1, new Object[]{5, 4., "5.0"});
        assertThrows(KAssertionException.class, () -> KAssertion.kAssertEqualsAny("toString", d1, new Object[]{5, 4., "5."}));
    }

    @Test
    void kAssertConsoleEquals() {
        KAssertion.kAssertConsoleEquals("printSomething", t, "Something!\n");
        assertThrows(KAssertionException.class, () ->
                KAssertion.kAssertConsoleEquals("printSomething", t, "Nothing"));

        assertThrows(KAssertionException.class, () ->
                KAssertion.kAssertConsoleEquals("printSomething", t, "Something!"));

        KAssertion.kAssertConsoleEquals("printThisThing", t, "This is the thing!1\n\n",1);

        assertThrows(KAssertionException.class, () ->
                KAssertion.kAssertConsoleEquals("printThisThing", t, "This is the thing!1\n",1));



    }
}