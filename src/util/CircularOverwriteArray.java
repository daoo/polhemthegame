package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularOverwriteArray<T> implements Iterable<T> {
  private final T[] data;

  private boolean full;
  private int head;

  public CircularOverwriteArray(int size, T def) {
    data = (T[]) new Object[size];
    head = 0;

    for (int i = 0; i < size; ++i) {
      data[i] = def;
    }

    full = false;
  }

  public void add(T item) {
    ++head;
    if (head >= data.length) {
      head = 0;
      full = true;
    }

    data[head] = item;
  }

  @Override
  public Iterator<T> iterator() {
    final int start = full ? head + 1 : 0;
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
