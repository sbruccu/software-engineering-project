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
    public TestRunner() {}
}