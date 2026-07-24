package myTest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import myAdapter.*;
import java.util.NoSuchElementException;

/**
 * Tests for the values() backing collection: size, contains, clear,
 * iterator, toArray, add (unsupported), remove, containsAll, removeAll,
 * retainAll.
 * <p>
 * <table border="1">
 * <caption></caption>
 * <tr>
 * <th>Summary</th><td>Tests for the values() backing collection: size, contains, clear,
 * iterator, toArray, add (unsupported), remove, containsAll, removeAll,
 * retainAll.</td></tr>
 * <tr><td><b>Test Case Design</b></td>
 * <td>Verifies values() is a live view backed by the map, and all operations
 * propagate correctly to the underlying MapAdapter.</td>
 * </tr>
 * </table>
 *
 * @author Andrea Cocurullo, 2147723
 * @version 1.0
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
     * Tests values() size reflects map size.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values() size reflects map size.</td></tr>
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
     * Tests values().contains().
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values().contains().</td></tr>
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
     * Tests values().clear() backs into map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values().clear() backs into map.</td></tr>
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
     * Tests values iterator remove backs map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values iterator remove backs map.</td></tr>
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
     * Tests values iterator NoSuchElementException.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values iterator NoSuchElementException.</td></tr>
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
     * Tests values iterator IllegalStateException on remove before next.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values iterator IllegalStateException on remove before next.</td></tr>
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
     * Tests values.toArray().
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.toArray().</td></tr>
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
     * Tests values.toArray(Object[]) with larger array.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.toArray(Object[]) with larger array.</td></tr>
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
     * Tests values.add() throws UnsupportedOperation.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.add() throws UnsupportedOperation.</td></tr>
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
     * Tests values.addAll() throws UnsupportedOperation.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.addAll() throws UnsupportedOperation.</td></tr>
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
     * Tests values.remove() backs into map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.remove() backs into map.</td></tr>
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
     * Tests values.remove() for absent value returns false.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.remove() for absent value returns false.</td></tr>
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
     * Tests values.containsAll().
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.containsAll().</td></tr>
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
     * Tests values.removeAll().
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.removeAll().</td></tr>
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
     * Tests values.retainAll().
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.retainAll().</td></tr>
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

    /**
     * Tests values.toArray(null) throws NullPointerException.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.toArray(null) throws NullPointerException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>toArray(null) must throw NullPointerException.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls toArray(null) on values collection.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testValuesToArrayNullThrows() {
        map.values().toArray(null);
    }

    /**
     * Tests values.toArray(Object[]) with an exactly sized array.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values.toArray(Object[]) with an exactly sized array.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>toArray with exact size must not allocate a new array.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Passes an array of exact size to toArray().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Returned array is the exact same instance passed in.</td></tr>
     * </table>
     */
    @Test
    public void testValuesToArrayExactSize() {
        map.put("A", "1");
        map.put("B", "2");
        Object[] input = new Object[2];
        Object[] output = map.values().toArray(input);
        assertSame(input, output);
    }

    /**
     * Tests removeAll with an empty collection.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests removeAll with an empty collection.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Passes an empty HCollection to removeAll.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Checks that removeAll returns false and doesn't modify the map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Returns false.</td></tr>
     * </table>
     */
    @Test
    public void testValuesRemoveAllEmptyCollection() {
        map.put("A", "1");
        map.put("B", "2");
        HCollection emptyColl = new MapAdapter().values();
        assertFalse(map.values().removeAll(emptyColl));
        assertEquals(2, map.size());
    }

    /**
     * Tests retainAll with an empty collection.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests retainAll with an empty collection.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Passes an empty HCollection to retainAll.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Checks that retainAll clears the map and returns true.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Empty map.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Returns true, map becomes empty.</td></tr>
     * </table>
     */
    @Test
    public void testValuesRetainAllEmptyCollection() {
        map.put("A", "1");
        map.put("B", "2");
        HCollection emptyColl = new MapAdapter().values();
        assertTrue(map.values().retainAll(emptyColl));
        assertTrue(map.isEmpty());
    }

    /**
     * Tests removeAll with incompatible types.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests removeAll with incompatible types.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Passes a collection of Integers to a String values collection.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Verifies that removeAll gracefully ignores them.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with String values.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Returns false.</td></tr>
     * </table>
     */
    @Test
    public void testValuesRemoveAllIncompatibleType() {
        map.put("A", "1");
        HMap otherMap = new MapAdapter();
        otherMap.put("K", new Integer(42));
        assertFalse(map.values().removeAll(otherMap.values()));
        assertEquals(1, map.size());
    }

    /**
     * Tests retainAll with incompatible types.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests retainAll with incompatible types.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Passes a collection of Integers to a String values collection.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Verifies that retainAll clears the map since no elements match.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with String values.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Empty map.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Returns true.</td></tr>
     * </table>
     */
    @Test
    public void testValuesRetainAllIncompatibleType() {
        map.put("A", "1");
        HMap otherMap = new MapAdapter();
        otherMap.put("K", new Integer(42));
        assertTrue(map.values().retainAll(otherMap.values()));
        assertTrue(map.isEmpty());
    }
}
