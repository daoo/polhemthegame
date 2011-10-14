/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.actions.IAction;
import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.interfaces.IRenderComponent;
import game.components.physics.AABB;
import game.entities.groups.EntityType;
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
    for (final IEntity e : world.getUnits()) {
      if (body.isContaining(e.getBody())) {
        onContainsEvent.execute(this, new ObjectEventArgs(world, e));
      } else {
        onNotContainsEvent.execute(this, new ObjectEventArgs(world, e));
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
  public EntityType getType() {
    return EntityType.GENERAL;
  }

  @Override
  public ILogicComponent getComponent(final ComponentType componentType) {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public void sendMessage(final ComponentMessage message, final Object args) {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public void addRenderComponent(final IRenderComponent comp) {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public void addLogicComponent(final ILogicComponent comp) {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public void clearComponents() {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public void addAction(final IAction action) {
    throw new UnsupportedOperationException("Not allowed");
  }
}
