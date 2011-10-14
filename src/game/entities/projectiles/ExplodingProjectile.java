/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities.projectiles;

import game.actions.AOEDamage;
import game.actions.SpawnRunToEndAnim;
import game.components.ComponentMessage;
import game.components.interfaces.ICompAnim;
import loader.data.json.ProjectilesData.ProjectileData;
import math.time.GameTime;


public class ExplodingProjectile extends Projectile {
  private final float        aoeRange, aoeDamage;

  private final ICompAnim    explosion;

  public ExplodingProjectile(final float x, final float y, final float rot,
                             final ProjectileData data,
                             final ICompAnim renderer, final ICompAnim explosion,
                             final GameTime time) {
    super(x, y, rot, data, renderer, time);

    aoeRange  = data.aoe.radius;
    aoeDamage = data.aoe.damage;

    this.explosion = explosion;
  }

  @Override
  public void sendMessage(final ComponentMessage message, final Object args) {
    super.sendMessage(message, args);

    if (message == ComponentMessage.KILL) {
      addAction(new AOEDamage(body.getCenter(), aoeRange, aoeDamage));
      addAction(new SpawnRunToEndAnim(body.getX1(), body.getY1(),
                                      explosion.getTileWidth(),
                                      explosion.getTileHeight(),
                                      explosion));
    }
  }
}
