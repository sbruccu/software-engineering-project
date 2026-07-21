package myAdapter;

/**
 * A collection that contains no duplicate elements. More formally, sets
 * contain no pair of elements {@code e1} and {@code e2} such that
 * {@code e1.equals(e2)}, and at most one null element.
 * This interface is the local equivalent of {@code java.util.Set} from
 * J2SE 1.4.2, defined to avoid collisions with the current Java version.
 * 
 * @author Andrea Cocurullo, 2147723
 * @version 1.0
 */
public interface HSet extends HCollection {
    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set.
     */
    int size();

    /**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if this set contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if this set contains the specified element.
     *
     * @param o element whose presence in this set is to be tested.
     * @return {@code true} if this set contains the specified element.
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over the elements in this set.
     *
     * @return an iterator over the elements in this set.
     */
    HIterator iterator();

    /**
     * Returns an array containing all of the elements in this set.
     *
     * @return an array containing all of the elements in this set.
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     *
     * @param a the array into which the elements of this set are to be
     *          stored, if it is big enough; otherwise, a new array is allocated.
     * @return an array containing the elements of this set.
     */
    Object[] toArray(Object[] a);

    /**
     * Adds the specified element to this set if it is not already present
     * (optional operation).
     *
     * @param o element to be added to this set.
     * @return {@code true} if this set did not already contain the element.
     * @throws UnsupportedOperationException if the add operation is not
     *         supported by this set.
     */
    boolean add(Object o);

    /**
     * Removes the specified element from this set if it is present
     * (optional operation).
     *
     * @param o object to be removed from this set, if present.
     * @return {@code true} if this set contained the specified element.
     */
    boolean remove(Object o);

    /**
     * Returns {@code true} if this set contains all of the elements of the
     * specified collection.
     *
     * @param c collection to be checked for containment in this set.
     * @return {@code true} if this set contains all of the elements of the
     *         specified collection.
     */
    boolean containsAll(HCollection c);

    /**
     * Adds all of the elements in the specified collection to this set if
     * they're not already present (optional operation).
     *
     * @param c collection whose elements are to be added to this set.
     * @return {@code true} if this set changed as a result of the call.
     * @throws UnsupportedOperationException if the addAll operation is not
     *         supported by this set.
     */
    boolean addAll(HCollection c);

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * @param c collection that defines which elements will be removed.
     * @return {@code true} if this set changed as a result of the call.
     */
    boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this set that are contained in the
     * specified collection (optional operation).
     *
     * @param c collection that defines which elements this set will retain.
     * @return {@code true} if this set changed as a result of the call.
     */
    boolean retainAll(HCollection c);

    /**
     * Removes all of the elements from this set (optional operation).
     */
    void clear();

    /**
     * Compares the specified object with this set for equality.
     *
     * @param o object to be compared for equality with this set.
     * @return {@code true} if the specified object is equal to this set.
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this set.
     *
     * @return the hash code value for this set.
     */
    int hashCode();
}