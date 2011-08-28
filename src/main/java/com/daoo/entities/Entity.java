/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.entities;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.Graphics;

import com.daoo.components.ComponentMessages;
import com.daoo.components.interfaces.IComp;
import com.daoo.components.interfaces.ICompRender;
import com.daoo.components.interfaces.ICompUpRend;
import com.daoo.components.interfaces.ICompUpdate;
import com.daoo.components.physics.AABB;
import com.daoo.components.triggers.actions.IAction;
import com.daoo.entities.interfaces.IEntity;
import com.daoo.game.World;
import com.daoo.math.Vector2;
import com.daoo.math.time.GameTime;

public class Entity implements IEntity {
  protected final AABB body;

  protected final ArrayList<IAction> actions;

  private final ArrayList<IComp>       all;
  private final ArrayList<ICompUpdate> updates;
  private final ArrayList<ICompRender> renders;

  public Entity(final float x, final float y,
                final float w, final float h,
                final float dx, final float dy) {
    body = new AABB(x, y, w, h, dx, dy);

    actions = new ArrayList<IAction>();

    all = new ArrayList<IComp>();
    updates = new ArrayList<ICompUpdate>();
    renders = new ArrayList<ICompRender>();
  }

  @Override
  public void update(final GameTime time, final World world) {
    body.integrate(time.getFrameLength());

    for (final ICompUpdate comp : updates) {
      comp.update(time);
    }
  }

  @Override
  public void render(final Graphics g) {
    g.pushTransform();
    g.translate(body.getX1(), body.getY1());

    for (final ICompRender comp : renders) {
      comp.render(g);
    }

    g.popTransform();
  }

  public void add(final ICompUpRend comp) {
    all.add(comp);
    updates.add(comp);
    renders.add(comp);
  }

  public void add(final ICompRender comp) {
    all.add(comp);
    renders.add(comp);
  }

  public void add(final ICompUpdate comp) {
    all.add(comp);
    updates.add(comp);
  }

  public void clearComponents() {
    updates.clear();
    renders.clear();
  }

  @Override
  public void setPosition(final Vector2 v) {
    body.setPosition(v);
  }

  @Override
  public void setVelocity(final Vector2 v) {
    body.setVelocity(v);
  }

  @Override
  public AABB getBody() {
    return body;
  }

  public void sendMessage(ComponentMessages message) {
    for (IComp comp : all) {
      comp.reciveMessage(message);
    }
  }

  @Override
  public boolean hasActions() {
    return !actions.isEmpty();
  }

  @Override
  public Collection<IAction> getActions() {
    return actions;
  }

  @Override
  public void clearActions() {
    actions.clear();
  }

  @Override
  public boolean isAlive() {
    return true;
  }
}
