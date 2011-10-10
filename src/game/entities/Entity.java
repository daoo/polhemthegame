/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.interfaces.IRenderComponent;
import game.components.misc.IAction;
import game.components.physics.AABB;
import game.entities.groups.EntityType;
import game.entities.interfaces.IEntity;
import game.world.World;

import java.util.ArrayList;

import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class Entity implements IEntity {
  protected final AABB body;

  private final ArrayList<IAction> actions;

  private final EntityType type;

  private final ArrayList<ILogicComponent> updates;
  private final ArrayList<IRenderComponent> renders;

  public Entity(final float x, final float y,
                final float w, final float h,
                final float dx, final float dy,
                final EntityType type) {
    body = new AABB(x, y, w, h, dx, dy);

    actions = new ArrayList<IAction>();

    updates = new ArrayList<ILogicComponent>();
    renders = new ArrayList<IRenderComponent>();

    this.type = type;
  }

  @Override
  public void update(final GameTime time, final World world) {
    body.integrate(time.getFrameLength());

    for (final ILogicComponent comp : updates) {
      comp.update(time);
    }

    for (final IAction action : actions) {
      action.execute(time, world);
    }
    actions.clear();
  }

  @Override
  public void render(final Graphics g) {
    g.pushTransform();
    g.translate(body.getX1(), body.getY1());

    for (final IRenderComponent comp : renders) {
      comp.render(g);
    }

    g.popTransform();
  }

  public void addRenderComponent(final IRenderComponent comp) {
    comp.setOwner(this);
    renders.add(comp);
  }

  public void addLogicComponent(final ILogicComponent comp) {
    comp.setOwner(this);
    updates.add(comp);
  }

  public void clearComponents() {
    updates.clear();
    renders.clear();
  }

  @Override
  public AABB getBody() {
    return body;
  }

  @Override
  public void sendMessage(final ComponentMessage message, final Object args) {
    for (final ILogicComponent comp : updates) {
      comp.reciveMessage(message, args);
    }

    for (final IRenderComponent comp : renders) {
      comp.reciveMessage(message, args);
    }
  }

  @Override
  public EntityType getType() {
    return type;
  }

  public void addAction(final IAction action) {
    actions.add(action);
  }

  @Override
  public ILogicComponent getComponent(final ComponentType componentType) {
    for (final ILogicComponent comp : updates) {
      if (comp.getComponentType().equals(componentType)) {
        return comp;
      }
    }
    
    for (final ILogicComponent comp : renders) {
      if (comp.getComponentType().equals(componentType)) {
        return comp;
      }
    }
    
    throw new UnsupportedOperationException("THIS IS SPARTA!");
  }
}
