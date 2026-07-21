package myAdapter;

/**
 * An iterator over a collection. Iterator takes the place of Enumeration
 * in the Java Collections Framework.
 * This interface is the local equivalent of {@code java.util.Iterator} from
 * J2SE 1.4.2, defined to avoid collisions with the current Java version.
 *
 * @author Andrea Cocurullo, 2147723
 * @version 1.0
 */
public interface HIterator {
    /**
     * Returns {@code true} if the iteration has more elements.
     *
     * @return {@code true} if the iterator has more elements.
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @throws java.util.NoSuchElementException if the iteration has no more elements.
     */
    Object next();

    /**
     * Removes from the underlying collection the last element returned by
     * the iterator (optional operation). This method can be called only once
     * per call to {@link #next}.
     *
     * @throws IllegalStateException if the {@code next} method has not yet
     *         been called, or the {@code remove} method has already been
     *         called after the last call to the {@code next} method.
     */
    void remove();
}