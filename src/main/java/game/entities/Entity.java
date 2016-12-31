/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import debug.DebugHelper;
import game.components.ILogicComponent;
import game.components.IRenderComponent;
import game.course.World;
import game.triggers.IEffect;
import game.types.GameTime;
import game.types.Message;
import math.Rectangle;
import util.Node;

public class Entity implements IEntity {
  private World mWorld;
  private boolean mActive;

  private final Rectangle mBody;

  private final LinkedList<IEffect> mEffects;
  private final ArrayList<ILogicComponent> mUpdates;
  private final ArrayList<IRenderComponent> mRenders;

  public Entity(float x, float y, int w, int h) {
    mBody = new Rectangle(x, y, w, h);
    mActive = true;

    mEffects = new LinkedList<>();

    mUpdates = new ArrayList<>();
    mRenders = new ArrayList<>();
  }

  public void addEffect(IEffect effect) {
    mEffects.add(effect);
  }

  public void addEffects(Collection<? extends IEffect> collection) {
    mEffects.addAll(collection);
  }

  public void addLogicComponent(ILogicComponent comp) {
    mUpdates.add(comp);
  }

  public void addRenderComponent(IRenderComponent comp) {
    mRenders.add(comp);
  }

  public Rectangle getBody() {
    return mBody;
  }

  public World getWorld() {
    return mWorld;
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(mBody.getX1(), mBody.getY1());

    for (IRenderComponent comp : mRenders) {
      comp.render(g);
    }

    g.popTransform();
  }

  @Override
  public void sendMessage(Message message, Object args) {
    assert message != null;

    for (ILogicComponent comp : mUpdates) {
      comp.reciveMessage(message, args);
    }

    for (IRenderComponent comp : mRenders) {
      comp.reciveMessage(message, args);
    }
  }

  public void setWorld(World world) {
    mWorld = world;
  }

  @Override
  public void update(GameTime time) {
    for (ILogicComponent comp : mUpdates) {
      comp.update(time);
    }

    for (IRenderComponent comp : mRenders) {
      comp.update(time);
    }

    for (IEffect effect : mEffects) {
      effect.execute(time, mWorld);
    }
    mEffects.clear();
  }

  @Override
  public boolean isActive() {
    return mActive || !mEffects.isEmpty();
  }

  @Override
  public void remove() {
    mActive = false;
  }

  @Override
  public String debugString() {
    return "Entity";
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());

    parent.nodes.add(new Node<>("Body = " + mBody));
    parent.nodes.add(new Node<>("Active = " + Boolean.toString(mActive)));

    parent.nodes.add(DebugHelper.listToNode("Logic components", mUpdates));
    parent.nodes.add(DebugHelper.listToNode("Render components", mRenders));
    parent.nodes.add(DebugHelper.listToNode("Effects", mEffects));

    return parent;
  }
}
