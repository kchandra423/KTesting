package tests;

import kchandra423.kTesting.*;
import org.junit.Test;


public class UtilTests {
    @Test
    public void testGetClassWithFolder() throws ClassNotFoundException {
        Class c = KUtils.getClass("ExampleClass", System.getProperty("user.dir") + "/Tests/src");
    }


}
