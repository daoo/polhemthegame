/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package states;


import org.newdawn.slick.Graphics;

import other.GameTime;

import components.IComp;

public interface ICompState extends IComp {
  public void update(final GameTime time);

  public void render(final Graphics g);

  public boolean isFinished();

  public void start();
}
