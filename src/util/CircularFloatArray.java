/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package util;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Circular overwriting array.
 * Contains a fixed number of elements. Adding a new element overwrites the
 * oldest element in the array, like a circular fixed-size buffer. Explicitly
 * made float because java boxing is slow.
 */
public class CircularFloatArray {
  private final float[] data;

  /**
   * The index of the most recently added item.
   */
  private int head;

  /**
   * Create a new circular overwriting array.
   * @param size the size of the array
   * @param def the default which the array is initially filled with
   */
  public CircularFloatArray(int size, float def) {
    if (size <= 0) {
      throw new IllegalArgumentException("Illegal size");
    }

    data = new float[size];
    head = 0;

    Arrays.fill(data, def);
  }

  /**
   * Add an item to the array.
   * Overwrites next item in the array.
   * @param item the item to add
   */
  public void add(float item) {
    head = (head + 1) % data.length;

    data[head] = item;
  }

  /**
   * An iterator from the oldest element to the newest.
   * @return an iterator (without remove support)
   */
  public FloatIterator iterator() {
    return new FloatIterator(data, (head + 1) % data.length);
  }

  /**
   * Iterator used for iterating a circular float array.
   * Does not implement Iterator because we can not implement the remove method.
   */
  public static class FloatIterator {
    private final float[] data;
    private int c, i;

    FloatIterator(float[] data, int start) {
      this.data = data;

      c = 0;
      i = start;
    }

    public boolean hasNext() {
      return c < data.length;
    }

    public float next() {
      if (c >= data.length) {
        throw new NoSuchElementException();
      }

      int o = i;

      i = (i + 1) % data.length;
      ++c;

      return data[o];
    }
  }
}
