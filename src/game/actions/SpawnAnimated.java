/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.actions;

import game.components.ComponentMessage;
import game.components.interfaces.ICompAnim;
import game.entities.Entity;
import game.entities.groups.EntityType;
import game.world.World;
import math.time.GameTime;

public class SpawnAnimated implements IAction {
  private final Entity animated;

  public SpawnAnimated(float x, float y, float width, float height, ICompAnim anim) {
    animated = new Entity(x, y, width, height, EntityType.ANIMATED);
    animated.addRenderComponent(anim);
  }

  @Override
  public void execute(GameTime time, World world) {
    animated.sendMessage(ComponentMessage.START_ANIMATION, null);
    world.add(animated);
  }
}
