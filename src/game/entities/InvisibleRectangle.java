/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import java.util.Collection;
import java.util.Collections;

import math.Vector2;
import math.time.GameTime;

import org.newdawn.slick.Graphics;


import game.World;
import game.components.physics.AABB;
import game.components.triggers.actions.IAction;
import game.entities.groups.Entities;
import game.entities.interfaces.IEntity;
import game.entities.interfaces.IObject;
import game.events.EventHandler;
import game.events.impl.ObjectEventArgs;

public class InvisibleRectangle implements IEntity {
  private final AABB body;

  public final EventHandler<ObjectEventArgs> onContainsEvent;
  public final EventHandler<ObjectEventArgs> onNotContainsEvent;

  public InvisibleRectangle(float x, float y, float w, float h) {
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
  public void render(Graphics g) {
    // Do nothing
  }

  @Override
  public void setPosition(Vector2 v) {
    body.setPosition(v);
  }

  @Override
  public void setVelocity(Vector2 v) {
    body.addVelocity(v);
  }

  @Override
  public AABB getBody() {
    return body;
  }

  @Override
  public boolean isAlive() {
    return true;
  }

  @Override
  public boolean hasActions() {
    return false;
  }

  @Override
  public Collection<IAction> getActions() {
    return Collections.emptyList();
  }

  @Override
  public void clearActions() {
    // Do nothing
  }

  @Override
  public Entities getType() {
    return Entities.GENERAL;
  }
}
