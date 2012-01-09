/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import game.components.graphics.animations.RunTo;
import game.components.interfaces.IAnimatedComponent;
import game.components.misc.AfterAnimation;
import game.entities.Entity;
import game.pods.GameTime;
import game.triggers.IEffect;
import game.triggers.effects.RemoveEntityEffect;
import game.triggers.effects.RenderCurrentEffect;
import game.world.World;
import math.Rectangle;

import org.newdawn.slick.Graphics;

/**
 * Spawn an run-to-last animation at the top left of another entity.
 */
public class SpawnAnimationEffect implements IEffect {
  private final Entity spawnee;
  private final Rectangle rect;
  private final IAnimatedComponent anim;

  public SpawnAnimationEffect(Entity entity, IAnimatedComponent anim, Graphics graphics) {
    this.rect = entity.getBody();
    this.anim = anim;

    spawnee = new Entity(0, 0, anim.getTileWidth(), anim.getTileHeight());
    spawnee.addRenderComponent(anim);

    AfterAnimation comp = new AfterAnimation(spawnee, anim, anim.getLastTile());
    comp.add(new RenderCurrentEffect(rect, anim, graphics));
    comp.add(new RemoveEntityEffect(spawnee));
    spawnee.addLogicComponent(comp);
  }

  @Override
  public void execute(GameTime time, World world) {
    spawnee.getBody().setPosition(rect.getMin());

    anim.setAnimator(new RunTo(anim.getTileCount(), anim.getLastTile()));

    world.addMisc(spawnee);
  }
}
