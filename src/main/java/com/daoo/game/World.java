/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game;

import java.util.ArrayList;

import main.GroupContainer;
import math.CollisionHelper;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

import components.physics.AABB;

import entities.Animated;
import entities.Creep;
import entities.Player;
import entities.Unit;
import entities.groups.Entities;
import entities.groups.Groups;
import entities.interfaces.IObject;
import entities.projectiles.Projectile;

public class World {
  private final ArrayList<IObject> toRemove;
  private final GroupContainer<Entities, IObject> entities;

  public World() {
    toRemove = new ArrayList<IObject>();
    entities = new GroupContainer<Entities, IObject>();
  }

  public void update(final GameTime time) {
    // Do projectile collisions first, I think it leads to more accurate collisions
    for (final IObject e1 : entities.get(Entities.PROJECTILE)) {
      Projectile p = (Projectile) e1;
      if (p.canCollide()) {
        // Check for collisions with units
        final AABB a = p.getBody();
        for (final IObject e2 : entities.get(Groups.UNITS)) {
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
    for (IObject e : entities.getAll()) {
      e.update(time, this);
    }

    entities.remove(toRemove);
  }

  public void render(final Graphics g) {
    for (final IObject e : entities.getAll()) {
      e.render(g);
    }
  }

  public void add(final IObject obj) {
    entities.add(Entities.GENERAL, obj);
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

  public Iterable<IObject> getUnits() {
    return entities.get(Groups.UNITS);
  }

  public Iterable<IObject> get(final Entities e) {
    return entities.get(e);
  }

  public void scheduleForRemoval(final IObject object) {
    toRemove.add(object);
  }
}
