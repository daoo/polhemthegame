/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package entities;

import math.time.GameTime;

import org.newdawn.slick.Color;

import ui.infobar.Bar;
import ui.infobar.InfoBar;

import components.ComponentMessages;
import components.basic.Life;
import components.graphics.animations.Continuous;
import components.graphics.animations.Idle;
import components.interfaces.ICompAnim;
import components.physics.AABB;
import components.triggers.actions.SpawnRunToEndAnim;
import entities.interfaces.IDamagable;
import entities.interfaces.IWalking;

public class Unit extends Entity implements IDamagable, IWalking {
  private final Life life;

  private final InfoBar infoBar;
  private final Bar hpBar;

  private final ICompAnim walk;
  private final ICompAnim death;

  public Unit(final float x, final float y,
              final float width, final float height,
              final float dx, final float dy,
              final int maxHP,
              final ICompAnim walk, final ICompAnim death) {
    super(x, y, width, height, dx, dy);

    this.walk = walk;
    this.death = death;

    life = new Life(this, maxHP);

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
    hpBar.setFraction(life.getHPFraction());
  }

  @Override
  public boolean isAlive() {
    return life.isAlive();
  }

  @Override
  public void kill() {
    life.kill();
  }

  @Override
  public AABB getBody() {
    return body;
  }

  @Override
  public void damage(final float damage) {
    life.damage(damage);
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
  public void sendMessage(ComponentMessages message) {
    if (message == ComponentMessages.KILLED) {
      clearComponents();
      actions.add(new SpawnRunToEndAnim(body.getX1(), body.getY1(),
                                        death.getTileWidth(), death.getTileHeight(),
                                        death));
    }
  
    super.sendMessage(message);
  }
}
