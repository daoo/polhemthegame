/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.physics.AABB;
import game.entities.groups.Entities;
import game.entities.interfaces.IEntity;
import game.entities.interfaces.IObject;
import game.events.EventHandler;
import game.events.impl.ObjectEventArgs;
import game.world.World;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class InvisibleRectangle implements IEntity {
  private final AABB body;

  public final EventHandler<ObjectEventArgs> onContainsEvent;
  public final EventHandler<ObjectEventArgs> onNotContainsEvent;

  public InvisibleRectangle(final float x, final float y, final float w, final float h) {
    body = new AABB(x, y, w, h, 0, 0);

    onContainsEvent = new EventHandler<ObjectEventArgs>();
    onNotContainsEvent = new EventHandler<ObjectEventArgs>();
  }

  @Override
  public void update(final GameTime time, final World world) {
    for (final IObject o : world.getUnits()) {
      final Unit u = (Unit) o;
      if (body.isContaining(u.getBody())) {
        onContainsEvent.execute(this, new ObjectEventArgs(world, u));
      } else {
        onNotContainsEvent.execute(this, new ObjectEventArgs(world, u));
      }
    }
  }

  @Override
  public void render(final Graphics g) {
    // Do nothing
  }

  @Override
  public AABB getBody() {
    return body;
  }

  @Override
  public Entities getType() {
    return Entities.GENERAL;
  }
}
