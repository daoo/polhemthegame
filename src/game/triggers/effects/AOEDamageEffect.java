/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.course.World;
import game.entities.Entity;
import game.entities.IEntity;
import game.triggers.IEffect;
import game.types.Damage;
import game.types.GameTime;
import game.types.Message;
import math.Rectangle;
import math.Vector2;

/**
 * Inflict damage in an circle around a position when executed.
 */
public class AOEDamageEffect implements IEffect {
  private final Rectangle body;
  private final float range;
  private final Damage damage;

  /**
   * Constructs a new AOEDamage effect.
   * @param source the source of the damage
   * @param body the body from where the circle will be centered
   * @param range the radius of the area
   * @param damage the amount of damage to deal per object in range
   */
  public AOEDamageEffect(IEntity source, Rectangle body, float range, float damage) {
    this.body = body;
    this.range = range;
    this.damage = new Damage(source, damage);
  }

  @Override
  public void execute(GameTime time, World world) {
    for (Entity e : world.getUnits()) {
      if (Vector2.distance(e.body.getCenter(), body.getCenter()) < range) {
        e.sendMessage(Message.DAMAGE, damage);
      }
    }
  }
}
