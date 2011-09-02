/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.entities.projectiles;

import com.daoo.components.interfaces.ICompAnim;
import com.daoo.components.triggers.actions.AOEDamage;
import com.daoo.components.triggers.actions.SpawnRunToEndAnim;
import com.daoo.loader.data.json.ProjectilesData.ProjectileData;
import com.daoo.math.time.GameTime;

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
