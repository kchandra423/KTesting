import kchandra423.kTesting.KAssertion;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import tests.AssertionTests;
import tests.ExistenceTests;
import tests.UtilTests;


public class Tester {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        KAssertion.enableSuccessMessages(false);
        Result result = junit.run(AssertionTests.class, ExistenceTests.class, UtilTests.class);
        resultReport(result);
    }

    public static void resultReport(Result result) {
        System.out.println("Finished. Result: Failures: " +
                result.getFailureCount() + ". Ignored: " +
                result.getIgnoreCount() + ". Tests run: " +
                result.getRunCount() + ". Time: " +
                result.getRunTime() + "ms.");
    }

}
