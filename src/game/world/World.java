/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.entities.Entity;
import game.entities.IEntity;
import game.pods.GameTime;
import game.triggers.ITrigger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Graphics;

import util.Node;
import debug.DebugHelper;
import debug.IDebuggable;

public class World implements IDebuggable {
  private final ArrayList<IEntity> misc;
  private final ArrayList<Entity> units, projectiles;
  private final ArrayList<IEntity> newMisc;
  private final ArrayList<Entity> newUnits, newProjectiles;
  private final ArrayList<ITrigger> triggers, newTriggers;

  public World() {
    misc        = new ArrayList<>();
    units       = new ArrayList<>();
    projectiles = new ArrayList<>();

    newMisc        = new ArrayList<>();
    newUnits       = new ArrayList<>();
    newProjectiles = new ArrayList<>();

    triggers    = new ArrayList<>();
    newTriggers = new ArrayList<>();
  }

  public void addMisc(IEntity obj) {
    assert obj != null;

    obj.setWorld(this);
    newMisc.add(obj);
  }

  public void addUnit(Entity obj) {
    assert obj != null;

    obj.setWorld(this);
    newUnits.add(obj);
  }

  public void addProjectile(Entity obj) {
    assert obj != null;

    obj.setWorld(this);
    newProjectiles.add(obj);
  }

  public Iterable<Entity> getUnits() {
    return units;
  }

  public void render(Graphics g) {
    for (IEntity e : misc) {
      e.render(g);
    }

    for (IEntity e : units) {
      e.render(g);
    }

    for (IEntity e : projectiles) {
      e.render(g);
    }
  }

  public void update(GameTime time) {
    // Triggers
    Iterator<ITrigger> itt = triggers.iterator();
    while (itt.hasNext()) {
      ITrigger t = itt.next();
      t.update(time);

      if (!t.runAgain()) {
        itt.remove();
      }
    }

    // Entities
    processEntities(misc, time);
    processEntities(units, time);
    processEntities(projectiles, time);

    // Move new stuff
    move(newMisc, misc);
    move(newUnits, units);
    move(newProjectiles, projectiles);
    move(newTriggers, triggers);
  }

  public void addTrigger(ITrigger trigger) {
    assert trigger != null;

    trigger.setWorld(this);
    newTriggers.add(trigger);
  }

  public void addAllTriggers(Collection<? extends ITrigger> collection) {
    assert collection != null;

    for (ITrigger t : collection) {
      t.setWorld(this);
    }

    newTriggers.addAll(collection);
  }

  @Override
  public String debugString() {
    return "World";
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());

    parent.nodes.add(DebugHelper.listToNode("Misc", misc));
    parent.nodes.add(DebugHelper.listToNode("Units", units));
    parent.nodes.add(DebugHelper.listToNode("Projectiles", projectiles));
    parent.nodes.add(DebugHelper.listToNode("Triggers", triggers));

    return parent;
  }

  private static void processEntities(Iterable<? extends IEntity> entities,
      GameTime time) {
    Iterator<? extends IEntity> itr = entities.iterator();
    while (itr.hasNext()) {
      IEntity e = itr.next();
      e.update(time);
      if (!e.isActive()) {
        itr.remove();
      }
    }
  }

  private static <T> void move(List<? extends T> from, List<? super T> to) {
    to.addAll(from);
    from.clear();
  }
}
