/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import debug.DebugHelper;
import game.components.LogicComponent;
import game.components.RenderComponent;
import game.course.World;
import game.triggers.Effect;
import game.types.GameTime;
import game.types.Message;
import math.Aabb;
import math.Rectangle;
import math.Vector2;
import util.Node;

/**
 * Generic implementation of an entity with logic and rendering components.
 */
public class EntityImpl implements Entity {
  private World mWorld;
  private boolean mActive;

  private final Aabb mBody;

  private final LinkedList<Effect> mEffects;
  private final ArrayList<LogicComponent> mUpdates;
  private final ArrayList<RenderComponent> mRenders;

  public EntityImpl(float x, float y, int w, int h) {
    mBody = new Aabb(new Vector2(x, y), new Rectangle(w, h));
    mActive = true;

    mEffects = new LinkedList<>();

    mUpdates = new ArrayList<>();
    mRenders = new ArrayList<>();
  }

  public void addEffect(Effect effect) {
    mEffects.add(effect);
  }

  public void addEffects(Collection<? extends Effect> collection) {
    mEffects.addAll(collection);
  }

  public void addLogicComponent(LogicComponent comp) {
    mUpdates.add(comp);
  }

  public void addRenderComponent(RenderComponent comp) {
    mRenders.add(comp);
  }

  public Aabb getBody() {
    return mBody;
  }

  public World getWorld() {
    return mWorld;
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(mBody.getMin().x, mBody.getMin().y);

    for (RenderComponent comp : mRenders) {
      comp.render(g);
    }

    g.popTransform();
  }

  @Override
  public void sendMessage(Message message, Object args) {
    assert message != null;

    for (LogicComponent comp : mUpdates) {
      comp.reciveMessage(message, args);
    }

    for (RenderComponent comp : mRenders) {
      comp.reciveMessage(message, args);
    }
  }

  public void setWorld(World world) {
    mWorld = world;
  }

  @Override
  public void update(GameTime time) {
    for (LogicComponent comp : mUpdates) {
      comp.update(time);
    }

    for (RenderComponent comp : mRenders) {
      comp.update(time);
    }

    for (Effect effect : mEffects) {
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
    return "EntityImpl";
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
