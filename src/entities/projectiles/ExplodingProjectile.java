/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package entities.projectiles;

import loader.data.json.ProjectilesData.ProjectileData;
import math.time.GameTime;

import components.interfaces.ICompAnim;
import components.triggers.actions.AOEDamage;
import components.triggers.actions.SpawnRunToEndAnim;

public class ExplodingProjectile extends Projectile {
  private final float        aoeRange, aoeDamage;

  private final ICompAnim    explosion;

  public ExplodingProjectile(final float x, final float y, final float rot,
                             final ProjectileData data,
                             final ICompAnim renderer, final ICompAnim explosion,
                             final GameTime time) {
    super(x, y, rot, data, renderer, time);

    aoeRange = data.aoe.radius;
    aoeDamage = data.aoe.damage;

    this.explosion = explosion;
  }

  @Override
  public void kill() {
    super.kill();

    actions.add(new AOEDamage(body.getCenter(), aoeRange, aoeDamage));
    actions.add(new SpawnRunToEndAnim(body.getX1(), body.getY1(),
                                      explosion.getTileWidth(),
                                      explosion.getTileHeight(),
                                      explosion));
  }
}
