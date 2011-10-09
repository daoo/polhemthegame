/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.world;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.basic.Life;
import game.components.physics.AABB;
import game.entities.Unit;
import game.entities.groups.EntityType;
import game.entities.groups.Groups;
import game.entities.interfaces.IEntity;
import game.entities.projectiles.Projectile;

import java.util.LinkedList;

import math.CollisionHelper;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class World {
  private final LinkedList<IEntity> toAdd, toRemove;
  private final WorldContainer entities;

  public World() {
    toAdd    = new LinkedList<IEntity>();
    toRemove = new LinkedList<IEntity>();
    entities = new WorldContainer();
  }

  public void update(final GameTime time) {
    // Do projectile collisions first, I think it leads to more accurate collisions
    for (final IEntity e1 : entities.iterate(EntityType.PROJECTILE)) {
      final Projectile p = (Projectile) e1;
      if (p.canCollide()) {
        // Check for collisions with units
        final AABB a = p.getBody();
        for (final IEntity e2 : entities.iterate(Groups.UNITS)) {
          final Unit u = (Unit) e2;
          if (CollisionHelper.SweepCollisionTest(a, u.getBody(), time.getFrameLength())) {
            // FIXME: If the projectile can hit multiple targets and is sufficently slow,
            //        it might hit the same target multiple times.
            if (((Life) p.getComponent(ComponentType.HEALTH)).isAlive()) {
              p.sendMessage(ComponentMessage.DAMAGE, 1);
              u.sendMessage(ComponentMessage.DAMAGE, p.getDamage());
            } else {
              p.sendMessage(ComponentMessage.KILL, null);
              break;
            }
          }
        }
      }
    }

    // Update
    for (final IEntity e : entities.iterateAll()) {
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
    for (final IEntity e : entities.iterateAll()) {
      e.render(g);
    }
  }

  /**
   * Delayed add, happens at the end of a frame.
   * @param obj object to add
   */
  public void add(final IEntity obj) {
    assert (obj != null);

    toAdd.add(obj);
  }

  /**
   * Delayed remove, happens at the end of a frame.
   * @param obj object to remove
   */
  public void remove(final IEntity obj) {
    assert (obj != null);

    toRemove.add(obj);
  }

  public Iterable<IEntity> getUnits() {
    return entities.iterate(Groups.UNITS);
  }

  public Iterable<IEntity> get(final EntityType e) {
    return entities.iterate(e);
  }
}
