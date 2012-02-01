package util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Circular overwriting array.
 * Contains a fixed number of elements. Adding a new element overwrites the
 * oldest element in the array, like a circular fixed-size buffer.
 * @param <T>
 */
public class CircularOverwriteArray<T> implements Iterable<T> {
  private final T[] data;

  /**
   * The index of the most recently added item.
   */
  private int head;

  /**
   * Create a new circular overwriting array.
   * @param size the size of the array
   * @param def the default which the array is initially filled with
   */
  @SuppressWarnings("unchecked")
  public CircularOverwriteArray(int size, T def) {
    if (size <= 0) {
      throw new IllegalArgumentException("Illegal size");
    }

    data = (T[]) new Object[size];
    head = 0;

    Arrays.fill(data, def);
  }

  /**
   * Add an item to the array.
   * Overwrites next item in the array.
   * @param item the item to add
   */
  public void add(T item) {
    head = (head + 1) % data.length;

    data[head] = item;
  }

  /**
   * An iterator from the oldest element to the newest.
   * @return an iterator (without remove support)
   */
  @Override
  public Iterator<T> iterator() {
    final int start = (head + 1) % data.length;
    final int length = data.length;
    final T[] dataCopy = data;

    return new Iterator<T>() {
      private int c = 0;
      private int i = start;

      @Override
      public boolean hasNext() {
        return c < length;
      }

      @Override
      public T next() {
        if (c >= length) {
          throw new NoSuchElementException();
        }

        int o = i;

        i = (i + 1) % length;
        ++c;

        return dataCopy[o];
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
