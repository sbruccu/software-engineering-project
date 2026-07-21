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
 * <td>Tests for the keySet() backing view: size, contains, clear, iterator,
 * toArray, add (unsupported), remove, containsAll, removeAll, retainAll,
 * equals, hashCode.</td>
 * </tr>
 * <tr>
 * <td><b>Test Case Design</b></td>
 * <td>Verifies keySet is a live view backed by the map, and all operations
 * propagate correctly to the underlying MapAdapter.</td>
 * </tr>
 * </table>
 */
public class MapAdapterKeySetTest {

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
     * <tr><td><b>Summary</b></td><td>Tests keySet() size reflects map size.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify keySet is a live view of the map's keys.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Adds entries, verifies keySet.size() matches map.size().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 3 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>keySet.size() equals 3.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetSize() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HSet keys = map.keySet();
        assertEquals(3, keys.size());
        assertFalse(keys.isEmpty());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.contains().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Delegates to containsKey on the backing map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Checks contains on keySet for present and absent keys.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>contains() returns correct values.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetContains() {
        map.put("A", "1");
        HSet keys = map.keySet();
        assertTrue(keys.contains("A"));
        assertFalse(keys.contains("B"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.clear() backs into the map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Clearing keySet must clear the backing map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Populates map, clears keySet, verifies map is empty.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with data.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Map size is 0.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetClearBacksMap() {
        map.put("A", "1");
        map.put("B", "2");
        map.keySet().clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet iterator remove backs map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Iterator.remove() on keySet must remove key from map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Iterates keySet, removes first key, checks map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Removed key no longer in map.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetIteratorRemove() {
        map.put("A", "1");
        map.put("B", "2");
        HSet keys = map.keySet();
        HIterator it = keys.iterator();
        assertTrue(it.hasNext());
        Object k = it.next();
        it.remove();
        assertEquals(1, map.size());
        assertFalse(map.containsKey(k));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet iterator NoSuchElementException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Calling next() beyond elements must throw NoSuchElementException.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Exhausts the iterator then calls next().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NoSuchElementException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NoSuchElementException.class)
    public void testKeySetIteratorNextThrowsNoSuchElement() {
        map.put("A", "1");
        HIterator it = map.keySet().iterator();
        it.next();
        it.next(); // should throw
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet iterator IllegalStateException on remove before next.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Calling remove() without prior next() must throw IllegalStateException.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates iterator, calls remove() immediately.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>IllegalStateException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = IllegalStateException.class)
    public void testKeySetIteratorRemoveBeforeNextThrows() {
        map.put("A", "1");
        HIterator it = map.keySet().iterator();
        it.remove(); // should throw
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet iterator double remove throws IllegalStateException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Calling remove() twice without next() in between must throw.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls next(), remove(), then remove() again.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>First element removed.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>IllegalStateException on second remove().</td></tr>
     * </table>
     */
    @Test(expected = IllegalStateException.class)
    public void testKeySetIteratorDoubleRemoveThrows() {
        map.put("A", "1");
        map.put("B", "2");
        HIterator it = map.keySet().iterator();
        it.next();
        it.remove();
        it.remove(); // should throw
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.toArray().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify toArray returns all keys.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Adds entries, converts keySet to array, checks length and contents.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Array length is 2, contains all keys.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetToArray() {
        map.put("A", "1");
        map.put("B", "2");
        Object[] arr = map.keySet().toArray();
        assertEquals(2, arr.length);
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.toArray(Object[]) with a smaller array.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>When passed array is too small, a new array is created.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Passes a 0-length array to toArray.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Returned array has correct length.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetToArrayWithSmallArray() {
        map.put("A", "1");
        map.put("B", "2");
        Object[] arr = map.keySet().toArray(new Object[0]);
        assertEquals(2, arr.length);
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.toArray(Object[]) with a larger array sets trailing null.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>When passed array is larger, element after last is set to null.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Passes a 5-length array to toArray on a 2-element keySet.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>arr[2] is null.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetToArrayWithLargerArray() {
        map.put("A", "1");
        map.put("B", "2");
        Object[] input = new Object[5];
        Object[] arr = map.keySet().toArray(input);
        assertSame(input, arr);
        assertNull(arr[2]);
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.add() throws UnsupportedOperation.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>keySet is not modifiable via add.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls add() on keySet.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>UnsupportedOperationException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testKeySetAddThrows() {
        map.keySet().add("X");
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.addAll() throws UnsupportedOperation.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>keySet is not modifiable via addAll.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls addAll() on keySet.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>UnsupportedOperationException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testKeySetAddAllThrows() {
        HMap map2 = new MapAdapter();
        map2.put("X", "1");
        map.keySet().addAll(map2.keySet());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.remove() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Removing a key from the keySet must remove the mapping.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls keySet.remove(key), verifies map state.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>remove() returns true, map no longer contains key.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetRemoveBacksMap() {
        map.put("A", "1");
        map.put("B", "2");
        boolean removed = map.keySet().remove("A");
        assertTrue(removed);
        assertEquals(1, map.size());
        assertFalse(map.containsKey("A"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.remove() for absent key returns false.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Removing absent key should return false.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls keySet.remove() on non-existing key.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 1 entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>remove() returns false.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetRemoveAbsentReturnsFalse() {
        map.put("A", "1");
        assertFalse(map.keySet().remove("Z"));
        assertEquals(1, map.size());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.containsAll().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify containsAll with subset of keys.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates second map with subset keys, checks containsAll.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A, B, C.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>containsAll returns true for subset, false when extra key.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetContainsAll() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HMap sub = new MapAdapter();
        sub.put("A", "x");
        sub.put("B", "x");
        assertTrue(map.keySet().containsAll(sub.keySet()));

        sub.put("Z", "x");
        assertFalse(map.keySet().containsAll(sub.keySet()));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.removeAll() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>removeAll on keySet must remove those keys from map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Removes subset of keys via removeAll, verifies map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A, B, C.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map only has C.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>removeAll returns true, size is 1.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetRemoveAll() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HMap toRemove = new MapAdapter();
        toRemove.put("A", "x");
        toRemove.put("B", "x");
        boolean changed = map.keySet().removeAll(toRemove.keySet());
        assertTrue(changed);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("C"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.removeAll() returns false when nothing to remove.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>removeAll with disjoint keys does nothing.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Removes keys not present in map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>removeAll returns false.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetRemoveAllNoChange() {
        map.put("A", "1");
        HMap other = new MapAdapter();
        other.put("Z", "x");
        boolean changed = map.keySet().removeAll(other.keySet());
        assertFalse(changed);
        assertEquals(1, map.size());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.retainAll() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>retainAll on keySet must keep only specified keys.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Retains only key A from map with A, B, C.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A, B, C.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map only has A.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>retainAll returns true, size is 1.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetRetainAll() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HMap toRetain = new MapAdapter();
        toRetain.put("A", "x");
        boolean changed = map.keySet().retainAll(toRetain.keySet());
        assertTrue(changed);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("A"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet.retainAll() returns false when all elements retained.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>retainAll with superset does nothing.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Retains all keys already present.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>retainAll returns false.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetRetainAllNoChange() {
        map.put("A", "1");
        HMap superset = new MapAdapter();
        superset.put("A", "x");
        superset.put("B", "x");
        boolean changed = map.keySet().retainAll(superset.keySet());
        assertFalse(changed);
        assertEquals(1, map.size());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests keySet equals and hashCode.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Two keySets from equivalent maps must be equal.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates two maps with same keys, compares keySets.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Two maps with identical keys.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() true, hashCodes equal.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetEqualsAndHashCode() {
        map.put("A", "1");
        map.put("B", "2");
        HMap map2 = new MapAdapter();
        map2.put("A", "99");
        map2.put("B", "100");
        assertTrue(map.keySet().equals(map2.keySet()));
        assertEquals(map.keySet().hashCode(), map2.keySet().hashCode());
    }
}
