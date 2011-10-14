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

  public SpawnAnimated(final float x, final float y,
                       final float width, final float height,
                       final ICompAnim anim) {
    animated = new Entity(x, y, width, height, 0, 0, EntityType.ANIMATED);
    animated.addRenderComponent(anim);
  }

  @Override
  public void execute(final GameTime time, final World world) {
    animated.sendMessage(ComponentMessage.START_ANIMATION, null);
    world.add(animated);
  }
}
