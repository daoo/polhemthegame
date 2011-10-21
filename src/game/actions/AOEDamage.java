/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.actions;

import game.components.ComponentMessage;
import game.components.life.Damage;
import game.entities.IEntity;
import game.world.World;
import math.Rectangle;
import math.time.GameTime;

/**
 * Inflict damage in an circle around a position when executed.
 */
public class AOEDamage implements IAction {
  private final Rectangle body;
  private final float range;
  private final Damage damage;

  /**
   * Constructs a new AOEDamage action.
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
