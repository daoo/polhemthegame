/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package entities.projectiles;

import other.GameTime;
import basics.Vector2;

import components.ICompAnim;
import components.basic.IDynamic;
import components.basic.IRenderable;

import entities.Entity;

public class Projectile extends Entity implements IDynamic, IRenderable {
  private final float duration, range, damage, maxHP;
  private final boolean gravity, collides;

  private boolean       alive;
  private float         hp;

  private Vector2       startPos;
  private float         startTime;

  public Projectile(final float x, final float y,
                    final float width, final float height,
                    final float rot, final float speed, final int targets,
                    final boolean gravity, final boolean collides,
                    final float duration, final float range,
                    final float damage, final ICompAnim renderer,
                    final GameTime time) {
    super(x, y, width, height,
          (float) Math.cos(rot) * speed,
          (float) Math.sin(rot) * speed);

    this.gravity = gravity;
    this.collides = collides;
    this.duration = duration;
    this.range = range;
    this.damage = damage;
    this.maxHP = targets;

    alive = true; 
    hp = targets;

    startPos = new Vector2(x, y);
    startTime = time.getElapsed();

    add(renderer);
  }

  @Override
  public void update(final GameTime time) {
    super.update(time);

    if (gravity) {
      // TODO: Move gravity somewhere else
      body.addVelocity(new Vector2(0, 100.0f * time.getFrameLength()));
    }

    if ((duration != -1) && ((time.getElapsed() - startTime) > duration)) {
      kill();
    }
    if ((range != -1) && (body.getMin().distance(startPos) > range)) {
      kill();
    }
  }

  public boolean canCollide() {
    return collides;
  }

  public float getDamage() {
    return damage;
  }

  @Override
  public void damage(float damage) {
    if (maxHP != -1) {
      hp -= damage;
      if (hp <= 0) {
        hp = 0;
        kill();
      }
    }
  }

  @Override
  public void kill() {
    alive = false;
  }

  @Override
  public boolean isAlive() {
    return alive;
  }
}
