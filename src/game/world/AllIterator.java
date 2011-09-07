/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.interfaces.IObject;

import java.util.Iterator;

public class AllIterator implements Iterator<IObject> {
  private final Iterator<IObject> internal;

  public AllIterator(Iterable<IObject> list) {
    internal = list.iterator();
  }

  @Override
  public IObject next() {
    return internal.next();
  }

  @Override
  public boolean hasNext() {
    return internal.hasNext();
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Not allowed");
  }
}
