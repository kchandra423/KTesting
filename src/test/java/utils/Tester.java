package utils;

public class Tester {
    private int noPeeking;
    private static int noLooking = 5;
    public static final String message = "hello!!";

    public Tester() {
        noPeeking = 0;
    }

    public Tester(int val) {
        noPeeking = val;
    }

    public boolean yes() {
        return true;
    }

    public static int gimme() {
        return 8;
    }

    public boolean no() {
        return false;
    }

    public void changeValue() {
        noPeeking = 4;
    }

    public void changeValue(int x) {
        noPeeking = x;
    }

    public void dummyMethod(int x, int dummy) {

    }

    public int[] returnInts() {
        return new int[]{5, 6};
    }

    public double[] returnDoubles() {
        return new double[]{5.5, 6.2};
    }

    public float[] returnFloats() {
        return new float[]{5.5f, 6.2f};
    }

    public boolean[] returnBools() {
        return new boolean[]{true, false};
    }

    public char[] returnChars() {
        return new char[]{5, 6};
    }

    public byte[] returnBytes() {
        return new byte[]{5, 6};
    }

    public short[] returnShorts() {
        return new short[]{1, 0};
    }

    public long[] returnLongs() {
        return new long[]{5, 6};
    }


    public Object echo(Object param) {
        return param;
    }


    public String toString() {
        return "Example class!";
    }


}
