package tests;

import exampleClasses.ExampleClass;
import kchandra423.kTesting.exceptions.KExistenceException;
import org.junit.Test;

import static kchandra423.kTesting.KAssertion.*;

public class ExistenceTests {
    @Test
    public void testKAssertFieldExistsPositive() {
        kAssertFieldExists(ExampleClass.class, "noPeeking");
    }
    @Test(expected = KExistenceException.class)
    public void testKAssertFieldExistsNegative() {
        kAssertFieldExists(ExampleClass.class, "nope");
    }
    @Test
    public void testKAssertMethodExistsPositive() {
        kAssertMethodExists("changeValue", ExampleClass.class);
    }
    @Test(expected = KExistenceException.class)
    public void testKAssertMethodExistsNegative() {
        kAssertMethodExists("ow", ExampleClass.class);
    }
    @Test
    public void testKAssertConstructorExistsPositive(){
        kAssertConstructorExists(ExampleClass.class, int.class);
    }
    @Test(expected = KExistenceException.class)
    public void testKAssertConstructorExistsNegative(){
        kAssertConstructorExists(ExampleClass.class);
    }
}
