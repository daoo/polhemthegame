/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.EntityType;
import game.entities.IEntity;

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

  public CustomIterable iterate(EntityType key) {
    return new CustomIterable(new SkipsIterator(list, new EntityType[] { key }));
  }

  public CustomIterable iterate(EntityType[] keys) {
    return new CustomIterable(new SkipsIterator(list, keys));
  }

  public void remove(Collection<IEntity> remove) {
    list.removeAll(remove);
  }

  public void add(IEntity obj) {
    list.add(obj);
  }

  public void add(LinkedList<IEntity> objs) {
    list.addAll(objs);
  }
}
