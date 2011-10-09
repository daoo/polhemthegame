/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.groups.EntityType;
import game.entities.interfaces.IObject;

import java.util.Collection;
import java.util.LinkedList;

public class WorldContainer {
  private final LinkedList<IObject> list;

  public WorldContainer() {
    list = new LinkedList<IObject>();
  }

  public int size() {
    return list.size();
  }

  public CustomIterable iterateAll() {
    return new CustomIterable(new AllIterator(list));
  }

  public CustomIterable iterate(final EntityType key) {
    return new CustomIterable(new SkipsIterator(list, new EntityType[] { key }));
  }

  public CustomIterable iterate(final EntityType[] keys) {
    return new CustomIterable(new SkipsIterator(list, keys));
  }

  public void remove(final Collection<IObject> remove) {
    list.removeAll(remove);
  }

  public void add(final IObject obj) {
    list.add(obj);
  }

  public void add(final LinkedList<IObject> objs) {
    list.addAll(objs);
  }
}
