package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * Adapter implementation of the {@link HMap} interface (J2SE 1.4.2 equivalent)
 * using CLDC 1.1 {@code java.util.Hashtable} as the adaptee.
 * This class provides a full implementation of the Map interface as specified
 * in J2SE 1.4.2, including all optional operations. The backing data structure
 * is a CLDC 1.1 Hashtable, which does not permit null keys or null values.
 * 
 * @author Andrea Cocurullo, 2147723
 * @version 1.0
 *
 * @see HMap
 * @see HCollection
 * @see HSet
 */
public class MapAdapter implements HMap {

    private Hashtable adaptee;

    /**
     * Constructs an empty MapAdapter.
     */
    public MapAdapter() {
        this.adaptee = new Hashtable();
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map.
     */
    public int size() {
        return adaptee.size();
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings.
     */
    public boolean isEmpty() {
        return adaptee.isEmpty();
    }

    /**
     * Returns {@code true} if this map contains a mapping for the specified key.
     *
     * @param key key whose presence in this map is to be tested.
     * @return {@code true} if this map contains a mapping for the specified key.
     * @throws NullPointerException if the key is {@code null}.
     */
    public boolean containsKey(Object key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        return adaptee.containsKey(key);
    }

    /**
     * Returns {@code true} if this map maps one or more keys to the specified value.
     *
     * @param value value whose presence in this map is to be tested.
     * @return {@code true} if this map maps one or more keys to the specified value.
     * @throws NullPointerException if the value is {@code null}.
     */
    public boolean containsValue(Object value) {
        if (value == null)
            throw new NullPointerException("Value cannot be null");
        return adaptee.contains(value);
    }

    /**
     * Returns the value to which this map maps the specified key, or {@code null}
     * if the map contains no mapping for this key.
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or {@code null}
     *         if the map contains no mapping for this key.
     * @throws NullPointerException if the key is {@code null}.
     */
    public Object get(Object key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        return adaptee.get(key);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for this key, the old value
     * is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or {@code null}
     *         if there was no mapping for key.
     * @throws NullPointerException if the key or value is {@code null}.
     */
    public Object put(Object key, Object value) {
        if (key == null || value == null)
            throw new NullPointerException("Key and Value cannot be null");
        return adaptee.put(key, value);
    }

    /**
     * Removes the mapping for this key from this map if it is present.
     *
     * @param key key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or {@code null}
     *         if there was no mapping for key.
     * @throws NullPointerException if the key is {@code null}.
     */
    public Object remove(Object key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        return adaptee.remove(key);
    }

    /**
     * Copies all of the mappings from the specified map to this map. The effect
     * of this call is equivalent to that of calling {@link #put(Object, Object)}
     * on this map once for each mapping in the specified map.
     *
     * @param t mappings to be stored in this map.
     * @throws NullPointerException if the specified map is {@code null}.
     */
    public void putAll(HMap t) {
        if (t == null)
            throw new NullPointerException("Map cannot be null");
        HIterator iter = t.entrySet().iterator();
        while (iter.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iter.next();
            this.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Removes all mappings from this map.
     */
    public void clear() {
        adaptee.clear();
    }

    /**
     * Returns a {@link HSet} view of the keys contained in this map. The set
     * is backed by the map, so changes to the map are reflected in the set,
     * and vice-versa. The set supports element removal, which removes the
     * corresponding mapping from the map.
     *
     * @return a set view of the keys contained in this map.
     */
    public HSet keySet() {
        return new KeySet();
    }

    /**
     * Returns a {@link HCollection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are reflected
     * in the collection, and vice-versa. The collection supports element removal,
     * which removes the corresponding mapping from the map.
     *
     * @return a collection view of the values contained in this map.
     */
    public HCollection values() {
        return new ValueCollection();
    }

    /**
     * Returns a {@link HSet} view of the mappings contained in this map. Each
     * element in the returned set is a {@link HMap.HEntry}. The set is backed
     * by the map, so changes to the map are reflected in the set, and
     * vice-versa. The set supports element removal, which removes the
     * corresponding mapping from the map.
     *
     * @return a set view of the mappings contained in this map.
     */
    public HSet entrySet() {
        return new EntrySet();
    }

    /**
     * Compares the specified object with this map for equality. Returns
     * {@code true} if the given object is also a map and the two maps
     * represent the same mappings.
     *
     * @param o object to be compared for equality with this map.
     * @return {@code true} if the specified object is equal to this map.
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof HMap))
            return false;
        HMap m = (HMap) o;
        if (m.size() != size())
            return false;
        try {
            HIterator i = entrySet().iterator();
            while (i.hasNext()) {
                HEntry e = (HEntry) i.next();
                Object key = e.getKey();
                Object value = e.getValue();
                if (value == null) {
                    if (!(m.get(key) == null && m.containsKey(key)))
                        return false;
                } else {
                    if (!value.equals(m.get(key)))
                        return false;
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
        return true;
    }

    /**
     * Returns the hash code value for this map. The hash code of a map is
     * defined to be the sum of the hash codes of each entry in the map's
     * entrySet view.
     *
     * @return the hash code value for this map.
     */
    public int hashCode() {
        int h = 0;
        HIterator i = entrySet().iterator();
        while (i.hasNext()) {
            h += i.next().hashCode();
        }
        return h;
    }

    // --- Inner classes for backing views ---

    /**
     * Inner class implementing a Set view of the map's keys. All operations
     * are backed by the enclosing MapAdapter instance.
     */
    private class KeySet implements HSet {

        /**
         * Returns the number of keys in this set.
         *
         * @return the number of keys in this set.
         */
        public int size() {
            return MapAdapter.this.size();
        }

        /**
         * Returns {@code true} if this set contains no keys.
         *
         * @return {@code true} if this set contains no keys.
         */
        public boolean isEmpty() {
            return MapAdapter.this.isEmpty();
        }

        /**
         * Returns {@code true} if this set contains the specified key.
         *
         * @param o element whose presence in this set is to be tested.
         * @return {@code true} if this set contains the specified element.
         */
        public boolean contains(Object o) {
            return MapAdapter.this.containsKey(o);
        }

        /**
         * Removes all of the elements from this set and consequently
         * removes all mappings from the backing map.
         */
        public void clear() {
            MapAdapter.this.clear();
        }

        /**
         * Returns an iterator over the keys in this set.
         *
         * @return an iterator over the keys in this set.
         */
        public HIterator iterator() {
            return new HIterator() {
                // Hashtable Enumeration used to traverse the keys
                private Enumeration keys = adaptee.keys();
                // Keeps track of the last key returned by next() so it can be removed
                private Object lastReturned = null;

                public boolean hasNext() {
                    return keys.hasMoreElements();
                }

                public Object next() {
                    if (!hasNext())
                        throw new NoSuchElementException();
                    
                    // Advances the Enumeration, saves the key in lastReturned, and returns it
                    lastReturned = keys.nextElement();
                    return lastReturned;
                }

                public void remove() {
                    // The Iterator requires that next() has been called before,
                    // and that remove() hasn't been called more than once for the same element
                    if (lastReturned == null)
                        throw new IllegalStateException();
                    
                    // Removes the key (and its associated value) from the main map
                    MapAdapter.this.remove(lastReturned);
                    
                    // Resets the state to prevent multiple removals of the same element
                    lastReturned = null;
                }
            };
        }

        /**
         * Returns an array containing all of the keys in this set.
         *
         * @return an array containing all of the keys in this set.
         */
        public Object[] toArray() {
            Object[] r = new Object[size()];
            HIterator it = iterator();
            for (int i = 0; i < r.length; i++)
                r[i] = it.next();
            return r;
        }

        /**
         * Returns an array containing all of the keys in this set; the
         * runtime type of the returned array is that of the specified array.
         *
         * @param a the array into which the elements of this set are to be
         *          stored, if it is big enough; otherwise, a new array of
         *          the same runtime type is allocated for this purpose.
         * @return an array containing the elements of this set.
         */
        public Object[] toArray(Object[] a) {
            int size = size();
            if (a.length < size) {
                a = new Object[size];
            }
            HIterator it = iterator();
            for (int i = 0; i < size; i++)
                a[i] = it.next();
            if (a.length > size)
                a[size] = null;
            return a;
        }

        /**
         * This operation is not supported by a key set view.
         *
         * @param o element to be added.
         * @return nothing, always throws.
         * @throws UnsupportedOperationException always.
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }

        /**
         * This operation is not supported by a key set view.
         *
         * @param c collection whose elements are to be added.
         * @return nothing, always throws.
         * @throws UnsupportedOperationException always.
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }

        /**
         * Removes the specified key from this set and the corresponding
         * mapping from the backing map.
         *
         * @param o key to be removed from this set.
         * @return {@code true} if the set contained the specified key.
         */
        public boolean remove(Object o) {
            if (o == null)
                return false;
            if (MapAdapter.this.containsKey(o)) {
                MapAdapter.this.remove(o);
                return true;
            }
            return false;
        }

        /**
         * Returns {@code true} if this set contains all of the elements
         * of the specified collection.
         *
         * @param c collection to be checked for containment in this set.
         * @return {@code true} if this set contains all of the elements.
         */
        public boolean containsAll(HCollection c) {
            HIterator it = c.iterator();
            while (it.hasNext())
                if (!contains(it.next()))
                    return false;
            return true;
        }

        /**
         * Removes from this set all of its elements that are contained in the
         * specified collection. The corresponding mappings are removed from
         * the backing map.
         *
         * @param c collection containing elements to be removed from this set.
         * @return {@code true} if this set changed as a result of the call.
         */
        public boolean removeAll(HCollection c) {
            boolean modified = false;
            HIterator it = iterator();
            while (it.hasNext()) {
                if (c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
            return modified;
        }

        /**
         * Retains only the elements in this set that are contained in the
         * specified collection. Elements not in the specified collection are
         * removed from the backing map.
         *
         * @param c collection containing elements to be retained in this set.
         * @return {@code true} if this set changed as a result of the call.
         */
        public boolean retainAll(HCollection c) {
            boolean modified = false;
            HIterator it = iterator();
            while (it.hasNext()) {
                if (!c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
            return modified;
        }

        /**
         * Compares the specified object with this set for equality.
         *
         * @param o object to be compared for equality with this set.
         * @return {@code true} if the specified object is equal to this set.
         */
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof HSet))
                return false;
            HSet c = (HSet) o;
            if (c.size() != size())
                return false;
            return containsAll(c);
        }

        /**
         * Returns the hash code value for this set.
         *
         * @return the hash code value for this set.
         */
        public int hashCode() {
            int h = 0;
            HIterator i = iterator();
            while (i.hasNext()) {
                Object obj = i.next();
                if (obj != null)
                    h += obj.hashCode();
            }
            return h;
        }
    }

    /**
     * Inner class implementing a Collection view of the map's values.
     */
    private class ValueCollection implements HCollection {

        /**
         * Returns the number of values in this collection.
         *
         * @return the number of values in this collection.
         */
        public int size() {
            return MapAdapter.this.size();
        }

        /**
         * Returns {@code true} if this collection contains no values.
         *
         * @return {@code true} if this collection contains no values.
         */
        public boolean isEmpty() {
            return MapAdapter.this.isEmpty();
        }

        /**
         * Returns {@code true} if this collection contains the specified value.
         *
         * @param o element whose presence in this collection is to be tested.
         * @return {@code true} if this collection contains the specified element.
         */
        public boolean contains(Object o) {
            return MapAdapter.this.containsValue(o);
        }

        /**
         * Removes all of the elements from this collection and consequently
         * removes all mappings from the backing map.
         */
        public void clear() {
            MapAdapter.this.clear();
        }

        /**
         * Returns an iterator over the values in this collection.
         *
         * @return an iterator over the values in this collection.
         */
        public HIterator iterator() {
            return new HIterator() {
                private Enumeration keys = adaptee.keys();
                private Object lastReturnedKey = null;

                public boolean hasNext() {
                    return keys.hasMoreElements();
                }

                public Object next() {
                    if (!hasNext())
                        throw new NoSuchElementException();
                    lastReturnedKey = keys.nextElement();
                    return adaptee.get(lastReturnedKey);
                }

                public void remove() {
                    if (lastReturnedKey == null)
                        throw new IllegalStateException();
                    MapAdapter.this.remove(lastReturnedKey);
                    lastReturnedKey = null;
                }
            };
        }

        /**
         * Returns an array containing all of the values in this collection.
         *
         * @return an array containing all of the values in this collection.
         */
        public Object[] toArray() {
            Object[] r = new Object[size()];
            HIterator it = iterator();
            for (int i = 0; i < r.length; i++)
                r[i] = it.next();
            return r;
        }

        /**
         * Returns an array containing all of the values in this collection;
         * the runtime type of the returned array is that of the specified array.
         *
         * @param a the array into which the elements are to be stored, if it
         *          is big enough; otherwise, a new array is allocated.
         * @return an array containing the elements of this collection.
         */
        public Object[] toArray(Object[] a) {
            int size = size();
            if (a.length < size)
                a = new Object[size];
            HIterator it = iterator();
            for (int i = 0; i < size; i++)
                a[i] = it.next();
            if (a.length > size)
                a[size] = null;
            return a;
        }

        /**
         * This operation is not supported by a values collection view.
         *
         * @param o element to be added.
         * @return nothing, always throws.
         * @throws UnsupportedOperationException always.
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }

        /**
         * This operation is not supported by a values collection view.
         *
         * @param c collection whose elements are to be added.
         * @return nothing, always throws.
         * @throws UnsupportedOperationException always.
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }

        /**
         * Removes a single instance of the specified element from this
         * collection, if it is present. Removes the corresponding mapping
         * from the backing map.
         *
         * @param o element to be removed from this collection.
         * @return {@code true} if the collection contained the specified element.
         */
        public boolean remove(Object o) {
            HIterator it = iterator();
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns {@code true} if this collection contains all of the elements
         * in the specified collection.
         *
         * @param c collection to be checked for containment.
         * @return {@code true} if this collection contains all of the elements.
         */
        public boolean containsAll(HCollection c) {
            HIterator it = c.iterator();
            while (it.hasNext())
                if (!contains(it.next()))
                    return false;
            return true;
        }

        /**
         * Removes all of this collection's elements that are also contained
         * in the specified collection. Removes the corresponding mappings
         * from the backing map.
         *
         * @param c collection containing elements to be removed.
         * @return {@code true} if this collection changed as a result.
         */
        public boolean removeAll(HCollection c) {
            boolean modified = false;
            HIterator it = iterator();
            while (it.hasNext()) {
                if (c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
            return modified;
        }

        /**
         * Retains only the elements in this collection that are contained
         * in the specified collection. Removes the corresponding mappings
         * from the backing map for elements not retained.
         *
         * @param c collection containing elements to be retained.
         * @return {@code true} if this collection changed as a result.
         */
        public boolean retainAll(HCollection c) {
            boolean modified = false;
            HIterator it = iterator();
            while (it.hasNext()) {
                if (!c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
            return modified;
        }
    }

    /**
     * Inner class implementing a Set view of the map's entries. Each element
     * is a {@link HMap.HEntry}. 
     */
    private class EntrySet implements HSet {

        /**
         * Returns the number of entries in this set.
         *
         * @return the number of entries in this set.
         */
        public int size() {
            return MapAdapter.this.size();
        }

        /**
         * Returns {@code true} if this set contains no entries.
         *
         * @return {@code true} if this set contains no entries.
         */
        public boolean isEmpty() {
            return MapAdapter.this.isEmpty();
        }

        /**
         * Removes all of the entries from this set and consequently
         * removes all mappings from the backing map.
         */
        public void clear() {
            MapAdapter.this.clear();
        }

        /**
         * Returns {@code true} if this set contains the specified entry.
         * The specified object must be an {@link HMap.HEntry} with a key
         * present in the map and a matching value.
         *
         * @param o element whose presence in this set is to be tested.
         * @return {@code true} if this set contains the specified element.
         */
        public boolean contains(Object o) {
            if (!(o instanceof HEntry))
                return false;
            HEntry e = (HEntry) o;
            Object k = e.getKey();
            if (!MapAdapter.this.containsKey(k))
                return false;
            Object v = MapAdapter.this.get(k);
            return v.equals(e.getValue());
        }

        /**
         * Returns an iterator over the entries in this set. Each element
         * returned by the iterator is a {@link HMap.HEntry}.
         *
         * @return an iterator over the entries in this set.
         */
        public HIterator iterator() {
            return new HIterator() {
                private Enumeration keys = adaptee.keys();
                private Object lastReturnedKey = null;

                public boolean hasNext() {
                    return keys.hasMoreElements();
                }

                public Object next() {
                    if (!hasNext())
                        throw new NoSuchElementException();
                    lastReturnedKey = keys.nextElement();
                    return new EntryAdapter(lastReturnedKey);
                }

                public void remove() {
                    if (lastReturnedKey == null)
                        throw new IllegalStateException();
                    MapAdapter.this.remove(lastReturnedKey);
                    lastReturnedKey = null;
                }
            };
        }

        /**
         * Returns an array containing all of the entries in this set.
         *
         * @return an array containing all of the entries in this set.
         */
        public Object[] toArray() {
            Object[] r = new Object[size()];
            HIterator it = iterator();
            for (int i = 0; i < r.length; i++)
                r[i] = it.next();
            return r;
        }

        /**
         * Returns an array containing all of the entries in this set;
         * the runtime type of the returned array is that of the specified array.
         *
         * @param a the array into which the elements are to be stored, if it
         *          is big enough; otherwise, a new array is allocated.
         * @return an array containing the elements of this set.
         */
        public Object[] toArray(Object[] a) {
            int size = size();
            if (a.length < size)
                a = new Object[size];
            HIterator it = iterator();
            for (int i = 0; i < size; i++)
                a[i] = it.next();
            if (a.length > size)
                a[size] = null;
            return a;
        }

        /**
         * This operation is not supported by an entry set view.
         *
         * @param o element to be added.
         * @return nothing, always throws.
         * @throws UnsupportedOperationException always.
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }

        /**
         * This operation is not supported by an entry set view.
         *
         * @param c collection whose elements are to be added.
         * @return nothing, always throws.
         * @throws UnsupportedOperationException always.
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }

        /**
         * Removes the specified entry from this set and the corresponding
         * mapping from the backing map.
         *
         * @param o entry to be removed from this set.
         * @return {@code true} if the set contained the specified entry.
         */
        public boolean remove(Object o) {
            if (contains(o)) {
                HEntry e = (HEntry) o;
                MapAdapter.this.remove(e.getKey());
                return true;
            }
            return false;
        }

        /**
         * Returns {@code true} if this set contains all of the elements
         * of the specified collection.
         *
         * @param c collection to be checked for containment in this set.
         * @return {@code true} if this set contains all of the elements.
         */
        public boolean containsAll(HCollection c) {
            HIterator it = c.iterator();
            while (it.hasNext())
                if (!contains(it.next()))
                    return false;
            return true;
        }

        /**
         * Removes from this set all of its elements that are contained in
         * the specified collection. The corresponding mappings are removed
         * from the backing map.
         *
         * @param c collection containing elements to be removed.
         * @return {@code true} if this set changed as a result of the call.
         */
        public boolean removeAll(HCollection c) {
            boolean modified = false;
            HIterator it = iterator();
            while (it.hasNext()) {
                if (c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
            return modified;
        }

        /**
         * Retains only the elements in this set that are contained in the
         * specified collection. Entries not in the specified collection are
         * removed from the backing map.
         *
         * @param c collection containing elements to be retained.
         * @return {@code true} if this set changed as a result of the call.
         */
        public boolean retainAll(HCollection c) {
            boolean modified = false;
            HIterator it = iterator();
            while (it.hasNext()) {
                if (!c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
            return modified;
        }

        /**
         * Compares the specified object with this set for equality.
         *
         * @param o object to be compared for equality with this set.
         * @return {@code true} if the specified object is equal to this set.
         */
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof HSet))
                return false;
            HSet c = (HSet) o;
            if (c.size() != size())
                return false;
            return containsAll(c);
        }

        /**
         * Returns the hash code value for this set.
         *
         * @return the hash code value for this set.
         */
        public int hashCode() {
            int h = 0;
            HIterator i = iterator();
            while (i.hasNext()) {
                h += i.next().hashCode();
            }
            return h;
        }
    }

    /**
     * Inner class implementing the {@link HMap.HEntry} interface. Each
     * EntryAdapter stores a reference to a key and delegates value operations
     * to the enclosing MapAdapter.
     */
    private class EntryAdapter implements HEntry {
        private Object key;

        /**
         * Constructs an EntryAdapter for the specified key.
         *
         * @param key the key for this entry.
         */
        public EntryAdapter(Object key) {
            this.key = key;
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry.
         */
        public Object getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry, obtained from the
         * backing map.
         *
         * @return the value corresponding to this entry.
         */
        public Object getValue() {
            return MapAdapter.this.get(key);
        }

        /**
         * Replaces the value corresponding to this entry with the specified
         * value. Writes through to the backing map.
         *
         * @param value new value to be stored in this entry.
         * @return old value corresponding to the entry.
         * @throws NullPointerException if the value is {@code null}.
         */
        public Object setValue(Object value) {
            if (value == null)
                throw new NullPointerException();
            return MapAdapter.this.put(key, value);
        }

        /**
         * Compares the specified object with this entry for equality.
         * Returns {@code true} if the given object is also a map entry and
         * the two entries have the same key and value.
         *
         * @param o object to be compared for equality with this map entry.
         * @return {@code true} if the specified object is equal to this entry.
         */
        public boolean equals(Object o) {
            if (!(o instanceof HEntry))
                return false;
            HEntry e = (HEntry) o;
            return key.equals(e.getKey()) && getValue().equals(e.getValue());
        }

        /**
         * Returns the hash code value for this map entry. The hash code
         * is defined as {@code key.hashCode() ^ value.hashCode()}.
         *
         * @return the hash code value for this map entry.
         */
        public int hashCode() {
            return key.hashCode() ^ getValue().hashCode();
        }
    }
}