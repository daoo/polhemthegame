/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.world.World;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public interface IRoundState  {
  void update(final GameTime time, final World world);

  void render(final Graphics g);

  boolean isFinished();
}
