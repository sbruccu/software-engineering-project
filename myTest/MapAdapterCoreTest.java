package myTest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import myAdapter.*;

/**
 * Tests for core Map operations: size, isEmpty, put, get, remove, clear,
 * containsKey, containsValue, putAll.
 * <p>
 * <table border="1">
 * <caption></caption>
 * <tr>
 * <th>Summary</th><td>Tests for core Map operations: size, isEmpty, put, get, remove, clear,
 * containsKey, containsValue, putAll.</td></tr>
 * <tr><td><b>Test Case Design</b></td>
 * <td>Verifies basic CRUD functionality and boundary conditions of MapAdapter.</td>
 * </tr>
 * </table>
 *
 * @author Andrea Cocurullo, 2147723
 * @version 1.0
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
     * Tests size() and isEmpty() on a fresh empty map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests size() and isEmpty() on a fresh empty map.</td></tr>
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
     * Tests basic put and get functionalities.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests basic put and get functionalities.</td></tr>
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
     * Tests put() overwrites existing key and returns old value.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests put() overwrites existing key and returns old value.</td></tr>
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
     * Tests get() for a key not present in the map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests get() for a key not present in the map.</td></tr>
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
     * Tests remove() functionality.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests remove() functionality.</td></tr>
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
     * Tests remove() on an absent key.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests remove() on an absent key.</td></tr>
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
     * Tests clear() functionality.
     * <p>
     * <table border="1">   
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests clear() functionality.</td></tr>
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
     * Tests clear() on an already empty map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests clear() on an already empty map.</td></tr>
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
     * Tests containsKey() functionality.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests containsKey() functionality.</td></tr>
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
     * Tests containsValue() functionality.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests containsValue() functionality.</td></tr>
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
     * Tests putAll() functionality.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests putAll() functionality.</td></tr>
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
     * Tests putAll() with overlapping keys overwrites existing mappings.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests putAll() with overlapping keys overwrites existing mappings.</td></tr>
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
     * Tests putAll() with an empty source map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests putAll() with an empty source map.</td></tr>
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

    /**
     * Tests get() on a removed key returns null.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests get() on a removed key returns null.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>After remove, the key must no longer be retrievable.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts a key, removes it, then calls get() on it.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>get() returns null.</td></tr>
     * </table>
     */
    @Test
    public void testGetAfterRemoveReturnsNull() {
        map.put("A", "1");
        map.remove("A");
        assertNull(map.get("A"));
    }

    /**
     * Tests containsKey() and containsValue() return false after clear().
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests containsKey() and containsValue() return false after clear().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>After clear, no key or value should be found.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Populates the map, clears it, checks containsKey and containsValue.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Both containsKey and containsValue return false.</td></tr>
     * </table>
     */
    @Test
    public void testContainsKeyAndValueAfterClear() {
        map.put("A", "1");
        map.clear();
        assertFalse(map.containsKey("A"));
        assertFalse(map.containsValue("1"));
    }

    /**
     * Tests get() on any key after clear() returns null.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests get() on any key after clear() returns null.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>After clear, all keys are gone so get must return null.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Populates the map, clears it, then calls get() on a previously present key.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with entry A=1.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map is empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>get("A") returns null.</td></tr>
     * </table>
     */
    @Test
    public void testGetAfterClearReturnsNull() {
        map.put("A", "1");
        map.clear();
        assertNull(map.get("A"));
    }

    /**
     * Tests identity vs equality for map keys.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests identity vs equality for map keys.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Hashtable relies on .equals(), not ==.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts two different String instances with the same value and verifies they overwrite.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty map.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Second put returns first value, size is 1.</td></tr>
     * </table>
     */
    @Test
    public void testKeyEqualityVsIdentity() {
        String key1 = new String("A");
        String key2 = new String("A");
        assertNotSame(key1, key2); // Ensure they are different instances
        
        assertNull(map.put(key1, "1"));
        assertEquals("1", map.put(key2, "2"));
        assertEquals(1, map.size());
        assertEquals("2", map.get("A"));
    }

    /**
     * Stress test for MapAdapter.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Stress test for MapAdapter.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Puts a large number of elements to trigger rehashing.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Inserts 100000 entries, removes 50000, checks size.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty map.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 50000 entries.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Size is accurately maintained under heavy load.</td></tr>
     * </table>
     */
    @Test
    public void testStressLoad() {
        int limit = 100000;
        for (int i = 0; i < limit; i++) {
            map.put(String.valueOf(i), i);
        }
        assertEquals(limit, map.size());
        for (int i = 0; i < limit / 2; i++) {
            map.remove(String.valueOf(i));
        }
        assertEquals(limit / 2, map.size());
    }

    /**
     * Tests self-referential map hashCode throws StackOverflowError.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests self-referential map hashCode throws StackOverflowError.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Corner case: map containing itself.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts the map into itself and calls hashCode().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty map.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map contains itself.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>StackOverflowError is thrown.</td></tr>
     * </table>
     */
    @Test(expected = StackOverflowError.class)
    public void testSelfReferentialHashCode() {
        map.put("self", map);
        map.hashCode();
    }

    /**
     * A simple key class that intentionally causes hash collisions.
     */
    private static class CollidingKey {
        private String id;
        public CollidingKey(String id) { this.id = id; }
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CollidingKey that = (CollidingKey) o;
            return id != null ? id.equals(that.id) : that.id == null;
        }
        public int hashCode() {
            return 1; // Constant hash code forces collisions
        }
    }

    /**
     * Tests behavior with many hash collisions.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests behavior with many hash collisions.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Inserts keys with the exact same hash code.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Verifies map logic holds up when Hashtable lists are heavily used.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty map.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map contains 1000 colliding elements.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>get() and remove() work correctly despite collisions.</td></tr>
     * </table>
     */
    @Test
    public void testHashCollisions() {
        for (int i = 0; i < 1000; i++) {
            map.put(new CollidingKey("K" + i), i);
        }
        assertEquals(1000, map.size());
        assertEquals(500, map.get(new CollidingKey("K500")));
        map.remove(new CollidingKey("K500"));
        assertNull(map.get(new CollidingKey("K500")));
        assertEquals(999, map.size());
    }

    /**
     * Tests empty strings and special characters.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests empty strings and special characters.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Uses unusual string values.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts and gets empty strings and escape characters.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty map.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map contains these special elements.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Proper retrieval.</td></tr>
     * </table>
     */
    @Test
    public void testSpecialCharacters() {
        map.put("", "empty");
        map.put("\n\t\r", "");
        
        assertEquals("empty", map.get(""));
        assertEquals("", map.get("\n\t\r"));
    }

    /**
     * Tests putAll with itself.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests putAll with itself.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>map.putAll(map).</td></tr>
     * <tr><td><b>Test Description</b></td><td>Checks if putting a map into itself throws exception or succeeds safely.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 elements.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map size unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>No changes to the map.</td></tr>
     * </table>
     */
    @Test
    public void testPutAllSelf() {
        map.put("A", "1");
        map.put("B", "2");
        map.putAll(map);
        assertEquals(2, map.size());
    }

    /**
     * Tests exact return values of put and remove.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests exact return values of put and remove.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Monitors return values for new keys vs existing keys.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Puts a new key (expects null), overwrites (expects old), removes (expects old), removes absent (expects null).</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty map.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Empty map.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Return values match the specification exactly.</td></tr>
     * </table>
     */
    @Test
    public void testPutAndRemoveReturnValues() {
        assertNull(map.put("K1", "V1")); // new key returns null
        assertEquals("V1", map.put("K1", "V2")); // existing key returns old value
        assertEquals("V2", map.remove("K1")); // removing existing returns old value
        assertNull(map.remove("K1")); // removing absent returns null
    }
}
