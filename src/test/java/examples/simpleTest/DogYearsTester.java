package examples.simpleTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static kchandra423.kTesting.KAssertion.kAssertEquals;


public class DogYearsTester {
    public static void testConvertToHuman() {
        DogYears d = new DogYears();
        kAssertEquals("convertToHumanYears", d, 1, 13);
        kAssertEquals("convertToHumanYears", d, 2, 18);
        kAssertEquals("convertToHumanYears", d, 3, 24);
        kAssertEquals("convertToHumanYears", d, 4, 29);
        kAssertEquals("convertToHumanYears", d, 6, 40);
        kAssertEquals("convertToHumanYears", d, 10, 61);
        kAssertEquals("convertToHumanYears", d, 14, 82);
    }

    public static void testConvertToDog() {
        DogYears d = new DogYears();
        kAssertEquals("convertToDogYears", d, 13, 1);
        kAssertEquals("convertToDogYears", d, 18, 2);
        kAssertEquals("convertToDogYears", d, 24, 3);
        kAssertEquals("convertToDogYears", d, 29, 4);
        kAssertEquals("convertToDogYears", d, 40, 6);
        kAssertEquals("convertToDogYears", d, 61, 10);
        kAssertEquals("convertToDogYears", d, 82, 14);
    }


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String choice = args[0];
        if (choice.equals("all")) {
            Method[] methods = DogYearsTester.class.getMethods();
            for (Method m :
                    methods) {
                if (!m.getName().equals("main") && Modifier.isPublic(m.getModifiers()) && Modifier.isStatic(m.getModifiers())) {
                    m.invoke(null);
                }
            }
        } else {
            Method m = DogYearsTester.class.getMethod(choice);
            m.invoke(null);
        }
    }

}
