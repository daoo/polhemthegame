/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.EntityType;
import game.entities.IEntity;

import java.util.Iterator;

public class SkipsIterator implements Iterator<IEntity> {
  private final Iterator<IEntity> internal;
  private final EntityType[] keys;

  private IEntity next;

  public SkipsIterator(Iterable<IEntity> list, EntityType[] keys) {
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
  public IEntity next() {
    if (next == null) {
      internalNext();
    }

    IEntity tmp = next;
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

        // We only use very small arrays here, binary search would probably be
        // slower because of larger constant.
        for (int i = 0; i < keys.length; ++i) {
          if (keys[i] == next.getType()) {
            return;
          }
        }
      }

      // Couldn't find a matching IEntity
      next = null;
    }
  }
}
