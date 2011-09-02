/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.game.states;

import org.newdawn.slick.Graphics;

import com.daoo.components.triggers.actions.IActionProducer;
import com.daoo.math.time.GameTime;

public interface IState extends IActionProducer {
  public void update(final GameTime time);

  public void render(final Graphics g);

  public boolean isFinished();
}
