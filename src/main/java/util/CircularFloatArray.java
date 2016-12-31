/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
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
  private final float[] mData;

  /**
   * The index of the most recently added item.
   */
  private int mHead;

  /**
   * Create a new circular overwriting array.
   *
   * @param size the size of the array, greater than zero
   * @param def the default which the array is initially filled with
   */
  public CircularFloatArray(int size, float def) {
    assert size > 0;

    mData = new float[size];
    mHead = 0;

    Arrays.fill(mData, def);
  }

  /**
   * Add an item to the array.
   * Overwrites next item in the array.
   *
   * @param item the item to add
   */
  public void add(float item) {
    mHead = (mHead + 1) % mData.length;

    mData[mHead] = item;
  }

  /**
   * An iterator from the oldest element to the newest.
   *
   * @return an iterator (without remove support)
   */
  public FloatIterator iterator() {
    return new FloatIterator(mData, (mHead + 1) % mData.length);
  }

  /**
   * Iterator used for iterating a circular float array.
   * Does not implement Iterator because we can not implement the remove method.
   */
  public static class FloatIterator {
    private final float[] mData;
    private int mCount;
    private int mIndex;

    FloatIterator(float[] data, int start) {
      mData = data;

      mCount = 0;
      mIndex = start;
    }

    public boolean hasNext() {
      return mCount < mData.length;
    }

    public float next() {
      if (mCount >= mData.length) {
        throw new NoSuchElementException();
      }

      int o = mIndex;

      mIndex = (mIndex + 1) % mData.length;
      ++mCount;

      return mData[o];
    }
  }
}
