/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.game.modes;

import org.newdawn.slick.Graphics;

public interface IMode {
  public void update(final float dt);
  public void render(final Graphics g);

  public boolean isFinished();
}
