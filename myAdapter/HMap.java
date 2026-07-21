package myAdapter;

/**
 * An object that maps keys to values. A map cannot contain duplicate keys;
 * each key can map to at most one value.
 * This interface is the local equivalent of {@code java.util.Map} from
 * J2SE 1.4.2, defined to avoid collisions with the current Java version.
 * 
 * @author Andrea Cocurullo, 2147723
 * @version 1.0
 */
public interface HMap {
    /**
     * A map entry (key-value pair). Represents a single mapping contained
     * in an {@code HMap}.
     *
     * <p>Objects of this type are obtained exclusively by iterating over the
     * set returned by {@link HMap#entrySet()}. Each {@code HEntry} provides
     * direct access to both the key and the value of the mapping, allowing
     * efficient traversal of the map without the need to call
     * {@link HMap#get(Object)} for each key.</p>
     *
     * <p>The {@link #setValue(Object)} method is the only safe way to modify
     * a value <i>during iteration</i>: it writes through directly to the
     * backing map and returns the previous value.</p>
     *
     * <p>Two entries are considered equal if their keys are equal and their
     * values are equal. The hash code of an entry is defined as
     * {@code key.hashCode() ^ value.hashCode()}, and the hash code of the
     * map itself is the sum of the hash codes of all its entries.</p>
     */
    interface HEntry {

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry.
         */
        Object getKey();

        /**
         * Returns the value corresponding to this entry.
         *
         * @return the value corresponding to this entry.
         */
        Object getValue();

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation). Writes through to the underlying map.
         *
         * @param value new value to be stored in this entry.
         * @return old value corresponding to the entry.
         * @throws NullPointerException if the specified value is null.
         */
        Object setValue(Object value);

        /**
         * Compares the specified object with this entry for equality.
         *
         * @param o object to be compared for equality with this map entry.
         * @return {@code true} if the specified object is equal to this map entry.
         */
        boolean equals(Object o);

        /**
         * Returns the hash code value for this map entry.
         *
         * @return the hash code value for this map entry.
         */
        int hashCode();
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map.
     */
    int size();

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings.
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if this map contains a mapping for the specified key.
     *
     * @param key key whose presence in this map is to be tested.
     * @return {@code true} if this map contains a mapping for the specified key.
     * @throws NullPointerException if the key is null.
     */
    boolean containsKey(Object key);

    /**
     * Returns {@code true} if this map maps one or more keys to the specified value.
     *
     * @param value value whose presence in this map is to be tested.
     * @return {@code true} if this map maps one or more keys to the specified value.
     * @throws NullPointerException if the value is null.
     */
    boolean containsValue(Object value);

    /**
     * Returns the value to which this map maps the specified key, or {@code null}
     * if the map contains no mapping for this key.
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or {@code null}
     *         if the map contains no mapping for this key.
     * @throws NullPointerException if the key is null.
     */
    Object get(Object key);

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).
     *
     * @param key   key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or {@code null}
     *         if there was no mapping for key.
     * @throws NullPointerException if the key or value is null.
     */
    Object put(Object key, Object value);

    /**
     * Removes the mapping for this key from this map if it is present
     * (optional operation).
     *
     * @param key key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or {@code null}
     *         if there was no mapping for key.
     * @throws NullPointerException if the key is null.
     */
    Object remove(Object key);

    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).
     *
     * @param t mappings to be stored in this map.
     * @throws NullPointerException if the specified map is null.
     */
    void putAll(HMap t);

    /**
     * Removes all mappings from this map (optional operation).
     */
    void clear();

    /**
     * Returns a set view of the keys contained in this map. The set is
     * backed by the map, so changes to the map are reflected in the set,
     * and vice-versa.
     *
     * @return a set view of the keys contained in this map.
     */
    HSet keySet();

    /**
     * Returns a collection view of the values contained in this map. The
     * collection is backed by the map, so changes to the map are reflected
     * in the collection, and vice-versa.
     *
     * @return a collection view of the values contained in this map.
     */
    HCollection values();

    /**
     * Returns a set view of the mappings contained in this map. Each element
     * in the returned set is a {@link HMap.HEntry}. The set is backed by the
     * map, so changes to the map are reflected in the set, and vice-versa.
     *
     * @return a set view of the mappings contained in this map.
     */
    HSet entrySet();

    /**
     * Compares the specified object with this map for equality.
     *
     * @param o object to be compared for equality with this map.
     * @return {@code true} if the specified object is equal to this map.
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this map.
     *
     * @return the hash code value for this map.
     */
    int hashCode();
}