/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.groups.Entities;
import game.entities.interfaces.IObject;

import java.util.ArrayList;
import java.util.Collection;

public class WorldContainer {
  private final ArrayList<IObject> list;

  public WorldContainer() {
    list = new ArrayList<IObject>();
  }

  public int size() {
    return list.size();
  }

  public CustomIterable iterateAll() {
    return new CustomIterable(new AllIterator(list));
  }

  public CustomIterable iterate(final Entities key) {
    return new CustomIterable(new SkipIterator(list, key));
  }

  public CustomIterable iterate(final Entities[] keys) {
    return new CustomIterable(new SkipsIterator(list, keys));
  }

  public void remove(final Collection<IObject> remove) {
    list.removeAll(remove);
  }

  public void add(final IObject obj) {
    list.add(obj);
  }
}
