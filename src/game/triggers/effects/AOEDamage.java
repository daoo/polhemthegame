/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.components.ComponentMessage;
import game.entities.IEntity;
import game.pods.Damage;
import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;
import math.Rectangle;

/**
 * Inflict damage in an circle around a position when executed.
 */
public class AOEDamage implements IEffect {
  private final Rectangle body;
  private final float range;
  private final Damage damage;

  /**
   * Constructs a new AOEDamage effect.
   * @param source the source of the damage
   * @param body the body from where the circle will be centered
   * @param range the radius of the area
   * @param damage the ammount of damage to deal per object in range
   */
  public AOEDamage(IEntity source, Rectangle body, float range, float damage) {
    this.body = body;
    this.range = range;
    this.damage = new Damage(source, damage);
  }

  @Override
  public void execute(GameTime time, World world) {
    for (IEntity e : world.getUnits()) {
      if (e.getBody().getCenter().distance(body.getCenter()) < range) {
        e.sendMessage(ComponentMessage.DAMAGE, damage);
      }
    }
  }
}
