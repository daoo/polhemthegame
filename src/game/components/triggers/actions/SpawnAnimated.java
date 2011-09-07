/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.triggers.actions;

import game.components.interfaces.ICompAnim;
import game.entities.Animated;
import game.world.World;
import math.time.GameTime;

public class SpawnAnimated implements IAction {
  private final Animated animated;

  public SpawnAnimated(final float x, final float y,
                       final float width, final float height,
                       final ICompAnim anim) {
    animated = new Animated(x, y, width, height, anim);
  }

  @Override
  public void execute(final GameTime time, final World world) {
    animated.start();
    world.add(animated);
  }
}
