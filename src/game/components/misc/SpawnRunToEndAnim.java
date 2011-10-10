/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.graphics.animations.RunTo;
import game.components.interfaces.ICompAnim;
import game.entities.Entity;
import game.entities.groups.EntityType;
import game.world.World;
import math.time.GameTime;

public class SpawnRunToEndAnim implements IAction {
  private final ICompAnim anim;
  private final Entity animated;

  public SpawnRunToEndAnim(final float x, final float y,
                        final float width, final float height,
                        final ICompAnim anim) {
    this.anim = anim;
    animated = new Entity(x, y, width, height, 0, 0, EntityType.ANIMATED);
  }

  @Override
  public void execute(final GameTime time, final World world) {
    anim.setAnimator(new RunTo(anim.getTileCount(), anim.getLastTile()));
    world.add(animated);
  }
}
