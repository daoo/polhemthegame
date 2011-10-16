/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.actions;

import game.components.ComponentMessage;
import game.components.misc.Damage;
import game.entities.IEntity;
import game.world.World;
import math.Rectangle;
import math.time.GameTime;

/**
 * Inflict damage in an circle around a position when executed.
 */
public class AOEDamage implements IAction {
  final Rectangle body;
  final float range;
  final Damage damage;

  /**
   * Constructs a new AOEDamage action.
   * @param body the body from where ti will become centered
   * @param range the radius of the area
   * @param damage the ammount of damage to deal per object in range
   */
  public AOEDamage(final Rectangle body, final float range, final float damage) {
    this.body = body;
    this.range = range;
    this.damage = new Damage(damage);
  }

  @Override
  public void execute(final GameTime time, final World world) {
    for (final IEntity e : world.getUnits()) {
      if (e.getBody().getCenter().distance(body.getCenter()) < range) {
        e.sendMessage(ComponentMessage.DAMAGE, damage);
      }
    }
  }
}
