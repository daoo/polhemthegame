/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.groups.Entities;
import game.entities.interfaces.IObject;

import java.util.Arrays;
import java.util.Iterator;

public class SkipsIterator implements Iterator<IObject> {
  private final Iterator<IObject> internal;
  private final Entities[] keys;

  private IObject next;

  public SkipsIterator(final Iterable<IObject> list, final Entities[] keys) {
    assert (list != null);
    assert (keys != null);

    internal = list.iterator();
    this.keys = keys;
  }

  @Override
  public boolean hasNext() {
    while (internal.hasNext()) {
      next = internal.next();
      if (Arrays.binarySearch(keys, next.getType()) >= 0) {
        return true;
      }
    }

    return false;
  }

  @Override
  public IObject next() {
    return null;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Not allowed");
  }
}
