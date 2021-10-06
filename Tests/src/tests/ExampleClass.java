package tests;

public class ExampleClass {
    private int noPeeking;
    private static int noLooking = 5;
    public static final String message = "hello!!";

    public ExampleClass() {
        noPeeking = 0;
    }

    public ExampleClass(int val) {
        noPeeking = val;
    }

    public void changeValue() {
        noPeeking = 4;
    }

    public void changeValue(int x) {
        noPeeking = x;
    }

    public void dummyMethod(int x, int dummy) {

    }
}
