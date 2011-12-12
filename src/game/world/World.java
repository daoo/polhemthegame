/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
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
  private final LinkedList<IEntity> toAddFirst, toAddLast;

  private final LinkedList<ITrigger> triggers, addTriggers;

  public World() {
    toAddFirst = new LinkedList<>();
    toAddLast  = new LinkedList<>();
    entities   = new WorldContainer();

    triggers    = new LinkedList<>();
    addTriggers = new LinkedList<>();
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
    Iterator<ITrigger> itt = triggers.iterator();
    while (itt.hasNext()) {
      ITrigger t = itt.next();
      t.update(time);

      if (!t.runAgain()) {
        itt.remove();
      }
    }

    Iterator<IEntity> itr = entities.iterateAll().iterator();
    while (itr.hasNext()) {
      if (!itr.next().isActive()) {
        itr.remove();
      }
    }

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
