/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package main;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;

import other.GameTime;

import physics.CollisionHelper;

import components.actions.IAction;
import components.basic.IDamagable;
import components.basic.IDynamic;
import components.basic.IUnit;
import components.physics.AABB;

import entities.Creep;
import entities.Player;
import entities.projectiles.Projectile;

public class World {
  private final float                 width, height;
  private final AABB                  smallBox, bigBox;

  private final ArrayList<IUnit>      units;
  private final ArrayList<Projectile> projectiles;
  private final ArrayList<Player>     players;

  public World(final float width, final float height) {
    this.width = width;
    this.height = height;
    smallBox = new AABB(0, 0, width, height, 0, 0);
    bigBox = new AABB(-1000, -1000, width + 2000, height + 2000, 0, 0);

    units = new ArrayList<IUnit>();
    projectiles = new ArrayList<Projectile>();
    players = new ArrayList<Player>();
  }

  public void update(final GameTime time) {
    // Use this for delaying action execution to the end of the frame
    final ArrayList<IAction> actions = new ArrayList<IAction>();

    // Do projectile collisions first, I think it leads to more accurate collisions
    for (final Projectile p : projectiles) {
      if (p.canCollide()) {
        // Check for collisions with units
        final AABB a = p.getBody();
        for (final IUnit u : units) {
          if (CollisionHelper.SweepCollisionTest(a, u.getBody(), time.getFrameLength())) {
            if (!projCollisionWithUnit(p, u)) {
              break;
            }
          }
        }
      }
    }

    // Update 
    updateDynamics(units, time);
    updateDynamics(projectiles, time);

    // Update players
    for (final Player p : players) {
      p.update(time);
      CollisionHelper.BlockFromExiting(p.getBody(), smallBox);
      actions.addAll(p.getActions());
      p.clearActions();
    }

    // Remove dead objects
    removeNoMores(units);
    removeNoMores(projectiles);

    // Finally execute all actions accumulated during the frame
    for (IAction a : actions) {
      a.execute(this);
    }
  }

  public void render(final Graphics g) {
    for (final IUnit u : units) {
      u.render(g);
    }

    for (final Player p : players) {
      p.render(g);
    }

    for (final Projectile p : projectiles) {
      p.render(g);
    }
  }

  public void add(final Player p) {
    players.add(p);
  }

  public void add(final Projectile p) {
    projectiles.add(p);
  }

  public void add(final Creep c) {
    units.add(c);
  }

  public int getX2() {
    return (int) width;
  }

  public int getY2() {
    return (int) height;
  }

  private <T extends IDamagable> void removeNoMores(final Iterable<T> list) {
    final Iterator<T> it = list.iterator();
    while (it.hasNext()) {
      if (!it.next().isAlive()) {
        it.remove();
      }
    }
  }

  private <T extends IDynamic> void updateDynamics(final Iterable<T> list, final GameTime time) {
    final Iterator<T> it = list.iterator();
    while (it.hasNext()) {
      final IDynamic e = it.next();
      e.update(time);

      if (!e.getBody().isIntersecting(bigBox)) {
        e.kill();
        it.remove();
      }
    }
  }

  /**
   * Handles collisons between a projectile and a unit.
   * 
   * @return Returns true if the projecile still exists, false otherwise.
   */
  private boolean projCollisionWithUnit(final Projectile p, final IDamagable u) {
    if (p.isAlive()) {
      p.damage(1);
      u.damage(p.getDamage());
    } else {
      p.kill();

      return false;
    }

    return true;
  }
}
