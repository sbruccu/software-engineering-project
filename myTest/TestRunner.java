package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Command line entry point for running the test suite.
 * Prints execution metrics including run count, failure count, and time
 * elapsed.
 */
public class TestRunner {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        Result result = JUnitCore.runClasses(
            MapAdapterCoreTest.class,
            MapAdapterNullConstraintTest.class,
            MapAdapterEqualsHashCodeTest.class,
            MapAdapterKeySetTest.class,
            MapAdapterValuesTest.class,
            MapAdapterEntrySetTest.class,
            MapAdapterIteratorTest.class
        );
        long endTime = System.currentTimeMillis();

        System.out.println("Test Results:");
        System.out.println("Total tests run: " + result.getRunCount());
        System.out.println("Failed tests   : " + result.getFailureCount());
        System.out.println("Time elapsed   : " + (endTime - startTime) + " ms");
        System.out.println("Overall Success: " + result.wasSuccessful());

        if (!result.wasSuccessful()) {
            System.out.println("\nFailures details:");
            for (Failure failure : result.getFailures()) {
                System.out.println("- " + failure.toString());
            }
        }
    }
}