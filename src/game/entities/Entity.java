/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.ILogicComponent;
import game.components.IRenderComponent;
import game.course.World;
import game.triggers.IEffect;
import game.types.GameTime;
import game.types.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import math.Rectangle;

import org.newdawn.slick.Graphics;

import util.Node;
import debug.DebugHelper;

public class Entity implements IEntity {
  private World world;
  private boolean active;

  public final Rectangle body;

  private final LinkedList<IEffect> effects;
  private final ArrayList<ILogicComponent> updates;
  private final ArrayList<IRenderComponent> renders;

  public Entity(float x, float y, int w, int h) {
    body = new Rectangle(x, y, w, h);
    active = true;

    effects = new LinkedList<>();

    updates = new ArrayList<>();
    renders = new ArrayList<>();
  }

  public void addEffect(IEffect effect) {
    effects.add(effect);
  }

  public void addEffects(Collection<? extends IEffect> collection) {
    effects.addAll(collection);
  }

  public void addLogicComponent(ILogicComponent comp) {
    updates.add(comp);
  }

  public void addRenderComponent(IRenderComponent comp) {
    renders.add(comp);
  }

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
  public void sendMessage(Message message, Object args) {
    assert message != null;

    for (ILogicComponent comp : updates) {
      comp.reciveMessage(message, args);
    }

    for (IRenderComponent comp : renders) {
      comp.reciveMessage(message, args);
    }
  }

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
  public boolean isActive() {
    return active || (!effects.isEmpty());
  }

  @Override
  public void remove() {
    active = false;
  }

  @Override
  public String debugString() {
    return "Entity";
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());

    parent.nodes.add(new Node<>("Body = " + body.toString()));
    parent.nodes.add(new Node<>("Active = " + Boolean.toString(active)));

    parent.nodes.add(DebugHelper.listToNode("Logic components", updates));
    parent.nodes.add(DebugHelper.listToNode("Render components", renders));
    parent.nodes.add(DebugHelper.listToNode("Effects", effects));

    return parent;
  }
}
