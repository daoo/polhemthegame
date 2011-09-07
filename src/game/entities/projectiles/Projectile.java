/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities.projectiles;

import loader.data.json.ProjectilesData.ProjectileData;
import math.Vector2;
import math.time.GameTime;


import game.World;
import game.components.basic.Life;
import game.components.graphics.animations.Continuous;
import game.components.interfaces.ICompAnim;
import game.components.physics.Gravity;
import game.entities.Entity;
import game.entities.interfaces.IDamagable;

public class Projectile extends Entity implements IDamagable {
  private final float   duration, range, damage;
  private final boolean collides;

  private final Life life;

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

    life = new Life(this, data.targets);

    startPos = new Vector2(x, y);
    startTime = time.getElapsed();

    if (data.gravity) {
      add(new Gravity(body, Gravity.FACTOR));
    }

    renderer.setAnimator(new Continuous(renderer.getTileCount()));
    add(renderer);
  }

  @Override
  public void update(final GameTime time, final World world) {
    super.update(time, world);

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
    life.damage(value);
  }

  @Override
  public void kill() {
    life.kill();
  }

  @Override
  public boolean isAlive() {
    return life.isAlive();
  }
}