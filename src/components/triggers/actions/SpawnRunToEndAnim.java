/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.triggers.actions;

import main.World;

import components.interfaces.ICompAnim;

import entities.Animated;

public class SpawnRunToEndAnim implements IAction {
  private final Animated animated;

  public SpawnRunToEndAnim(final float x, final float y,
                        final float width, final float height,
                        final ICompAnim anim) {
    animated = new Animated(x, y, width, height, anim);
  }

  @Override
  public void execute(final World world) {
    animated.stop();
    world.add(animated);
  }
}
