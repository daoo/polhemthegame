/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.components.ComponentMessages;
import game.components.physics.AABB;
import game.entities.Unit;
import game.entities.groups.EntityType;
import game.entities.groups.Groups;
import game.entities.interfaces.IObject;
import game.entities.projectiles.Projectile;

import java.util.LinkedList;

import math.CollisionHelper;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class World {
  private final LinkedList<IObject> toAdd, toRemove;
  private final WorldContainer entities;

  public World() {
    toAdd    = new LinkedList<IObject>();
    toRemove = new LinkedList<IObject>();
    entities = new WorldContainer();
  }

  public void update(final GameTime time) {
    // Do projectile collisions first, I think it leads to more accurate collisions
    for (final IObject e1 : entities.iterate(EntityType.PROJECTILE)) {
      final Projectile p = (Projectile) e1;
      if (p.canCollide()) {
        // Check for collisions with units
        final AABB a = p.getBody();
        for (final IObject e2 : entities.iterate(Groups.UNITS)) {
          final Unit u = (Unit) e2;
          if (CollisionHelper.SweepCollisionTest(a, u.getBody(), time.getFrameLength())) {
            // FIXME: If the projectile can hit multiple targets and is sufficently slow,
            //        it might hit the same target multiple times.
            if (p.isAlive()) {
              p.damage(1);
              u.damage(p.getDamage());
            } else {
              p.sendMessage(ComponentMessages.KILL);
              break;
            }
          }
        }
      }
    }

    // Update
    for (final IObject e : entities.iterateAll()) {
      e.update(time, this);
    }

    if (!toRemove.isEmpty()) {
      entities.remove(toRemove);
      toRemove.clear();
    }

    if (!toAdd.isEmpty()) {
      entities.add(toAdd);
      toAdd.clear();
    }
  }

  public void render(final Graphics g) {
    for (final IObject e : entities.iterateAll()) {
      e.render(g);
    }
  }

  /**
   * Delayed add, happens at the end of a frame.
   * @param obj object to add
   */
  public void add(final IObject obj) {
    assert (obj != null);

    toAdd.add(obj);
  }

  /**
   * Delayed remove, happens at the end of a frame.
   * @param obj object to remove
   */
  public void remove(final IObject obj) {
    assert (obj != null);

    toRemove.add(obj);
  }

  public Iterable<IObject> getUnits() {
    return entities.iterate(Groups.UNITS);
  }

  public Iterable<IObject> get(final EntityType e) {
    return entities.iterate(e);
  }
}
