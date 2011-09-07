/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;


import game.components.graphics.animations.Continuous;
import game.components.graphics.animations.RunTo;
import game.components.interfaces.ICompAnim;
import game.entities.interfaces.IWalking;

public class Animated extends Entity implements IWalking {
  private final ICompAnim anim;

  public Animated(final float x, final float y,
                  final float w, final float h,
                  final ICompAnim anim) {
    super(x, y, w, h, 0, 0);

    this.anim = anim;
    add(anim);
  }

  @Override
  public void start() {
    anim.setAnimator(new Continuous(anim.getTileCount()));
  }

  @Override
  public void stop() {
    anim.setAnimator(new RunTo(anim.getTileCount(), anim.getLastTile()));
  }
}
