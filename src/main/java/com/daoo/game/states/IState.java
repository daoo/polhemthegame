/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import math.time.GameTime;

import org.newdawn.slick.Graphics;

import components.triggers.actions.IActionProducer;

public interface IState extends IActionProducer {
  public void update(final GameTime time);

  public void render(final Graphics g);

  public boolean isFinished();
}
