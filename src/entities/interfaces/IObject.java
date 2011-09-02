package com.daoo.entities.interfaces;

import org.newdawn.slick.Graphics;

import com.daoo.game.World;
import com.daoo.math.time.GameTime;

public interface IObject {
  void update(final GameTime time, final World world);
  void render(final Graphics g);
}
