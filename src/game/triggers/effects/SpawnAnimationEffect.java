/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.components.graphics.animations.RunTo;
import game.components.interfaces.IAnimatedComponent;
import game.components.misc.AfterAnimation;
import game.entities.Entity;
import game.entities.EntityType;
import game.pods.GameTime;
import game.triggers.IEffect;
import game.world.World;

import org.newdawn.slick.Graphics;

/**
 * Spawn an run-to-last animation at the top left of another entity.
 */
public class SpawnAnimationEffect implements IEffect {
  private final Entity owner, spawnee;
  private final IAnimatedComponent anim;

  public SpawnAnimationEffect(Entity entity, IAnimatedComponent anim, Graphics graphics) {
    this.owner = entity;
    this.anim = anim;

    spawnee = new Entity(
      owner.getBody().getX1(),
      owner.getBody().getY1(),
      anim.getTileWidth(),
      anim.getTileHeight(),
      EntityType.ANIMATED
    );
    spawnee.addRenderComponent(anim);

    spawnee.addLogicComponent(
      new AfterAnimation(owner, anim, anim.getLastTile(),
        new RenderCurrent(anim, graphics)));

  }

  @Override
  public void execute(GameTime time, World world) {
    spawnee.getBody().setPosition(owner.getBody().getMin());

    anim.setAnimator(new RunTo(anim.getTileCount(), anim.getLastTile()));

    world.addFirst(spawnee);
  }
}
