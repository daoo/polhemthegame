/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.groups.Entities;
import game.entities.interfaces.IObject;

import java.util.Iterator;

public class SkipIterator implements Iterator<IObject> {
  private final Iterator<IObject> internal;
  private final Entities entities;

  private IObject next;

  public SkipIterator(Iterable<IObject> object, Entities entities) {
    internal = object.iterator();
    this.entities = entities;
  }

  @Override
  public boolean hasNext() {
    while (internal.hasNext()) {
      next = internal.next();
      if (next.getType() == entities) {
        return true;
      }
    }

    return false;
  }

  @Override
  public IObject next() {
    if (next == null) {
      return internal.next();
    } else {
      return next;
    }
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Not allowed");
  }
}
