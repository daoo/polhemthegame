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
import debug.Debuggable;
import game.entities.Entity;
import game.entities.EntityImpl;
import game.triggers.Trigger;
import game.types.GameTime;
import util.Node;

public class World implements Debuggable {
  private final ArrayList<Entity> mMisc = new ArrayList<>();
  private final ArrayList<EntityImpl> mUnits = new ArrayList<>();
  private final ArrayList<EntityImpl> mProjectiles = new ArrayList<>();
  private final ArrayList<Entity> mNewMisc = new ArrayList<>();
  private final ArrayList<EntityImpl> mNewUnits = new ArrayList<>();
  private final ArrayList<EntityImpl> mNewProjectiles = new ArrayList<>();
  private final ArrayList<Trigger> mTriggers = new ArrayList<>();
  private final ArrayList<Trigger> mNewTriggers = new ArrayList<>();

  private final Graphics mStatics;

  public World(Graphics statics) {
    mStatics = statics;
  }

  public void addMisc(Entity obj) {
    assert obj != null;

    mNewMisc.add(obj);
  }

  public void addUnit(EntityImpl obj) {
    assert obj != null;

    obj.setWorld(this);
    mNewUnits.add(obj);
  }

  public void addProjectile(EntityImpl obj) {
    assert obj != null;

    obj.setWorld(this);
    mNewProjectiles.add(obj);
  }

  public Iterable<EntityImpl> getUnits() {
    return Collections.unmodifiableCollection(mUnits);
  }

  public void render(Graphics g) {
    for (Entity e : mMisc) {
      e.render(g);
    }

    for (Entity e : mUnits) {
      e.render(g);
    }

    for (Entity e : mProjectiles) {
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

  private void processEntities(Iterable<? extends Entity> entities, GameTime time) {
    Iterator<? extends Entity> itr = entities.iterator();
    while (itr.hasNext()) {
      Entity e = itr.next();
      e.update(time);
      if (!e.isActive()) {
        if (!e.keepRendering()) {
          e.render(mStatics);
        }
        itr.remove();
      }
    }
  }

  private static <T> void move(List<? extends T> from, List<? super T> to) {
    to.addAll(from);
    from.clear();
  }
}
