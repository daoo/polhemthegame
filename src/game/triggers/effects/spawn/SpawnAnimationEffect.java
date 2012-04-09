/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import game.components.graphics.AnimatedSheet;
import game.components.graphics.animations.RunToAnimator;
import game.entities.Animation;
import game.entities.Entity;
import game.triggers.IEffect;
import game.types.GameTime;
import game.world.World;
import math.Rectangle;

import org.newdawn.slick.Graphics;

/**
 * Spawn an run-to-last animation at the top left of another entity.
 */
public class SpawnAnimationEffect implements IEffect {
  private final Animation spawnee;
  private final Rectangle rect;
  private final AnimatedSheet anim;

  public SpawnAnimationEffect(Entity entity, AnimatedSheet anim,
                              Graphics graphics) {
    assert entity != null && anim != null && graphics != null;

    this.rect = entity.body;
    this.anim = anim;

    spawnee = new Animation(0, 0, anim, graphics);
  }

  @Override
  public void execute(GameTime time, World world) {
    spawnee.setPosition(rect.getMin());

    anim.setAnimator(new RunToAnimator(anim.getTileCount(), anim.getLastTile()));

    world.addMisc(spawnee);
  }
}
