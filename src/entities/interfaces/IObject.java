/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package entities.interfaces;

import math.time.GameTime;

import org.newdawn.slick.Graphics;

import entities.groups.Entities;
import game.World;

public interface IObject {
  Entities getType();

  void update(final GameTime time, final World world);
  void render(final Graphics g);
}
