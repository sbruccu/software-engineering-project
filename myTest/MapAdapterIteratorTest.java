package myTest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import myAdapter.*;
import java.util.NoSuchElementException;

/**
 * Tests for iterator full traversal, empty-map iterators, remove-all-via-iteration,
 * live-view consistency, and large map stress test.
 * <p>
 * <table border="1">
 * <caption></caption>
 * <tr>
 * <th>Summary</th><td>Tests for iterator full traversal, empty-map iterators,
 * remove-all-via-iteration, live-view consistency, and large map
 * stress test.</td></tr>
 * <tr><td><b>Test Case Design</b></td>
 * <td>Verifies iterator contracts across all view types, live view propagation,
 * and MapAdapter scalability.</td>
 * </tr>
 * </table>
 */
public class MapAdapterIteratorTest {

    private HMap map;

    /**
     * Initializes a fresh empty MapAdapter before each test.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    /**
     * Tests keySet iterator traverses all keys.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests keySet iterator traverses all keys.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Full iteration must visit every key exactly once.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Iterates all keys, counts them.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 3 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Iteration count equals 3.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetIteratorFullTraversal() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HIterator it = map.keySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            Object key = it.next();
            assertNotNull(key);
            assertTrue(map.containsKey(key));
            count++;
        }
        assertEquals(3, count);
    }

    /**
     * Tests values iterator traverses all values.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values iterator traverses all values.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Full iteration must visit every value exactly once.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Iterates all values, counts them.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 3 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Iteration count equals 3.</td></tr>
     * </table>
     */
    @Test
    public void testValuesIteratorFullTraversal() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HIterator it = map.values().iterator();
        int count = 0;
        while (it.hasNext()) {
            Object val = it.next();
            assertNotNull(val);
            assertTrue(map.containsValue(val));
            count++;
        }
        assertEquals(3, count);
    }

    /**
     * Tests entrySet iterator traverses all entries.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet iterator traverses all entries.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Full iteration must visit every entry exactly once.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Iterates all entries, verifies each is an HEntry with valid key/value.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 3 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Iteration count equals 3, each entry is valid.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetIteratorFullTraversal() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HIterator it = map.entrySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            Object obj = it.next();
            assertTrue(obj instanceof HMap.HEntry);
            HMap.HEntry entry = (HMap.HEntry) obj;
            assertEquals(map.get(entry.getKey()), entry.getValue());
            count++;
        }
        assertEquals(3, count);
    }

    /**
     * Tests iterator on empty map hasNext returns false.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests iterator on empty map hasNext returns false.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Boundary: empty map iterator.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets keySet iterator on empty map, checks hasNext.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>hasNext() returns false.</td></tr>
     * </table>
     */
    @Test
    public void testIteratorOnEmptyMap() {
        assertFalse(map.keySet().iterator().hasNext());
        assertFalse(map.values().iterator().hasNext());
        assertFalse(map.entrySet().iterator().hasNext());
    }

    /**
     * Tests next() on empty map keySet iterator throws.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests next() on empty map keySet iterator throws.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Boundary: next on empty iterator.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls next() on empty keySet iterator.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NoSuchElementException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyKeySetIteratorThrows() {
        map.keySet().iterator().next();
    }

    /**
     * Tests next() on empty map values iterator throws.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests next() on empty map values iterator throws.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Boundary: next on empty iterator.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls next() on empty values iterator.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NoSuchElementException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyValuesIteratorThrows() {
        map.values().iterator().next();
    }

    /**
     * Tests next() on empty map entrySet iterator throws.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests next() on empty map entrySet iterator throws.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Boundary: next on empty iterator.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls next() on empty entrySet iterator.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NoSuchElementException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyEntrySetIteratorThrows() {
        map.entrySet().iterator().next();
    }

    /**
     * Tests removing all elements via keySet iterator empties the map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests removing all elements via keySet iterator empties the map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Iterate and remove every element via keySet iterator.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Iterates keySet, removes each element, verifies map is empty.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 3 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Map size is 0.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetIteratorRemoveAll() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HIterator it = map.keySet().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * Tests removing all elements via entrySet iterator empties the map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests removing all elements via entrySet iterator empties the map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Iterate and remove every element via entrySet iterator.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Iterates entrySet, removes each element, verifies map is empty.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 3 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Map size is 0.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetIteratorRemoveAll() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HIterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * Tests that keySet reflects map changes.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests that keySet reflects map changes.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>keySet is a live view: adding to map must be visible in keySet.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets keySet, then adds to map, checks keySet size increases.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty map, keySet obtained.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>keySet size is 1 after adding to map.</td></tr>
     * </table>
     */
    @Test
    public void testKeySetReflectsMapChanges() {
        HSet keys = map.keySet();
        assertEquals(0, keys.size());
        map.put("A", "1");
        assertEquals(1, keys.size());
        assertTrue(keys.contains("A"));
    }

    /**
     * Tests that values reflects map changes.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests that values reflects map changes.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>values is a live view: adding to map must be visible in values.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets values, then adds to map, checks values size increases.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty map, values obtained.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>values size is 1 after adding to map.</td></tr>
     * </table>
     */
    @Test
    public void testValuesReflectsMapChanges() {
        HCollection vals = map.values();
        assertEquals(0, vals.size());
        map.put("A", "1");
        assertEquals(1, vals.size());
        assertTrue(vals.contains("1"));
    }

    /**
     * Tests that entrySet reflects map changes.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests that entrySet reflects map changes.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>entrySet is a live view: adding to map must be visible in entrySet.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets entrySet, then adds to map, checks entrySet size increases.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty map, entrySet obtained.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>entrySet size is 1 after adding to map.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetReflectsMapChanges() {
        HSet entries = map.entrySet();
        assertEquals(0, entries.size());
        map.put("A", "1");
        assertEquals(1, entries.size());
    }

    /**
     * Stress test with many entries.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Stress test with many entries.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify map handles many insertions and retrievals correctly.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Inserts 1000 entries, verifies size, containsKey, get, then removes all.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is empty after removals.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>All operations succeed with correct results.</td></tr>
     * </table>
     */
    @Test
    public void testLargeMap() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            map.put("key" + i, "val" + i);
        }
        assertEquals(n, map.size());
        for (int i = 0; i < n; i++) {
            assertTrue(map.containsKey("key" + i));
            assertEquals("val" + i, map.get("key" + i));
        }
        for (int i = 0; i < n; i++) {
            map.remove("key" + i);
        }
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * Tests iterator double remove throws IllegalStateException.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests iterator double remove throws IllegalStateException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Calling remove() twice without next() must throw.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls next(), remove(), then remove() again.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 1 entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>IllegalStateException is thrown on second remove.</td></tr>
     * </table>
     */
    @Test(expected = IllegalStateException.class)
    public void testIteratorDoubleRemoveThrows() {
        map.put("A", "1");
        HIterator it = map.keySet().iterator();
        it.next();
        it.remove();
        it.remove(); // should throw
    }

    /**
     * Tests entrySet iterator remove before next throws IllegalStateException.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet iterator remove before next throws IllegalStateException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Calling remove() without prior next() must throw.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates entrySet iterator, calls remove() immediately.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>IllegalStateException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = IllegalStateException.class)
    public void testEntrySetIteratorRemoveBeforeNextThrows() {
        map.put("A", "1");
        HIterator it = map.entrySet().iterator();
        it.remove(); // should throw
    }

    /**
     * Tests values iterator double remove throws IllegalStateException.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests values iterator double remove throws IllegalStateException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Calling remove() twice without next() must throw.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls next(), remove(), then remove() again on values iterator.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>IllegalStateException is thrown on second remove.</td></tr>
     * </table>
     */
    @Test(expected = IllegalStateException.class)
    public void testValuesIteratorDoubleRemoveThrows() {
        map.put("A", "1");
        HIterator it = map.values().iterator();
        it.next();
        it.remove();
        it.remove(); // should throw
    }
}
