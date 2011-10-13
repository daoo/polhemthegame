/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities.projectiles;

import game.components.ComponentMessage;
import game.components.basic.Life;
import game.components.graphics.animations.Continuous;
import game.components.interfaces.ICompAnim;
import game.components.physics.Gravity;
import game.entities.Entity;
import game.entities.groups.EntityType;
import game.world.World;
import loader.data.json.ProjectilesData.ProjectileData;
import math.Vector2;
import math.time.GameTime;

public class Projectile extends Entity {
  private final float   duration, range, damage;
  private final boolean collides;

  private final Vector2 startPos;
  private final float   startTime;

  public Projectile(final float x, final float y, final float rot,
                    final ProjectileData data,
                    final ICompAnim renderer, final GameTime time) {
    super(x, y, data.hitbox.width, data.hitbox.height,
          (float) Math.cos(rot) * data.speed,
          (float) Math.sin(rot) * data.speed,
          EntityType.PROJECTILE);

    assert (renderer != null);
    assert (data != null);

    collides = data.collides;
    duration = data.duration;
    range = data.range;
    damage = data.damage;

    addLogicComponent(new Life(data.targets));

    startPos = new Vector2(x, y);
    startTime = time.getElapsed();

    if (data.gravity) {
      addLogicComponent(new Gravity());
    }

    renderer.setAnimator(new Continuous(renderer.getTileCount()));
    addRenderComponent(renderer);
  }

  @Override
  public void update(final GameTime time, final World world) {
    super.update(time, world);

    if ((duration != -1) && ((time.getElapsed() - startTime) > duration)) {
      sendMessage(ComponentMessage.KILL, null);
    }
    if ((range != -1) && (body.getMin().distance(startPos) > range)) {
      sendMessage(ComponentMessage.KILL, null);
    }
  }

  public boolean canCollide() {
    return collides;
  }

  public float getDamage() {
    return damage;
  }
}
