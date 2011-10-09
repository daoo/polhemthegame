/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.groups.EntityType;
import game.entities.interfaces.IObject;

import java.util.Arrays;
import java.util.Iterator;

public class SkipsIterator implements Iterator<IObject> {
  private final Iterator<IObject> internal;
  private final EntityType[] keys;

  private IObject next;

  public SkipsIterator(final Iterable<IObject> list, final EntityType[] keys) {
    assert (list != null);
    assert (keys != null);

    internal = list.iterator();
    this.keys = keys;
  }

  @Override
  public boolean hasNext() {
    if (next == null) {
      internalNext();
    }

    return next != null;
  }

  @Override
  public IObject next() {
    if (next == null) {
      internalNext();
    }

    final IObject tmp = next;
    // Set next to null to force internalNext on next call
    next = null;

    return tmp;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Not allowed");
  }

  private void internalNext() {
    if (next == null) {
      while (internal.hasNext()) {
        next = internal.next();
        if (Arrays.binarySearch(keys, next.getType()) >= 0) {
          return;
        }
      }

      // Couldn't find a matching IObject
      next = null;
    }
  }
}
