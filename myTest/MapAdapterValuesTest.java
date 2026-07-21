package myTest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import myAdapter.*;
import java.util.NoSuchElementException;

/**
 * <table border="1">
 * <caption>Test Suite Documentation</caption>
 * <tr>
 * <th>Section</th>
 * <th>Section description</th>
 * </tr>
 * <tr>
 * <td><b>Summary</b></td>
 * <td>Tests for the values() backing collection: size, contains, clear,
 * iterator, toArray, add (unsupported), remove, containsAll, removeAll,
 * retainAll.</td>
 * </tr>
 * <tr>
 * <td><b>Test Case Design</b></td>
 * <td>Verifies values() is a live view backed by the map, and all operations
 * propagate correctly to the underlying MapAdapter.</td>
 * </tr>
 * </table>
 */
public class MapAdapterValuesTest {

    private HMap map;

    /**
     * Initializes a fresh empty MapAdapter before each test.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values() size reflects map size.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>values() is a live view.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Adds entries, verifies values().size().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>values().size() equals 2.</td></tr>
     * </table>
     */
    @Test
    public void testValuesSize() {
        map.put("A", "1");
        map.put("B", "2");
        assertEquals(2, map.values().size());
        assertFalse(map.values().isEmpty());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values().contains().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Delegates to containsValue on backing map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Checks contains on values for present and absent values.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>contains() returns correct values.</td></tr>
     * </table>
     */
    @Test
    public void testValuesContains() {
        map.put("A", "1");
        HCollection vals = map.values();
        assertTrue(vals.contains("1"));
        assertFalse(vals.contains("2"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values().clear() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Clearing values must clear backing map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Populates map, clears values(), verifies map empty.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with data.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Map size is 0.</td></tr>
     * </table>
     */
    @Test
    public void testValuesClearBacksMap() {
        map.put("A", "1");
        map.put("B", "2");
        map.values().clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values iterator remove backs map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Iterator.remove() on values must remove entry from map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Iterates values, removes first, verifies map size.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Size decreases to 1.</td></tr>
     * </table>
     */
    @Test
    public void testValuesIteratorRemove() {
        map.put("A", "1");
        map.put("B", "2");
        HCollection values = map.values();
        HIterator it = values.iterator();
        assertTrue(it.hasNext());
        it.next();
        it.remove();
        assertEquals(1, map.size());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values iterator NoSuchElementException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Calling next() beyond elements must throw.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Exhausts values iterator then calls next().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NoSuchElementException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NoSuchElementException.class)
    public void testValuesIteratorNextThrowsNoSuchElement() {
        map.put("A", "1");
        HIterator it = map.values().iterator();
        it.next();
        it.next(); // should throw
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values iterator IllegalStateException on remove before next.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Calling remove() without prior next() must throw.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates values iterator, calls remove() immediately.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>IllegalStateException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = IllegalStateException.class)
    public void testValuesIteratorRemoveBeforeNextThrows() {
        map.put("A", "1");
        HIterator it = map.values().iterator();
        it.remove(); // should throw
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values.toArray().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify toArray returns all values.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Adds entries, converts values to array.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Array length is 2.</td></tr>
     * </table>
     */
    @Test
    public void testValuesToArray() {
        map.put("A", "1");
        map.put("B", "2");
        Object[] arr = map.values().toArray();
        assertEquals(2, arr.length);
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values.toArray(Object[]) with larger array.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Trailing element set to null when array is larger.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Passes large array to values.toArray().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 1 entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>arr[1] is null.</td></tr>
     * </table>
     */
    @Test
    public void testValuesToArrayWithLargerArray() {
        map.put("A", "1");
        Object[] input = new Object[5];
        Object[] arr = map.values().toArray(input);
        assertSame(input, arr);
        assertNull(arr[1]);
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values.add() throws UnsupportedOperation.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>values() is not modifiable via add.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls add() on values collection.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>UnsupportedOperationException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testValuesAddThrows() {
        map.values().add("X");
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values.addAll() throws UnsupportedOperation.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>values() is not modifiable via addAll.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls addAll() on values collection.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>UnsupportedOperationException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testValuesAddAllThrows() {
        HMap m2 = new MapAdapter();
        m2.put("X", "1");
        map.values().addAll(m2.values());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values.remove() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Removing a value via values collection removes the first matching entry.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls values().remove(value), verifies map state.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>remove() returns true, size decreases.</td></tr>
     * </table>
     */
    @Test
    public void testValuesRemoveBacksMap() {
        map.put("A", "1");
        map.put("B", "2");
        boolean removed = map.values().remove("1");
        assertTrue(removed);
        assertEquals(1, map.size());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values.remove() for absent value returns false.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Removing absent value does nothing.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls values().remove() on non-existing value.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 1 entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>remove() returns false.</td></tr>
     * </table>
     */
    @Test
    public void testValuesRemoveAbsentReturnsFalse() {
        map.put("A", "1");
        assertFalse(map.values().remove("Z"));
        assertEquals(1, map.size());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values.containsAll().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify containsAll for values collection.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Checks containsAll with subset and superset values.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A=1, B=2.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>containsAll returns correct booleans.</td></tr>
     * </table>
     */
    @Test
    public void testValuesContainsAll() {
        map.put("A", "1");
        map.put("B", "2");
        HMap sub = new MapAdapter();
        sub.put("X", "1");
        assertTrue(map.values().containsAll(sub.values()));

        sub.put("Y", "99");
        assertFalse(map.values().containsAll(sub.values()));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values.removeAll().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>removeAll on values removes matching entries.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Removes matching values via removeAll.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A=1, B=2, C=3.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Entries with values 1 and 2 removed.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>removeAll returns true, size is 1.</td></tr>
     * </table>
     */
    @Test
    public void testValuesRemoveAll() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HMap toRemove = new MapAdapter();
        toRemove.put("X", "1");
        toRemove.put("Y", "2");
        boolean changed = map.values().removeAll(toRemove.values());
        assertTrue(changed);
        assertEquals(1, map.size());
        assertTrue(map.containsValue("3"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests values.retainAll().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>retainAll on values keeps only matching entries.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Retains only value "1" from map with values 1, 2, 3.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A=1, B=2, C=3.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map only has entry with value 1.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>retainAll returns true, size is 1.</td></tr>
     * </table>
     */
    @Test
    public void testValuesRetainAll() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HMap toRetain = new MapAdapter();
        toRetain.put("X", "1");
        boolean changed = map.values().retainAll(toRetain.values());
        assertTrue(changed);
        assertEquals(1, map.size());
        assertTrue(map.containsValue("1"));
    }
}
