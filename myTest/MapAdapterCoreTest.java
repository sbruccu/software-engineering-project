package myTest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import myAdapter.*;

/**
 * <table border="1">
 * <caption>Test Suite Documentation</caption>
 * <tr>
 * <th>Section</th>
 * <th>Section description</th>
 * </tr>
 * <tr>
 * <td><b>Summary</b></td>
 * <td>Tests for core Map operations: size, isEmpty, put, get, remove, clear,
 * containsKey, containsValue, putAll.</td>
 * </tr>
 * <tr>
 * <td><b>Test Case Design</b></td>
 * <td>Verifies basic CRUD functionality and boundary conditions of MapAdapter.</td>
 * </tr>
 * </table>
 */
public class MapAdapterCoreTest {

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
     * <tr><td><b>Summary</b></td><td>Tests size() and isEmpty() on a fresh empty map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Boundary: verify initial state of a new MapAdapter.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls size() and isEmpty() on an empty map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>MapAdapter just created.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is still empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>size() returns 0, isEmpty() returns true.</td></tr>
     * </table>
     */
    @Test
    public void testSizeAndIsEmptyOnNewMap() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests basic put and get functionalities.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify MapAdapter correctly stores and retrieves pairs.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Inserts two key-value pairs, verifies size and retrieval.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map contains two items.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>get() returns correct values; size() is 2; isEmpty() is false.</td></tr>
     * </table>
     */
    @Test
    public void testPutAndGet() {
        map.put("Key1", "Value1");
        map.put("Key2", "Value2");
        assertEquals("Value1", map.get("Key1"));
        assertEquals("Value2", map.get("Key2"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests put() overwrites existing key and returns old value.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify put replaces existing mapping and returns previous value.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts key twice with different values. Checks return value and final state.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map contains 1 item with updated value.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>First put returns null, second put returns old value. Size is 1.</td></tr>
     * </table>
     */
    @Test
    public void testPutOverwriteReturnsOldValue() {
        Object old1 = map.put("A", "1");
        assertNull(old1);
        Object old2 = map.put("A", "2");
        assertEquals("1", old2);
        assertEquals("2", map.get("A"));
        assertEquals(1, map.size());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests get() for a key not present in the map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify get returns null for absent keys.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls get() with a key that was never inserted.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>get() returns null.</td></tr>
     * </table>
     */
    @Test
    public void testGetAbsentKeyReturnsNull() {
        assertNull(map.get("nonexistent"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests remove() functionality.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Ensures remove() deletes the mapping and returns the previous value.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts an element, removes it, verifies return value and new size.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>MapAdapter with one element.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>remove() returns the correct value, size is 0.</td></tr>
     * </table>
     */
    @Test
    public void testRemove() {
        map.put("A", "1");
        Object removed = map.remove("A");
        assertEquals("1", removed);
        assertEquals(0, map.size());
        assertFalse(map.containsKey("A"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests remove() on an absent key.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify remove returns null when key is not present.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls remove() on a key never inserted.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>remove() returns null.</td></tr>
     * </table>
     */
    @Test
    public void testRemoveAbsentKeyReturnsNull() {
        assertNull(map.remove("nonexistent"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests clear() functionality.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Ensures clear() removes all mappings from the map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts elements, calls clear(), verifies size is 0.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>MapAdapter with elements.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>size() returns 0, isEmpty() returns true.</td></tr>
     * </table>
     */
    @Test
    public void testClear() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests clear() on an already empty map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Boundary: clearing an empty map should not throw.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls clear() on a new empty map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map still empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>No exception thrown, size remains 0.</td></tr>
     * </table>
     */
    @Test
    public void testClearOnEmptyMap() {
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests containsKey() functionality.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Ensures containsKey returns true for present and false for absent keys.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts an element, checks containsKey for present and absent keys.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>MapAdapter with one element.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map remains unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>containsKey() returns correct boolean values.</td></tr>
     * </table>
     */
    @Test
    public void testContainsKey() {
        map.put("A", "1");
        assertTrue(map.containsKey("A"));
        assertFalse(map.containsKey("B"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests containsValue() functionality.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Ensures containsValue returns true for present and false for absent values.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts an element, checks containsValue for present and absent values.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>MapAdapter with one element.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map remains unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>containsValue() returns correct boolean values.</td></tr>
     * </table>
     */
    @Test
    public void testContainsValue() {
        map.put("A", "1");
        assertTrue(map.containsValue("1"));
        assertFalse(map.containsValue("2"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests putAll() functionality.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Ensures putAll copies all mappings from the specified map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates a second map, adds elements, calls putAll on the first map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Two MapAdapters.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>First map contains elements from the second map.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Size is correct and get() returns expected values.</td></tr>
     * </table>
     */
    @Test
    public void testPutAll() {
        HMap map2 = new MapAdapter();
        map2.put("A", "1");
        map2.put("B", "2");
        map.putAll(map2);
        assertEquals(2, map.size());
        assertEquals("1", map.get("A"));
        assertEquals("2", map.get("B"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests putAll() with overlapping keys overwrites existing mappings.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify putAll replaces values for shared keys.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Map has key A=1, putAll with A=99, verify A=99.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with key A.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Value for A is overwritten.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>get(A) returns 99.</td></tr>
     * </table>
     */
    @Test
    public void testPutAllOverwritesExistingKeys() {
        map.put("A", "1");
        HMap map2 = new MapAdapter();
        map2.put("A", "99");
        map2.put("B", "2");
        map.putAll(map2);
        assertEquals(2, map.size());
        assertEquals("99", map.get("A"));
        assertEquals("2", map.get("B"));
    }

    /**
     * <table border="1">
     * <caption>Test Method Documentation</caption>
     * <tr><th>Section</th><th>Section description</th></tr>
     * <tr><td><b>Summary</b></td><td>Tests putAll() with an empty source map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Boundary: putAll from empty map does nothing.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls putAll with an empty MapAdapter.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one element.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Size remains 1.</td></tr>
     * </table>
     */
    @Test
    public void testPutAllEmptyMap() {
        map.put("A", "1");
        map.putAll(new MapAdapter());
        assertEquals(1, map.size());
    }
}
