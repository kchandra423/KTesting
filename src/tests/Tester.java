package tests;

import kchandra423.kTesting.exceptions.KAssertionException;
import org.junit.Test;

import java.util.ArrayList;

import static kchandra423.kTesting.KAssertion.*;

public class Tester {

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
    public void testKAssertsNotEqualsPositive(){

        Double d1 = 5.;
        kAssertNotEquals("toString", d1, "5");

        ArrayList<String> lists = new ArrayList<>();
        lists.add("hi!");
        kAssertNotEquals("get", lists, "wrong", 0);

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        kAssertNotEquals("size", ints, 0);
    }

}
