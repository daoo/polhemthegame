/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package entities;

import java.util.ArrayList;
import java.util.Collection;

import math.Vector2;
import math.time.GameTime;

import org.newdawn.slick.Color;

import ui.infobar.Bar;
import ui.infobar.InfoBar;

import components.graphics.animations.Continuous;
import components.graphics.animations.Idle;
import components.interfaces.ICompAnim;
import components.interfaces.IUnit;
import components.physics.AABB;
import components.triggers.actions.IAction;
import components.triggers.actions.SpawnRunToEndAnim;

public class Unit extends Entity implements IUnit {
  private final float                maxHP;
  private float                      hp;
  private boolean                    alive;

  private final InfoBar              infoBar;
  private final Bar                  hpBar;

  private final ICompAnim            walk;
  private final ICompAnim            death;

  protected final ArrayList<IAction> actions;

  public Unit(final float x, final float y,
              final float width, final float height,
              final float dx, final float dy,
              final int maxHP,
              final ICompAnim walk, final ICompAnim death) {
    super(x, y, width, height, dx, dy);
    actions = new ArrayList<IAction>();

    this.walk = walk;
    this.death = death;

    hp = maxHP;
    this.maxHP = maxHP;
    alive = true;

    add(walk);

    infoBar = new InfoBar(width, 2, 0, -6);
    hpBar = new Bar(Color.green, Color.red);
    infoBar.add(hpBar);

    add(infoBar);
  }

  protected void addBar(final Bar bar) {
    infoBar.add(bar);
  }

  @Override
  public void update(final GameTime time) {
    super.update(time);
    hpBar.setFraction(hp / maxHP);
  }

  @Override
  public boolean isAlive() {
    return alive;
  }

  @Override
  public void killSilently() {
    alive = false;
  }

  @Override
  public void kill() {
    if (isAlive()) {
      killSilently();

      clearComponents();
      actions.add(new SpawnRunToEndAnim(body.getX1(), body.getY1(),
                                        death.getTileWidth(), death.getTileHeight(),
                                        death));
    }
  }

  @Override
  public AABB getBody() {
    return body;
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
  public void damage(final float damage) {
    hp -= damage;

    if (hp <= 0) {
      hp = 0;
      kill();
    }
  }

  @Override
  public void start() {
    walk.setAnimator(new Continuous(walk.getTileCount()));
  }

  @Override
  public void stop() {
    if (!walk.getAnimator().isFinished()) {
      walk.goToFirstFrame();
      walk.setAnimator(new Idle());
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
}
