/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game;

import java.util.ArrayList;

import main.GroupContainer;
import math.CollisionHelper;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

import components.interfaces.IDamagable;
import components.interfaces.IEntity;
import components.physics.AABB;
import components.triggers.Trigger;
import components.triggers.actions.IAction;

import entities.Animated;
import entities.Creep;
import entities.Player;
import entities.Unit;
import entities.projectiles.Projectile;

public class World {
  private static final Entities[] UNITS = { Entities.CREEP,
                                            Entities.BOSS,
                                            Entities.PLAYER};

  /**
   * The layout of rectangles:
   * |-----------------------------|
   * | bigRect                     |
   * |                             |
   * |                             |
   * |---------|---------|         |
   * | cKiller |smallRect|         |
   * |---------|---------|         |
   * |                             |
   * |                             |
   * |                             |
   * |-----------------------------|
   */
  private final Rectangle             smallRect, bigRect, creepKiller;

  private final ArrayList<Trigger> triggers;

  private final GroupContainer<Entities, IEntity> entities;

  public World(final float width, final float height) {
    smallRect = new Rectangle(0, 0, width, height);
    bigRect = new Rectangle(-width, -height, width * 3, height * 3);
    creepKiller = new Rectangle(-width, 0, width, height);

    triggers = new ArrayList<Trigger>();

    entities = new GroupContainer<Entities, IEntity>();
  }

  public void update(final GameTime time) {
    // Do projectile collisions first, I think it leads to more accurate collisions
    for (final IEntity e1 : entities.get(Entities.PROJECTILE)) {
      Projectile p = (Projectile) e1;
      if (p.canCollide()) {
        // Check for collisions with units
        final AABB a = p.getBody();
        for (final IEntity e2 : entities.get(UNITS)) {
          Unit u = (Unit) e2;
          if (CollisionHelper.SweepCollisionTest(a, u.getBody(), time.getFrameLength())) {
            // FIXME: If the projectile can hit multiple targets and is sufficently slow,
            //        it might hit the same target multiple times.
            if (p.isAlive()) {
              p.damage(1);
              u.damage(p.getDamage());
            } else {
              p.kill();
              break;
            }
          }
        }
      }
    }

    // Update
    ArrayList<IEntity> toRemove = new ArrayList<IEntity>();
    for (IEntity e : entities.getAll()) {
      e.update(time);

      if (!e.getBody().isIntersecting(bigRect)) {
        toRemove.add(e);
      }
    }

    // Update players
    for (final IEntity e : entities.get(Entities.PLAYER)) {
      Player p = (Player) e;
      CollisionHelper.BlockFromExiting(p.getBody(), smallRect);
    }

    // Remove creeps when they've reach their goal
    for (final IEntity e1 : entities.get(Entities.CREEP)) {
      Creep c = (Creep) e1;
      if (creepKiller.isContaining(c.getBody())) {
        // TODO: c.killSilently();
        for (final IEntity e2 : entities.get(Entities.PLAYER)) {
          ((Player) e2).damage(c.getDamage());
        }
      }
    }

    for (final Trigger t : triggers) {
      t.update(time, this);
    }

    // Get actions
    final ArrayList<IAction> actions = new ArrayList<IAction>();
    for (final IEntity e : entities.getAll()) {
      actions.addAll(e.getActions());
      e.clearActions();
    }

    // Execute all actions
    for (final IAction a : actions) {
      a.execute(time, this);
    }

    // Remove dead objects, do this last so we make sure any actions
    // are carried out properly
    for (final IEntity e : entities.getAll()) {
      if (!e.isAlive()) {
        if (!toRemove.contains(e)) {
          toRemove.add(e);
        }
      }
    }
    
    entities.remove(toRemove);
  }

  public void render(final Graphics g) {
    for (final IEntity e : entities.getAll()) {
      e.render(g);
    }
  }

  public void addPlayer(final Player p) {
    assert (p != null);
    
    entities.add(Entities.PLAYER, p);
  }

  public void addProjectile(final Projectile p) {
    assert (p != null);
    
    entities.add(Entities.PROJECTILE, p);
  }

  public void addCreep(final Creep c) {
    assert (c != null);
    
    entities.add(Entities.CREEP, c);
  }

  public void addAnimated(final Animated a) {
    assert (a != null);
    
    entities.add(Entities.ANIMATED, a);
  }

  public void addTrigger(final Trigger trigger) {
    triggers.add(trigger);
  }

  public int getX2() {
    return (int) smallRect.getX2();
  }

  public int getY2() {
    return (int) smallRect.getY2();
  }

  public Iterable<IEntity> getUnits() {
    return entities.get(UNITS);
  }

  public Iterable<IEntity> get(Entities e) {
    return entities.get(e);
  }
}
