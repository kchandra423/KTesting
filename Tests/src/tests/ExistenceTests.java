package tests;

import kchandra423.kTesting.KAssertion;
import kchandra423.kTesting.KExistenceException;
import org.junit.Test;

import static kchandra423.kTesting.KAssertion.*;

public class ExistenceTests {

    @Test
    public void testKAssertFieldExistsPositive() {
        KAssertion.kAssertFieldExists(ExampleClass.class, "noPeeking");
    }

    @Test(expected = KExistenceException.class)
    public void testKAssertFieldExistsNegative() {
        kAssertFieldExists(ExampleClass.class, "nope");
    }

    @Test
    public void testKAssertMethodExistsPositive() {
        kAssertMethodExists("changeValue", ExampleClass.class);
        kAssertMethodExists("changeValue", ExampleClass.class, int.class);
        kAssertMethodExists("changeValue", ExampleClass.class, Integer.class);
        kAssertMethodExists("dummyMethod", ExampleClass.class, Integer.class, int.class);
    }

    @Test(expected = KExistenceException.class)
    public void testKAssertMethodExistsNegative() {
        kAssertMethodExists("ow", ExampleClass.class);
        kAssertMethodExists("changeValue", ExampleClass.class, double.class);
        kAssertMethodExists("changeValue", ExampleClass.class, Object.class);
        kAssertMethodExists("dummyMethod", ExampleClass.class, int.class);
    }

    @Test
    public void testKAssertConstructorExistsPositive() {
        kAssertConstructorExists(ExampleClass.class, int.class);
        kAssertConstructorExists(ExampleClass.class);
    }

    @Test(expected = KExistenceException.class)
    public void testKAssertConstructorExistsNegative() {
        kAssertConstructorExists(ExampleClass.class, String.class);
    }
}
