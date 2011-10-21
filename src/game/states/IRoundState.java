/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.time.GameTime;
import game.world.World;

import org.newdawn.slick.Graphics;

public interface IRoundState  {
  void update(GameTime time, World world);

  void render(Graphics g);

  boolean isFinished();
}
