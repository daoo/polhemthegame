/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.entities;

import com.daoo.components.graphics.animations.Continuous;
import com.daoo.components.graphics.animations.RunTo;
import com.daoo.components.interfaces.ICompAnim;
import com.daoo.entities.interfaces.IWalking;

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
