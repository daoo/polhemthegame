/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities.interfaces;

import math.time.GameTime;

import org.newdawn.slick.Graphics;

import game.World;
import game.entities.groups.Entities;

public interface IObject {
  Entities getType();

  void update(final GameTime time, final World world);
  void render(final Graphics g);
}
