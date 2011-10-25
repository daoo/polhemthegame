/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
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
import math.Rectangle;

public class SpawnDeathAnim implements IEffect {
  private final Rectangle body;
  private final float width, height;
  private final IAnimatedComponent anim;

  public SpawnDeathAnim(Rectangle body, float width, float height, IAnimatedComponent anim) {
    this.body   = body;
    this.width  = width;
    this.height = height;
    this.anim   = anim;
  }

  @Override
  public void execute(GameTime time, World world) {
    IEntity e = new Entity(body.getX1(), body.getY1(), width, height,
                                 EntityType.ANIMATED);
    e.addRenderComponent(anim);
    e.sendMessage(ComponentMessage.END_ANIMATION, null);
    world.add(e);
  }
}
