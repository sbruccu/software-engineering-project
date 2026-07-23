package myTest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import myAdapter.*;
import java.util.NoSuchElementException;

/**
 * Tests for the entrySet() backing view and the EntryAdapter (HEntry):
 * size, contains, clear, iterator, toArray, add (unsupported), remove,
 * removeAll, retainAll, containsAll, equals, hashCode, and HEntry-specific
 * operations (getKey, getValue, setValue, equals, hashCode).
 * <p>
 * <table border="1">
 * <caption></caption>
 * <tr>
 * <th>Summary</th><td>Tests for the entrySet() backing view and the EntryAdapter (HEntry):
 * size, contains, clear, iterator, toArray, add (unsupported), remove,
 * removeAll, retainAll, containsAll, equals, hashCode, and HEntry-specific
 * operations (getKey, getValue, setValue, equals, hashCode).</td></tr>
 * <tr><td><b>Test Case Design</b></td>
 * <td>Verifies entrySet is a live view backed by the map and HEntry conforms
 * to the J2SE 1.4.2 Map.Entry contract.</td>
 * </tr>
 * </table>
 *
 * @author Andrea Cocurullo, 2147723
 * @version 1.0
 */
public class MapAdapterEntrySetTest {

    private HMap map;

    /**
     * Initializes a fresh empty MapAdapter before each test.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    /**
     * Tests entrySet() size reflects map size.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet() size reflects map size.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>entrySet is a live view of the map's entries.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Adds entries, verifies entrySet.size().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>entrySet.size() equals 2.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetSize() {
        map.put("A", "1");
        map.put("B", "2");
        assertEquals(2, map.entrySet().size());
        assertFalse(map.entrySet().isEmpty());
    }

    /**
     * Tests entrySet.contains() with matching entry.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.contains() with matching entry.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>entrySet.contains checks both key and value match.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets an entry from entrySet, checks contains returns true.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>contains() returns true for matching entry.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetContains() {
        map.put("A", "1");
        HSet entries = map.entrySet();
        HIterator it = entries.iterator();
        Object entry = it.next();
        assertTrue(entries.contains(entry));
    }

    /**
     * Tests entrySet.contains() with non-entry object returns false.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.contains() with non-entry object returns false.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Contains must reject non-HEntry objects.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls contains with a String.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>contains() returns false.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetContainsNonEntry() {
        map.put("A", "1");
        assertFalse(map.entrySet().contains("not an entry"));
    }

    /**
     * Tests entrySet.clear() backs into map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.clear() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Clearing entrySet must clear the backing map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Populates map, clears entrySet, verifies map empty.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with data.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Map size is 0.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetClearBacksMap() {
        map.put("X", "10");
        map.put("Y", "20");
        map.entrySet().clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * Tests entrySet iterator remove backs map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet iterator remove backs map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Iterator.remove() on entrySet must remove entry from map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Iterates entrySet, removes first entry, verifies map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Removed entry's key no longer in map.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetIteratorRemove() {
        map.put("A", "1");
        map.put("B", "2");
        HSet entries = map.entrySet();
        HIterator it = entries.iterator();
        HMap.HEntry entry = (HMap.HEntry) it.next();
        Object removedKey = entry.getKey();
        it.remove();
        assertEquals(1, map.size());
        assertFalse(map.containsKey(removedKey));
    }

    /**
     * Tests entrySet iterator NoSuchElementException.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet iterator NoSuchElementException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Calling next() beyond elements must throw.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Exhausts entrySet iterator then calls next().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NoSuchElementException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NoSuchElementException.class)
    public void testEntrySetIteratorNextThrowsNoSuchElement() {
        map.put("A", "1");
        HIterator it = map.entrySet().iterator();
        it.next();
        it.next(); // should throw
    }

    /**
     * Tests entrySet iterator IllegalStateException on remove before next.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet iterator IllegalStateException on remove before next.</td></tr>
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
     * Tests entrySet.toArray().
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.toArray().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify toArray returns all entries.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Converts entrySet to array, checks length.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Array length is 2.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetToArray() {
        map.put("A", "1");
        map.put("B", "2");
        Object[] arr = map.entrySet().toArray();
        assertEquals(2, arr.length);
        assertTrue(arr[0] instanceof HMap.HEntry);
    }

    /**
     * Tests entrySet.add() throws UnsupportedOperation.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.add() throws UnsupportedOperation.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>entrySet is not modifiable via add.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls add() on entrySet.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>UnsupportedOperationException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testEntrySetAddThrows() {
        map.entrySet().add("X");
    }

    /**
     * Tests entrySet.remove() backs into map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.remove() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Removing an entry from entrySet must remove it from map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets entry, calls entrySet.remove(entry), verifies map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has 1 entry.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>remove() returns true, map size decreases.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetRemoveBacksMap() {
        map.put("A", "1");
        map.put("B", "2");
        HSet entries = map.entrySet();
        HIterator it = entries.iterator();
        Object entry = it.next();
        boolean removed = entries.remove(entry);
        assertTrue(removed);
        assertEquals(1, map.size());
    }

    /**
     * Tests entrySet.removeAll() backs into map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.removeAll() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Bulk remove on entrySet must remove matching entries from map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates another map's entrySet with subset, calls removeAll.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A=1, B=2, C=3.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map only has C=3.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>removeAll returns true, size is 1.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetRemoveAll() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HMap toRemoveMap = new MapAdapter();
        toRemoveMap.put("A", "1");
        toRemoveMap.put("B", "2");
        boolean changed = map.entrySet().removeAll(toRemoveMap.entrySet());
        assertTrue(changed);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("C"));
        assertFalse(map.containsKey("A"));
    }

    /**
     * Tests entrySet.retainAll() backs into map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.retainAll() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>retainAll on entrySet keeps only matching entries.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Retains only entry A=1 from map with A=1, B=2, C=3.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A=1, B=2, C=3.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map only has A=1.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>retainAll returns true, size is 1.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetRetainAll() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        HMap toRetain = new MapAdapter();
        toRetain.put("A", "1");
        boolean changed = map.entrySet().retainAll(toRetain.entrySet());
        assertTrue(changed);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("A"));
    }

    /**
     * Tests entrySet.containsAll().
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.containsAll().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify containsAll for entrySet.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Checks containsAll with subset entries.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with A=1, B=2.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>containsAll returns true for subset.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetContainsAll() {
        map.put("A", "1");
        map.put("B", "2");
        HMap sub = new MapAdapter();
        sub.put("A", "1");
        assertTrue(map.entrySet().containsAll(sub.entrySet()));
    }

    /**
     * Tests entrySet equals and hashCode.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet equals and hashCode.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Two entrySets from equivalent maps must be equal.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates two maps with same entries, compares entrySets.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Two maps with identical entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() true, hashCodes equal.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetEqualsAndHashCode() {
        map.put("A", "1");
        map.put("B", "2");
        HMap map2 = new MapAdapter();
        map2.put("A", "1");
        map2.put("B", "2");
        assertTrue(map.entrySet().equals(map2.entrySet()));
        assertEquals(map.entrySet().hashCode(), map2.entrySet().hashCode());
    }

    /**
     * Tests HEntry getKey() and getValue().
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests HEntry getKey() and getValue().</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Verify entry exposes correct key and value.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets entry from entrySet, checks getKey and getValue.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>getKey and getValue return correct objects.</td></tr>
     * </table>
     */
    @Test
    public void testEntryGetKeyAndGetValue() {
        map.put("K", "V");
        HIterator it = map.entrySet().iterator();
        HMap.HEntry entry = (HMap.HEntry) it.next();
        assertEquals("K", entry.getKey());
        assertEquals("V", entry.getValue());
    }

    /**
     * Tests HEntry setValue() backs into map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests HEntry setValue() backs into map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>setValue must update the backing map and return old value.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets entry, calls setValue, verifies map and return value.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry K=V.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map has K=NewV.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>setValue returns old value, map.get returns new value.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetValue() {
        map.put("K", "V");
        HIterator it = map.entrySet().iterator();
        HMap.HEntry entry = (HMap.HEntry) it.next();
        Object oldVal = entry.setValue("NewV");
        assertEquals("V", oldVal);
        assertEquals("NewV", map.get("K"));
        assertEquals("NewV", entry.getValue());
    }

    /**
     * Tests HEntry setValue(null) throws NullPointerException.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests HEntry setValue(null) throws NullPointerException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>CLDC 1.1 constraint: no null values.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets entry, calls setValue(null).</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testEntrySetValueNullThrows() {
        map.put("K", "V");
        HIterator it = map.entrySet().iterator();
        HMap.HEntry entry = (HMap.HEntry) it.next();
        entry.setValue(null);
    }

    /**
     * Tests HEntry equals() with identical entry.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests HEntry equals() with identical entry.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Two entries with same key and value must be equal.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets entries from two identical maps, compares them.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Two maps with same entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>entries are equal.</td></tr>
     * </table>
     */
    @Test
    public void testEntryEquals() {
        map.put("K", "V");
        HMap map2 = new MapAdapter();
        map2.put("K", "V");

        HMap.HEntry e1 = (HMap.HEntry) map.entrySet().iterator().next();
        HMap.HEntry e2 = (HMap.HEntry) map2.entrySet().iterator().next();

        assertTrue(e1.equals(e2));
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    /**
     * Tests HEntry equals() with different value.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests HEntry equals() with different value.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Entries with same key but different value must not be equal.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets entries from two maps with same key different values.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Two maps with K=V and K=W.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() returns false.</td></tr>
     * </table>
     */
    @Test
    public void testEntryNotEqualsDifferentValue() {
        map.put("K", "V");
        HMap map2 = new MapAdapter();
        map2.put("K", "W");

        HMap.HEntry e1 = (HMap.HEntry) map.entrySet().iterator().next();
        HMap.HEntry e2 = (HMap.HEntry) map2.entrySet().iterator().next();

        assertFalse(e1.equals(e2));
    }

    /**
     * Tests HEntry equals() with non-HEntry object.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests HEntry equals() with non-HEntry object.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Entry must not be equal to a non-HEntry object.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Compares entry with a String.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() returns false.</td></tr>
     * </table>
     */
    @Test
    public void testEntryEqualsNonEntry() {
        map.put("K", "V");
        HMap.HEntry e = (HMap.HEntry) map.entrySet().iterator().next();
        assertFalse(e.equals("not an entry"));
    }

    /**
     * Tests HEntry hashCode follows key^value contract.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests HEntry hashCode follows key^value contract.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>hashCode must be key.hashCode() XOR value.hashCode().</td></tr>
     * <tr><td><b>Test Description</b></td><td>Computes expected hashCode manually and compares.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>hashCode matches key^value.</td></tr>
     * </table>
     */
    @Test
    public void testEntryHashCode() {
        map.put("K", "V");
        HMap.HEntry e = (HMap.HEntry) map.entrySet().iterator().next();
        int expected = "K".hashCode() ^ "V".hashCode();
        assertEquals(expected, e.hashCode());
    }

    /**
     * Tests entrySet.toArray(null) throws NullPointerException.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.toArray(null) throws NullPointerException.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>toArray(null) must throw NullPointerException.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls toArray(null) on entrySet.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testEntrySetToArrayNullThrows() {
        map.entrySet().toArray(null);
    }

    /**
     * Tests HEntry.setValue() propagates change to the backing map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests HEntry.setValue() propagates change to the backing map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>entrySet is a live view: setValue on an entry must update the map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Gets an HEntry from entrySet, calls setValue(), verifies map.get() reflects the new value.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with one entry K=V.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map entry K now has value NEW.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>map.get("K") returns "NEW", old value "V" is returned by setValue.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetValuePropagatestoMap() {
        map.put("K", "V");
        HMap.HEntry e = (HMap.HEntry) map.entrySet().iterator().next();
        Object oldVal = e.setValue("NEW");
        assertEquals("V", oldVal);
        assertEquals("NEW", map.get("K"));
    }

    /**
     * Tests entrySet.toArray(Object[]) with an exactly sized array.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests entrySet.toArray(Object[]) with an exactly sized array.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>toArray with exact size must not allocate a new array.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Passes an array of exact size to toArray().</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Map with 2 entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Returned array is the exact same instance passed in.</td></tr>
     * </table>
     */
    @Test
    public void testEntrySetToArrayExactSize() {
        map.put("A", "1");
        map.put("B", "2");
        Object[] input = new Object[2];
        Object[] output = map.entrySet().toArray(input);
        assertSame(input, output);
    }
}
