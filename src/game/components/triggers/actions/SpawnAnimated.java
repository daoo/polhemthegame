/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.triggers.actions;

import game.components.graphics.animations.Continuous;
import game.components.interfaces.ICompAnim;
import game.entities.Entity;
import game.entities.groups.Entities;
import game.world.World;
import math.time.GameTime;

public class SpawnAnimated implements IAction {
  private final ICompAnim anim;
  private final Entity animated;

  public SpawnAnimated(final float x, final float y,
                       final float width, final float height,
                       final ICompAnim anim) {
    this.anim = anim;
    animated = new Entity(x, y, width, height, 0, 0, Entities.ANIMATED);
    animated.add(anim);
  }

  @Override
  public void execute(final GameTime time, final World world) {
    anim.setAnimator(new Continuous(anim.getTileCount()));
    world.add(animated);
  }
}
