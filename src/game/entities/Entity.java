/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.ComponentMessages;
import game.components.interfaces.IComp;
import game.components.interfaces.ICompRender;
import game.components.interfaces.ICompUpRend;
import game.components.interfaces.ICompUpdate;
import game.components.physics.AABB;
import game.components.triggers.actions.IAction;
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

  private final ArrayList<IComp>       all;
  private final ArrayList<ICompUpdate> updates;
  private final ArrayList<ICompRender> renders;

  public Entity(final float x, final float y,
                final float w, final float h,
                final float dx, final float dy,
                final EntityType type) {
    body = new AABB(x, y, w, h, dx, dy);

    actions = new ArrayList<IAction>();

    all = new ArrayList<IComp>();
    updates = new ArrayList<ICompUpdate>();
    renders = new ArrayList<ICompRender>();

    this.type = type;
  }

  @Override
  public void update(final GameTime time, final World world) {
    body.integrate(time.getFrameLength());

    for (final ICompUpdate comp : updates) {
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
  public AABB getBody() {
    return body;
  }

  public void sendMessage(final ComponentMessages message) {
    for (final IComp comp : all) {
      comp.reciveMessage(message);
    }
  }

  @Override
  public EntityType getType() {
    return type;
  }

  protected void addAction(final IAction action) {
    actions.add(action);
  }
}
