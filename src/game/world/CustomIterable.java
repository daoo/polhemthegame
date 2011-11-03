/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.IEntity;

import java.util.Iterator;

public class CustomIterable implements Iterable<IEntity> {
  private final Iterator<IEntity> iterator;

  public CustomIterable(Iterator<IEntity> iterator) {
    this.iterator = iterator;
  }

  @Override
  public Iterator<IEntity> iterator() {
    return iterator;
  }
}
