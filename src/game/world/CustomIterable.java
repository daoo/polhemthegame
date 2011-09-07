/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.interfaces.IObject;

import java.util.Iterator;

public class CustomIterable implements Iterable<IObject> {
  private final Iterator<IObject> iterator;

  public CustomIterable(final Iterator<IObject> iterator) {
    this.iterator = iterator;
  }

  @Override
  public Iterator<IObject> iterator() {
    return iterator;
  }
}
