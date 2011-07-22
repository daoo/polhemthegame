/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.basic;

import other.GameTime;

import components.ICompAnim;

public class Walker {
  private final ICompAnim anim;

  public Walker(final ICompAnim anim) {
    this.anim = anim;
  }

  public void startWalking(final GameTime time) {
    anim.start();
  }

  public void stopWalking() {
    anim.stop();
    anim.goToFirstFrame();
  }
}
