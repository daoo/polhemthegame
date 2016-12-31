/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.course;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import debug.DebugHelper;
import debug.IDebuggable;
import game.entities.Entity;
import game.entities.IEntity;
import game.triggers.Trigger;
import game.types.GameTime;
import util.Node;

public class World implements IDebuggable {
  private final ArrayList<IEntity> mMisc;
  private final ArrayList<Entity> mUnits;
  private final ArrayList<Entity> mProjectiles;
  private final ArrayList<IEntity> mNewMisc;
  private final ArrayList<Entity> mNewUnits;
  private final ArrayList<Entity> mNewProjectiles;
  private final ArrayList<Trigger> mTriggers;
  private final ArrayList<Trigger> mNewTriggers;

  public World() {
    mMisc = new ArrayList<>();
    mUnits = new ArrayList<>();
    mProjectiles = new ArrayList<>();

    mNewMisc = new ArrayList<>();
    mNewUnits = new ArrayList<>();
    mNewProjectiles = new ArrayList<>();

    mTriggers = new ArrayList<>();
    mNewTriggers = new ArrayList<>();
  }

  public void addMisc(IEntity obj) {
    assert obj != null;

    mNewMisc.add(obj);
  }

  public void addUnit(Entity obj) {
    assert obj != null;

    obj.setWorld(this);
    mNewUnits.add(obj);
  }

  public void addProjectile(Entity obj) {
    assert obj != null;

    obj.setWorld(this);
    mNewProjectiles.add(obj);
  }

  public Iterable<Entity> getUnits() {
    return Collections.unmodifiableCollection(mUnits);
  }

  public void render(Graphics g) {
    for (IEntity e : mMisc) {
      e.render(g);
    }

    for (IEntity e : mUnits) {
      e.render(g);
    }

    for (IEntity e : mProjectiles) {
      e.render(g);
    }
  }

  public void update(GameTime time) {
    // Triggers
    Iterator<Trigger> itt = mTriggers.iterator();
    while (itt.hasNext()) {
      Trigger t = itt.next();
      t.update(time);

      if (!t.runAgain()) {
        itt.remove();
      }
    }

    // Entities
    processEntities(mMisc, time);
    processEntities(mUnits, time);
    processEntities(mProjectiles, time);

    // Move new stuff
    move(mNewMisc, mMisc);
    move(mNewUnits, mUnits);
    move(mNewProjectiles, mProjectiles);
    move(mNewTriggers, mTriggers);
  }

  public void addTrigger(Trigger trigger) {
    assert trigger != null;

    trigger.setWorld(this);
    mNewTriggers.add(trigger);
  }

  public void addAllTriggers(Collection<Trigger> collection) {
    assert collection != null;

    for (Trigger t : collection) {
      t.setWorld(this);
    }

    mNewTriggers.addAll(collection);
  }

  @Override
  public String debugString() {
    return "World";
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());

    parent.nodes.add(DebugHelper.listToNode("Misc", mMisc));
    parent.nodes.add(DebugHelper.listToNode("Units", mUnits));
    parent.nodes.add(DebugHelper.listToNode("Projectiles", mProjectiles));
    parent.nodes.add(DebugHelper.listToNode("Triggers", mTriggers));

    return parent;
  }

  private static void processEntities(Iterable<? extends IEntity> entities, GameTime time) {
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
