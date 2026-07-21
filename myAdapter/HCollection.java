package myAdapter;

/**
 * The root interface in the collection hierarchy. A collection represents
 * a group of objects, known as its elements.
 * This interface is the local equivalent of {@code java.util.Collection}
 * from J2SE 1.4.2, defined to avoid collisions with the current Java version.
 *
 * @author Andrea Cocurullo, 2147723
 * @version 1.0
 */
public interface HCollection {
    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements in this collection.
     */
    int size();

    /**
     * Returns {@code true} if this collection contains no elements.
     *
     * @return {@code true} if this collection contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if this collection contains the specified element.
     *
     * @param o element whose presence in this collection is to be tested.
     * @return {@code true} if this collection contains the specified element.
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over the elements in this collection.
     *
     * @return an iterator over the elements in this collection.
     */
    HIterator iterator();

    /**
     * Returns an array containing all of the elements in this collection.
     *
     * @return an array containing all of the elements in this collection.
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this collection;
     * the runtime type of the returned array is that of the specified array.
     *
     * @param a the array into which the elements of this collection are to be
     *          stored, if it is big enough; otherwise, a new array is allocated.
     * @return an array containing the elements of this collection.
     */
    Object[] toArray(Object[] a);

    /**
     * Ensures that this collection contains the specified element (optional
     * operation).
     *
     * @param o element whose presence in this collection is to be ensured.
     * @return {@code true} if this collection changed as a result of the call.
     * @throws UnsupportedOperationException if the add operation is not
     *         supported by this collection.
     */
    boolean add(Object o);

    /**
     * Removes a single instance of the specified element from this collection,
     * if it is present (optional operation).
     *
     * @param o element to be removed from this collection, if present.
     * @return {@code true} if the collection contained the specified element.
     */
    boolean remove(Object o);

    /**
     * Returns {@code true} if this collection contains all of the elements
     * in the specified collection.
     *
     * @param c collection to be checked for containment in this collection.
     * @return {@code true} if this collection contains all of the elements
     *         in the specified collection.
     */
    boolean containsAll(HCollection c);

    /**
     * Adds all of the elements in the specified collection to this collection
     * (optional operation).
     *
     * @param c elements to be added to this collection.
     * @return {@code true} if this collection changed as a result of the call.
     * @throws UnsupportedOperationException if the addAll operation is not
     *         supported by this collection.
     */
    boolean addAll(HCollection c);

    /**
     * Removes all of this collection's elements that are also contained in
     * the specified collection (optional operation).
     *
     * @param c elements to be removed from this collection.
     * @return {@code true} if this collection changed as a result of the call.
     */
    boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation).
     *
     * @param c elements to be retained in this collection.
     * @return {@code true} if this collection changed as a result of the call.
     */
    boolean retainAll(HCollection c);

    /**
     * Removes all of the elements from this collection (optional operation).
     */
    void clear();

    /**
     * Compares the specified object with this collection for equality.
     *
     * @param o object to be compared for equality with this collection.
     * @return {@code true} if the specified object is equal to this collection.
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.
     *
     * @return the hash code value for this collection.
     */
    int hashCode();
}