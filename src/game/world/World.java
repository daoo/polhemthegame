/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.EntityType;
import game.entities.Groups;
import game.entities.IEntity;
import game.time.GameTime;
import game.triggers.ITrigger;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.Graphics;

public class World {
  private final WorldContainer entities;
  private final LinkedList<IEntity> toAddFirst, toAddLast, toRemove;

  private final LinkedList<ITrigger> triggers, addTriggers;

  public World() {
    toAddFirst = new LinkedList<IEntity>();
    toAddLast  = new LinkedList<IEntity>();
    toRemove   = new LinkedList<IEntity>();
    entities   = new WorldContainer();
    
    triggers   = new LinkedList<ITrigger>();
    addTriggers = new LinkedList<ITrigger>();
  }

  /**
   * Delayed add at the beginning of the world container.
   * Happens at the end of a update iteration.
   * @param obj object to add
   */
  public void addFirst(IEntity obj) {
    assert (obj != null);

    obj.setWorld(this);
    toAddFirst.add(obj);
  }

  /**
   * Delayed add at the end of the world container.
   * Happens at the end of a update iteration.
   * @param obj object to add
   */
  public void addLast(IEntity obj) {
    assert (obj != null);

    obj.setWorld(this);
    toAddLast.add(obj);
  }

  public Iterable<IEntity> get(EntityType e) {
    return entities.iterate(e);
  }

  public Iterable<IEntity> getUnits() {
    return entities.iterate(Groups.UNITS);
  }

  /**
   * Delayed remove, happens at the end of a frame.
   * @param obj object to remove
   */
  public void remove(IEntity obj) {
    assert (obj != null);

    toRemove.add(obj);
  }

  public void render(Graphics g) {
    for (IEntity e : entities.iterateAll()) {
      e.render(g);
    }
  }

  public void update(GameTime time) {
    // Entities
    for (IEntity e : entities.iterateAll()) {
      e.update(time);
    }

    // Triggers
    Iterator<ITrigger> it = triggers.iterator();
    while (it.hasNext()) {
      ITrigger t = it.next();
      t.update(time);

      if (!t.runAgain()) {
        it.remove();
      }
    }

    // Add and remove
    entities.remove(toRemove);
    toRemove.clear();

    entities.addLastAll(toAddLast);
    toAddLast.clear();

    entities.addFirstAll(toAddFirst);
    toAddFirst.clear();
    
    triggers.addAll(addTriggers);
    addTriggers.clear();
  }

  public void addTrigger(ITrigger trigger) {
    trigger.setWorld(this);
    addTriggers.add(trigger);
  }
}
