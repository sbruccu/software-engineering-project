package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Standard JUnit 4 Test Suite that groups all tests for MapAdapter.
 *
 * @author Andrea Cocurullo, 2147723
 * @version 1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    MapAdapterCoreTest.class,
    MapAdapterNullConstraintTest.class,
    MapAdapterEqualsHashCodeTest.class,
    MapAdapterKeySetTest.class,
    MapAdapterValuesTest.class,
    MapAdapterEntrySetTest.class,
    MapAdapterIteratorTest.class
})
public class TestRunner {

    /**
     * Default constructor.
     */
    public TestRunner() {
    }

    public static void main(String[] args) {
        org.junit.runner.Result result = org.junit.runner.JUnitCore.runClasses(TestRunner.class);

        System.out.println("Test result: " + (result.wasSuccessful() ? "SUCCESS" : "FAILURE"));
        System.out.println("Number of tests: " + result.getRunCount());
        System.out.println("Number of failed tests: " + result.getFailureCount());
        System.out.println("Time required: " + result.getRunTime() + " ms\n");

        if (!result.wasSuccessful()) {
            System.out.println("Failure details:\n");
            for (org.junit.runner.notification.Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
}
