/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import math.time.GameTime;

import org.newdawn.slick.Graphics;


import components.interfaces.IActionProducer;
import components.interfaces.IComp;

public interface ICompState extends IActionProducer {
  public void update(final GameTime time);

  public void render(final Graphics g);

  public boolean isFinished();

  public void start();
}
