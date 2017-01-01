/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import game.components.graphics.AnimatedSheet;
import game.components.graphics.animations.RunToAnimator;
import game.course.World;
import game.entities.Animation;
import game.entities.EntityImpl;
import game.triggers.Effect;
import game.types.GameTime;
import math.Aabb;

/**
 * Spawn an run-to-last animation at the top left of another entity.
 */
public class SpawnAnimationEffect implements Effect {
  private final Aabb mBox;
  private final AnimatedSheet mAnim;

  public SpawnAnimationEffect(EntityImpl entity, AnimatedSheet anim) {
    assert entity != null && anim != null;

    mBox = entity.getBody();
    mAnim = anim;
  }

  @Override
  public void execute(GameTime time, World world) {
    mAnim.setAnimator(new RunToAnimator(mAnim.getTileCount(), mAnim.getLastTile()));
    world.addMisc(new Animation(mBox.getMin(), mAnim));
  }
}
