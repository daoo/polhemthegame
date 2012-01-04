/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.EntityType;
import game.entities.Groups;
import game.entities.IEntity;
import game.pods.GameTime;
import game.triggers.ITrigger;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.Graphics;

import util.Node;
import debug.DebugHelper;
import debug.IDebuggable;

public class World implements IDebuggable {
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
    for (IEntity e : entities) {
      e.render(g);
    }
  }

  public void update(GameTime time) {
    // Entities
    for (IEntity e : entities) {
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

    Iterator<IEntity> itr = entities.iterator();
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
    assert trigger != null;

    trigger.setWorld(this);
    addTriggers.add(trigger);
  }

  public void addAllTriggers(Collection<? extends ITrigger> collection) {
    assert collection != null;

    for (ITrigger t : collection) {
      t.setWorld(this);
    }

    addTriggers.addAll(collection);
  }


  @Override
  public String debugString() {
    return "World";
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());

    Node<String> ents = new Node<>("Entities (" + entities.size() + ")");
    for (IEntity e : entities) {
      ents.nodes.add(e.debugTree());
    }
    parent.nodes.add(ents);

    parent.nodes.add(DebugHelper.listToNode("Triggers", triggers));

    return parent;
  }
}
