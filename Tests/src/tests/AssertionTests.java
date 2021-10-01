package tests;

import exampleClasses.ExampleClass;
import kchandra423.kTesting.exceptions.KAssertionException;
import org.junit.Test;

import java.util.ArrayList;

import static kchandra423.kTesting.KAssertion.*;

public class AssertionTests {
    @Test
    public void testKAssertEqualsPositive() {
        Double d1 = 5.;

        kAssertEquals("toString", d1, "5.0");

        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        kAssertEquals("get", lists, "hi!", 0);

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        kAssertEquals("get", ints, 1, 0);
        kAssertEquals("remove", ints, true, 1);
        kAssertEquals("size", ints, 0);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertEqualsNegative1() {
        Double d1 = 5.;
        kAssertEquals("toString", d1, "5");
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertEqualsNegative2() {
        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        kAssertEquals("get", lists, "wrong", 0);

    }

    @Test(expected = KAssertionException.class)
    public void testKAssertEqualsNegative3() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        kAssertEquals("size", ints, 0);
    }

    @Test
    public void testKAssertTruePositive() {
        Double d1 = 5.;
        kAssertTrue("equals", d1, 5.);
        ArrayList<Integer> ints = new ArrayList<>();
        kAssertTrue("isEmpty", ints);
        String s1 = "hi";
        String s2 = "hi";
        kAssertTrue("equals", s1, s2);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertTrueNegative1() {
        double d1 = 5.;
        kAssertTrue("equals", d1, 5);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertTrueNegative2() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        kAssertTrue("isEmpty", ints);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertTrueNegative3() {
        String s1 = "hi";
        String s2 = "bye";
        kAssertTrue("equals", s1, s2);
    }

    @Test
    public void testKAssertFalsePositive() {
        double d1 = 5.;
        kAssertFalse("equals", d1, 5);
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        kAssertFalse("isEmpty", ints);
        String s1 = "hi";
        String s2 = "bye";
        kAssertFalse("equals", s1, s2);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertFalseNegative1() {
        Double d1 = 5.;
        kAssertFalse("equals", d1, 5.);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertFalseNegative2() {
        ArrayList<Integer> ints = new ArrayList<>();
        kAssertFalse("isEmpty", ints);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertFalseNegative3() {
        String s1 = "hi";
        String s2 = "hi";
        kAssertFalse("equals", s1, s2);
    }

    @Test
    public void testKAssertsNotEqualsPositive() {
        Double d1 = 5.;
        kAssertNotEquals("toString", d1, "5");

        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        kAssertNotEquals("get", lists, "wrong", 0);

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        kAssertNotEquals("size", ints, 0);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertsNotEqualsNegative1() {
        Double d1 = 5.;

        kAssertNotEquals("toString", d1, "5.0");
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertsNotEqualsNegative2() {
        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        kAssertNotEquals("get", lists, "hi!", 0);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertsNotEqualsNegative3() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        kAssertNotEquals("get", ints, 1, 0);
        kAssertNotEquals("remove", ints, true, 1);
        kAssertNotEquals("size", ints, 0);
    }

    @Test
    public void testKAssertsEqualsAnyPositive() {

        Double d1 = 5.;
        kAssertEqualsAny("toString", d1, new Object[]{5, 5.0, "5.0"});

        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        kAssertEqualsAny("get", lists, new Object[]{"wrong", "hi!", "5.0"}, 0);

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        kAssertEqualsAny("size", ints, new Object[]{0, 1});
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertsEqualsAnyNegative1() {
        Double d1 = 5.;
        kAssertEqualsAny("toString", d1, new Object[]{5, 4., "5."});
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertsEqualsAnyNegative2() {
        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        kAssertEqualsAny("get", lists, new Object[]{"wrong", "hi?", "5.0"}, 0);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertsEqualsAnyNegative3() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        kAssertEqualsAny("size", ints, new Object[]{0, -1});
    }

    @Test
    public void testKAssertsEqualFieldPositive() {
        ExampleClass o = new ExampleClass(0);
        kAssertEquals(o, "noPeeking", 0);
        kAssertEquals(o, "noLooking", 5);
        o.changeValue();
        kAssertEquals(o, "noPeeking", 4);
        kAssertEquals(o, "message", "hello!!");
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertsEqualFieldNegative1() {
        ExampleClass o = new ExampleClass(0);
        kAssertEquals(o, "noPeeking", 1);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertsEqualFieldNegative2() {
        ExampleClass o = new ExampleClass(0);
        o.changeValue();
        kAssertEquals(o, "noPeeking", 0);
    }

    @Test(expected = KAssertionException.class)
    public void testKAssertsEqualFieldNegative3() {
        ExampleClass o = new ExampleClass(0);
        o.changeValue();
        kAssertEquals(o, "message", "wrong");
    }

}
