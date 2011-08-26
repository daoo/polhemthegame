/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package entities.projectiles;

import loader.data.json.ProjectilesData.ProjectileData;
import math.Vector2;
import math.time.GameTime;

import components.graphics.animations.Continuous;
import components.interfaces.ICompAnim;
import components.interfaces.IDamagable;
import components.physics.Gravity;

import entities.Entity;

public class Projectile extends Entity implements IDamagable {
  private final float   duration, range, damage, maxHP;
  private final boolean collides;

  private boolean alive;
  private float   hp;

  private final Vector2 startPos;
  private final float   startTime;

  public Projectile(final float x, final float y, final float rot,
                    final ProjectileData data,
                    final ICompAnim renderer, final GameTime time) {
    super(x, y, data.hitbox.width, data.hitbox.height,
          (float) Math.cos(rot) * data.speed,
          (float) Math.sin(rot) * data.speed);
    
    assert (renderer != null);
    assert (data != null);

    collides = data.collides;
    duration = data.duration;
    range = data.range;
    damage = data.damage;

    maxHP = data.targets;
    alive = true;
    hp = maxHP;

    startPos = new Vector2(x, y);
    startTime = time.getElapsed();

    if (data.gravity) {
      add(new Gravity(body, Gravity.FACTOR));
    }

    renderer.setAnimator(new Continuous(renderer.getTileCount()));
    add(renderer);
  }

  @Override
  public void update(final GameTime time) {
    super.update(time);

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
  public void damage(final float value) {
    if (maxHP != -1) {
      hp -= value;
      if (hp <= 0) {
        hp = 0;
        kill();
      }
    }
  }

  @Override
  public void kill() {
  }

  @Override
  public boolean isAlive() {
    return alive;
  }
}
