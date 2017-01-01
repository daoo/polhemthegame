/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import org.newdawn.slick.Graphics;

import game.components.graphics.AnimatedSheet;
import game.components.graphics.animations.RunToAnimator;
import game.course.World;
import game.entities.Animation;
import game.entities.Entity;
import game.triggers.IEffect;
import game.types.GameTime;
import math.Aabb;

/**
 * Spawn an run-to-last animation at the top left of another entity.
 */
public class SpawnAnimationEffect implements IEffect {
  private final Animation mSpawnee;
  private final Aabb mBox;
  private final AnimatedSheet mAnim;

  public SpawnAnimationEffect(Entity entity, AnimatedSheet anim, Graphics graphics) {
    assert entity != null && anim != null && graphics != null;

    mBox = entity.getBody();
    mAnim = anim;

    mSpawnee = new Animation(0, 0, anim, graphics);
  }

  @Override
  public void execute(GameTime time, World world) {
    mSpawnee.setPosition(mBox.getMin());

    mAnim.setAnimator(new RunToAnimator(mAnim.getTileCount(), mAnim.getLastTile()));

    world.addMisc(mSpawnee);
  }
}
