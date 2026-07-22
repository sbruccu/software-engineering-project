package myTest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import myAdapter.*;

/**
 * Tests for CLDC 1.1 null constraints: null keys and null values are not
 * allowed in MapAdapter (backed by Hashtable).
 * <p>
 * <table border="1">
 * <caption></caption>
 * <tr>
 * <th>Summary</th><td>Tests for CLDC 1.1 null constraints: null keys and null values are not
 * allowed in MapAdapter (backed by Hashtable).</td></tr>
 * <tr><td><b>Test Case Design</b></td>
 * <td>Verifies NullPointerException is thrown for all operations receiving null.</td>
 * </tr>
 * </table>
 */
public class MapAdapterNullConstraintTest {

    private HMap map;

    /**
     * Initializes a fresh empty MapAdapter before each test.
     */
    @Before
    public void setUp() {
        map = new MapAdapter();
    }

    /**
     * Tests NullPointerException for put(null, value).
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests NullPointerException for put(null, value).</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>CLDC 1.1 Hashtable does not allow null keys.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Attempts to put a null key.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map still empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testPutNullKeyThrows() {
        map.put(null, "Value");
    }

    /**
     * Tests NullPointerException for put(key, null).
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests NullPointerException for put(key, null).</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>CLDC 1.1 Hashtable does not allow null values.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Attempts to put a null value.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map still empty.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testPutNullValueThrows() {
        map.put("Key", null);
    }

    /**
     * Tests NullPointerException for get(null).
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests NullPointerException for get(null).</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>CLDC 1.1 constraint: null key not allowed.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Attempts to get with a null key.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testGetNullKeyThrows() {
        map.get(null);
    }

    /**
     * Tests NullPointerException for remove(null).
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests NullPointerException for remove(null).</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>CLDC 1.1 constraint: null key not allowed.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Attempts to remove with a null key.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNullKeyThrows() {
        map.remove(null);
    }

    /**
     * Tests NullPointerException for containsKey(null).
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests NullPointerException for containsKey(null).</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>CLDC 1.1 constraint: null key not allowed.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Attempts containsKey with null.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsKeyNullThrows() {
        map.containsKey(null);
    }

    /**
     * Tests NullPointerException for containsValue(null).
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests NullPointerException for containsValue(null).</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>CLDC 1.1 constraint: null value not allowed.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Attempts containsValue with null.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testContainsValueNullThrows() {
        map.containsValue(null);
    }

    /**
     * Tests NullPointerException for putAll(null).
     * <p>
     * <table border="1">
     * <caption></caption>
     * <tr><th>Summary</th><td>Tests NullPointerException for putAll(null).</td></tr>
     * <tr><td><b>Test Case Design</b></td><td>CLDC 1.1 constraint: null arguments not allowed where a map is expected.</td></tr>
     * <tr><td><b>Test Description</b></td><td>Attempts putAll with null.</td></tr>
     * <tr><td><b>Pre-Condition</b></td><td>Empty MapAdapter.</td></tr>
     * <tr><td><b>Post-Condition</b></td><td>Map unchanged.</td></tr>
     * <tr><td><b>Expected Results</b></td><td>NullPointerException is thrown.</td></tr>
     * </table>
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllNullThrows() {
        map.putAll(null);
    }
}
