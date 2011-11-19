/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.interfaces.IRenderComponent;
import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;

import java.util.Collection;
import java.util.LinkedList;

import math.Rectangle;

import org.newdawn.slick.Graphics;

public class Entity implements IEntity {
  private final Rectangle body;
  private final EntityType type;
  private World world;
  private boolean active;

  private final LinkedList<IEffect> effects;
  private final LinkedList<ILogicComponent> updates;
  private final LinkedList<IRenderComponent> renders;

  public Entity(float x, float y, float w, float h, EntityType type) {
    body = new Rectangle(x, y, w, h);
    active = true;

    this.type = type;

    effects = new LinkedList<IEffect>();

    updates = new LinkedList<ILogicComponent>();
    renders = new LinkedList<IRenderComponent>();
  }

  public void addEffect(IEffect effect) {
    effects.add(effect);
  }

  public void addEffects(Collection<IEffect> collection) {
    effects.addAll(collection);
  }

  @Override
  public void addLogicComponent(ILogicComponent comp) {
    updates.add(comp);
  }

  @Override
  public void addRenderComponent(IRenderComponent comp) {
    renders.add(comp);
  }

  @Override
  public Rectangle getBody() {
    return body;
  }

  @Override
  public ILogicComponent getComponent(ComponentType componentType) {
    for (ILogicComponent comp : updates) {
      if (comp.getComponentType().equals(componentType)) {
        return comp;
      }
    }

    for (ILogicComponent comp : renders) {
      if (comp.getComponentType().equals(componentType)) {
        return comp;
      }
    }

    return null;
  }

  @Override
  public EntityType getType() {
    return type;
  }

  @Override
  public World getWorld() {
    return world;
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(body.getX1(), body.getY1());

    for (IRenderComponent comp : renders) {
      comp.render(g);
    }

    g.popTransform();
  }

  @Override
  public void sendMessage(ComponentMessage message, Object args) {
    for (ILogicComponent comp : updates) {
      comp.reciveMessage(message, args);
    }

    for (IRenderComponent comp : renders) {
      comp.reciveMessage(message, args);
    }
  }

  @Override
  public void setWorld(World world) {
    this.world = world;
  }

  @Override
  public void update(GameTime time) {
    for (ILogicComponent comp : updates) {
      comp.update(time);
    }

    for (IRenderComponent comp : renders) {
      comp.update(time);
    }

    for (IEffect effect : effects) {
      effect.execute(time, world);
    }
    effects.clear();
  }

  @Override
  public boolean equals(IEntity other) {
    return this == other;
  }

  @Override
  public boolean isActive() {
    return active || (!effects.isEmpty());
  }

  @Override
  public void remove() {
    active = false;
  }
}
