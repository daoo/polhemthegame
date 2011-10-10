/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.IEntity;
import game.entities.groups.EntityType;

import java.util.Collection;
import java.util.LinkedList;

public class WorldContainer {
  private final LinkedList<IEntity> list;

  public WorldContainer() {
    list = new LinkedList<IEntity>();
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

  public void remove(final Collection<IEntity> remove) {
    list.removeAll(remove);
  }

  public void add(final IEntity obj) {
    list.add(obj);
  }

  public void add(final LinkedList<IEntity> objs) {
    list.addAll(objs);
  }
}
