package entities.interfaces;

import math.time.GameTime;

import org.newdawn.slick.Graphics;

public interface IObject {
  void update(final GameTime time);
  void render(final Graphics g);
}
