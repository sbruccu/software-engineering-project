package myTest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import myAdapter.*;

/**
 * Tests for Map-level equals() and hashCode() contract compliance.
 * <p>
 * <table border="1">
 * <caption></caption>
 * <tr>
 * <th>Summary</th><td>Tests for Map-level equals() and hashCode() contract compliance.</td></tr>
 * <tr><td><b>Test Case Design</b></td>
 * <td>Verifies reflexivity, symmetry, different-content inequality, and
 * hashCode consistency for MapAdapter.</td>
 * </tr>
 * </table>
 */
public class MapAdapterEqualsHashCodeTest {

    private HMap map;

    /**
     * Initializes a fresh empty MapAdapter before each test.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    /**
     * Tests equals() and hashCode() on identical maps.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests equals() and hashCode() on identical maps.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Two maps with same entries must be equal and have same hashCode.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates two maps with identical data, verifies equals and hashCode.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Two MapAdapters with identical data.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() true, hashCodes equal.</td></tr>
     * </table>
     */
    @Test
    public void testEqualsAndHashCodeIdentical() {
        map.put("A", "1");
        map.put("B", "2");

        HMap map2 = new MapAdapter();
        map2.put("A", "1");
        map2.put("B", "2");

        assertTrue(map.equals(map2));
        assertTrue(map2.equals(map));
        assertEquals(map.hashCode(), map2.hashCode());
    }

    /**
     * Tests equals() returns false for different maps.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests equals() returns false for different maps.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Maps with different entries must not be equal.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates two maps with different data, verifies not equal.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Two MapAdapters with different data.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() returns false.</td></tr>
     * </table>
     */
    @Test
    public void testEqualsDifferentMaps() {
        map.put("A", "1");
        HMap map2 = new MapAdapter();
        map2.put("A", "2");
        assertFalse(map.equals(map2));
    }

    /**
     * Tests equals() returns false for different sizes.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests equals() returns false for different sizes.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Maps with different sizes must not be equal.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Creates two maps with different number of entries.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Two MapAdapters with different sizes.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() returns false.</td></tr>
     * </table>
     */
    @Test
    public void testEqualsDifferentSize() {
        map.put("A", "1");
        HMap map2 = new MapAdapter();
        map2.put("A", "1");
        map2.put("B", "2");
        assertFalse(map.equals(map2));
    }

    /**
     * Tests equals() reflexivity.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests equals() reflexivity.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>A map must be equal to itself.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls equals(this) on the map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>MapAdapter with data.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() returns true.</td></tr>
     * </table>
     */
    @Test
    public void testEqualsReflexive() {
        map.put("A", "1");
        assertTrue(map.equals(map));
    }

    /**
     * Tests equals() with non-Map object.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests equals() with non-Map object.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>A map must not be equal to a non-HMap object.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls equals with a String.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>MapAdapter with data.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() returns false.</td></tr>
     * </table>
     */
    @Test
    public void testEqualsWithNonMapObject() {
        map.put("A", "1");
        assertFalse(map.equals("not a map"));
    }

    /**
     * Tests two empty maps are equal.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests two empty maps are equal.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Boundary: two empty maps must be equal.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Compares two empty MapAdapters.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Two empty MapAdapters.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals() returns true, hashCodes equal.</td></tr>
     * </table>
     */
    @Test
    public void testEqualsEmptyMaps() {
        HMap map2 = new MapAdapter();
        assertTrue(map.equals(map2));
        assertEquals(map.hashCode(), map2.hashCode());
    }

    /**
     * Tests hashCode() is 0 for an empty map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests hashCode() is 0 for an empty map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>Sum of entry hashCodes for empty map is 0.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Checks hashCode on empty map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>None.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>hashCode() returns 0.</td></tr>
     * </table>
     */
    @Test
    public void testHashCodeEmptyMap() {
        assertEquals(0, map.hashCode());
    }

    /**
     * Tests equals(null) returns false without throwing.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests equals(null) returns false without throwing.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>equals(null) must return false per the Map contract.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls map.equals(null) on a populated map.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>MapAdapter with one entry.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>equals(null) returns false.</td></tr>
     * </table>
     */
    @Test
    public void testEqualsNull() {
        map.put("A", "1");
        assertFalse(map.equals(null));
    }

    /**
     * Tests hashCode() is consistent across multiple calls on the same map.
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests hashCode() is consistent across multiple calls on the same map.</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>hashCode must return the same value each time it is called on an unchanged map.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Calls hashCode() twice on the same map, compares results.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>MapAdapter with entries.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>Both calls return the same value.</td></tr>
     * </table>
     */
    @Test
    public void testHashCodeConsistency() {
        map.put("A", "1");
        map.put("B", "2");
        assertEquals(map.hashCode(), map.hashCode());
    }
}
