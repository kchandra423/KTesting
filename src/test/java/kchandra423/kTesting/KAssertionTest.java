package kchandra423.kTesting;

import kchandra423.kTesting.kAssertions.KAssert;
import kchandra423.kTesting.kExceptions.KAssertionException;
import org.junit.jupiter.api.Test;
import utils.Tester;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KAssertionTest {
    private static final Tester t = new Tester();

    @Test
    void kAssertTrue() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertTrue("no", t));
        KAssert.kAssertTrue("yes", t);
    }

    @Test
    void kAssertFalse() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertFalse("yes", t));
        KAssert.kAssertFalse("no", t);
    }

    @Test
    void kAssertArrayEqualsInt() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnInts", t, new int[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnInts", t, new int[]{5, 6, 3}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnFloats", t, new int[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnInts", t, new int[]{}));
        KAssert.kAssertEquals("returnInts", t, new int[]{5, 6});
    }

    @Test
    void kAssertArrayEqualsDouble() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnDoubles", t, new double[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnDoubles", t, new double[]{5.5, 6.2, 3}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnFloats", t, new double[]{5.5, 6.2}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnDoubles", t, new double[]{}));
        KAssert.kAssertEquals("returnDoubles", t, new double[]{5.5, 6.2});
    }

    @Test
    void kAssertArrayEqualsFloat() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnFloats", t, new float[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnFloats", t, new float[]{5.5f, 6.2f, 3}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnDoubles", t, new float[]{5.5f, 6.2f}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnFloats", t, new float[]{}));
        KAssert.kAssertEquals("returnFloats", t, new float[]{5.5f, 6.2f});
    }

    @Test
    void kAssertArrayEqualsBool() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnBools", t, new boolean[]{false, true}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnBools", t, new boolean[]{true, false, true}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnShorts", t, new boolean[]{true, false}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnBools", t, new boolean[]{}));
        KAssert.kAssertEquals("returnBools", t, new boolean[]{true, false});
    }

    @Test
    void kAssertArrayEqualsChar() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnChars", t, new char[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnChars", t, new char[]{5, 6, 3}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnInts", t, new char[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnChars", t, new char[]{}));
        KAssert.kAssertEquals("returnChars", t, new char[]{5, 6});
    }

    @Test
    void kAssertArrayEqualsByte() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnBytes", t, new byte[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnBytes", t, new byte[]{5, 6, 3}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnInts", t, new byte[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnBytes", t, new byte[]{}));
        KAssert.kAssertEquals("returnBytes", t, new byte[]{5, 6});
    }

    @Test
    void kAssertArrayEqualsShort() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnShorts", t, new short[]{1, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnShorts", t, new short[]{1, 0, 3}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnInts", t, new short[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnShorts", t, new short[]{}));
        KAssert.kAssertEquals("returnShorts", t, new short[]{1, 0});
    }

    @Test
    void kAssertArrayEqualsLong() {
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnLongs", t, new long[]{4, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnLongs", t, new long[]{5, 6, 3}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnInts", t, new long[]{5, 6}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("returnLongs", t, new long[]{}));
        KAssert.kAssertEquals("returnLongs", t, new long[]{5, 6});
    }

    @Test
    void kAssertEquals() {
        Double d1 = 5.;
        KAssert.kAssertEquals("toString", d1, "5.0");
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("toString", d1, "5"));

        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        KAssert.kAssertEquals("get", lists, "hi!", 0);

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        Integer integer = 0;
        KAssert.kAssertEquals("get", ints, 1, integer);
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEquals("get", lists, 2, integer));

        KAssert.kAssertEquals("remove", ints, true, integer + 1);
        KAssert.kAssertEquals("size", ints, 0);

    }

    @Test
    void kAssertFieldEquals() {
        KAssert.kAssertFieldEquals("noPeeking", t, 0);
        KAssert.kAssertFieldEquals("noLooking", t, 5);

        assertThrows(KAssertionException.class, () -> KAssert.kAssertFieldEquals("noPeeking", t, 4));
        t.changeValue();
        KAssert.kAssertFieldEquals("noPeeking", t, 4);
        KAssert.kAssertFieldEquals("message", t, "hello!!");
        assertThrows(KAssertionException.class, () -> KAssert.kAssertFieldEquals("noPeeking", t, 5));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertFieldEquals("message", t, "wrong"));
    }

    @Test
    void kAssertEqualsAny() {
        KAssert.kAssertEqualsAny("yes", t, new Object[]{true, false, 5.0});
        KAssert.kAssertEqualsAny("gimme", t, new Object[]{8L, 5, 4.6f, 8});
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEqualsAny("yes", t, new Object[]{6, false, 5.0}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEqualsAny("gimme", t, new Object[]{8L, 5, 4.6f, 8d}));

        Double d1 = 5.;
        KAssert.kAssertEqualsAny("toString", d1, new Object[]{5, 4., "5.0"});
        assertThrows(KAssertionException.class, () -> KAssert.kAssertEqualsAny("toString", d1, new Object[]{5, 4., "5."}));
    }

    @Test
    void kAssertConsoleEquals() {
        KAssert.kAssertConsoleEquals("printSomething", t, "Something!");
        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEquals("printSomething", t, "Nothing"));
        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEquals("printSomething", t, "Something!\n"));

        KAssert.kAssertConsoleEquals("printThisThing", t, "This is the thing!1", 1);

        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEquals("printThisThing", t, "This is not the thing!1", 1));


    }

    @Test
    void kAssertConsoleEqualsWithTrim() {
        KAssert.kAssertConsoleEquals("printWeird", t, new String[]{"\n", "\t", " "}, "e");
        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEquals("printWeird", t, new String[]{}, "e"));
        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEquals("printWeird", t, new String[]{"\n", "\t"}, "e"));


        KAssert.kAssertConsoleEquals("printWeird", t, new String[]{"\n", "\t"}, " e");

        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEquals("printWeird", t, new String[]{"\t"}, "\n\n e"));


    }

    @Test
    void kAssertConsoleEqualsAny() {
        KAssert.kAssertConsoleEqualsAny("printSomething", new String[]{"Something!", "not something"}, t);
        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEqualsAny("printSomething", new String[]{"nothing", "not something"}, t));
        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEqualsAny("printSomething", new String[]{"Something!\n", "Something!\t"}, t));

        KAssert.kAssertConsoleEqualsAny("printThisThing", new String[]{"This is the thing!1", "this is not the thing!"}, t, 1);

        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEqualsAny("printThisThing", new String[]{"This is also not the thing!1", "this is not the thing!"}, t, 1));
    }

    @Test
    void kAssertFieldEqualsAny() {
        KAssert.kAssertFieldEqualsAny("noPeeking", t, new Object[]{0, 4, new ArrayList<>()});
        KAssert.kAssertFieldEqualsAny("noLooking", t, new Object[]{5, 4, "yes"});

        assertThrows(KAssertionException.class, () -> KAssert.kAssertFieldEqualsAny("noPeeking", t, new Object[]{"4", -4}));
        t.changeValue();
        KAssert.kAssertFieldEqualsAny("message", t, new Object[]{"hello!!", "goodbye"});
        assertThrows(KAssertionException.class, () -> KAssert.kAssertFieldEqualsAny("noPeeking", t, new Object[]{5, 3}));
        assertThrows(KAssertionException.class, () -> KAssert.kAssertFieldEqualsAny("message", t, new Object[]{"wrong", "seemingly correct"}));
    }

    @Test
    void kAssertConsoleEqualsAnyWithTrim() {
        KAssert.kAssertConsoleEqualsAny("printWeird", new String[]{"\n", "\t", " "}, new String[]{"e", "ur mom"}, t);
        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEqualsAny("printWeird", new String[]{}, new String[]{"e", "blad"}, t));
        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEqualsAny("printWeird", new String[]{"\n", "\t"}, new String[]{"e", "b", "z"}, t));


        KAssert.kAssertConsoleEqualsAny("printWeird", new String[]{"\n", "\t"}, new String[]{" e", "ur dad"}, t);

        assertThrows(KAssertionException.class, () ->
                KAssert.kAssertConsoleEqualsAny("printWeird", new String[]{"\t"}, new String[]{"\n\n e", "\ne"}, t));
    }
}