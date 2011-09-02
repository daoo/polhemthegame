/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.triggers.actions;

import com.daoo.components.interfaces.ICompAnim;
import com.daoo.entities.Animated;
import com.daoo.game.World;
import com.daoo.math.time.GameTime;

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
    world.addAnimated(animated);
  }
}
