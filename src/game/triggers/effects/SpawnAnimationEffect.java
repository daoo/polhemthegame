/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.components.ComponentMessage;
import game.components.interfaces.IAnimatedComponent;
import game.entities.Entity;
import game.entities.EntityType;
import game.entities.IEntity;
import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;

public class SpawnAnimationEffect implements IEffect {
  private final IEntity owner;
  private final IAnimatedComponent anim;

  public SpawnAnimationEffect(IEntity entity, IAnimatedComponent anim) {
    this.owner = entity;
    this.anim  = anim;
  }

  @Override
  public void execute(GameTime time, World world) {
    Entity e = new Entity(
      owner.getBody().getX1(),
      owner.getBody().getY1(),
      anim.getTileWidth(),
      anim.getTileHeight(),
      EntityType.ANIMATED
    );
    e.addRenderComponent(anim);
    e.sendMessage(ComponentMessage.END_ANIMATION, null);
    world.addFirst(e);
  }
}
