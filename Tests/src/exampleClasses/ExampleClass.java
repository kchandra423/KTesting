package exampleClasses;

public class ExampleClass {
    private int noPeeking = 0;
    private static int noLooking = 5;
    public static final String message = "hello!!";
    public ExampleClass(int val){
        noPeeking = val;
    }
    public void changeValue() {
        noPeeking = 4;
    }
}
