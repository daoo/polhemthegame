/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.IEntity;

import java.util.Iterator;

public class AllIterator implements Iterator<IEntity> {
  private final Iterator<IEntity> internal;

  public AllIterator(Iterable<IEntity> list) {
    internal = list.iterator();
  }

  @Override
  public IEntity next() {
    return internal.next();
  }

  @Override
  public boolean hasNext() {
    return internal.hasNext();
  }

  @Override
  public void remove() {
    internal.remove();
  }
}
