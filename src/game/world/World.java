/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.IEntity;
import game.entities.groups.EntityType;
import game.entities.groups.Groups;

import java.util.LinkedList;

import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class World {
  private final WorldContainer entities;
  private final LinkedList<IEntity> toAdd, toRemove;

  public World() {
    toAdd    = new LinkedList<IEntity>();
    toRemove = new LinkedList<IEntity>();
    entities = new WorldContainer();
  }

  /**
   * Delayed add, happens at the end of a frame.
   * @param obj object to add
   */
  public void add(final IEntity obj) {
    assert (obj != null);

    obj.setWorld(this);
    toAdd.add(obj);
  }

  public Iterable<IEntity> get(final EntityType e) {
    return entities.iterate(e);
  }

  public Iterable<IEntity> getUnits() {
    return entities.iterate(Groups.UNITS);
  }

  /**
   * Delayed remove, happens at the end of a frame.
   * @param obj object to remove
   */
  public void remove(final IEntity obj) {
    assert (obj != null);

    toRemove.add(obj);
  }

  public void render(final Graphics g) {
    for (final IEntity e : entities.iterateAll()) {
      e.render(g);
    }
  }

  public void update(final GameTime time) {
    // Update
    for (final IEntity e : entities.iterateAll()) {
      e.update(time);
    }

    entities.remove(toRemove);
    toRemove.clear();

    entities.add(toAdd);
    toAdd.clear();
  }
}
