/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.interfaces.IRenderComponent;
import game.events.EventHandler;
import game.events.impl.ObjectEventArgs;
import game.pods.GameTime;
import game.world.World;
import math.Rectangle;

import org.newdawn.slick.Graphics;

import util.Node;

public class InvisibleRectangle implements IEntity {
  public final EventHandler<ObjectEventArgs> onContainsEvent;
  public final EventHandler<ObjectEventArgs> onNotContainsEvent;

  private final Rectangle body;
  private World world;

  public InvisibleRectangle(float x, float y, float w, float h) {
    body = new Rectangle(x, y, w, h);

    onContainsEvent    = new EventHandler<>();
    onNotContainsEvent = new EventHandler<>();
  }

  @Override
  public void addLogicComponent(ILogicComponent comp) {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public void addRenderComponent(IRenderComponent comp) {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public Rectangle getBody() {
    return body;
  }

  @Override
  public ILogicComponent getComponent(ComponentType componentType) {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public EntityType getType() {
    return EntityType.GENERAL;
  }

  @Override
  public World getWorld() {
    return world;
  }

  @Override
  public void render(Graphics g) {
    // Do nothing
  }

  @Override
  public void sendMessage(ComponentMessage message, Object args) {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public void setWorld(World world) {
    this.world = world;
  }

  @Override
  public void update(GameTime time) {
    for (IEntity e : world.getUnits()) {
      if (body.isContaining(e.getBody())) {
        onContainsEvent.execute(this, new ObjectEventArgs(world, e));
      } else {
        onNotContainsEvent.execute(this, new ObjectEventArgs(world, e));
      }
    }
  }

  @Override
  public boolean isActive() {
    return true;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  public String toString() {
    return getClass().getName();
  }

  @Override
  public Node<Object> debugInfo() {

    Node<Object> parent = new Node<Object>(this);

    parent.nodes.add(new Node<Object>("Body = " + body.toString()));

    return parent;
  }
}
