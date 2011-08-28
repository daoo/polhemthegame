package entities.interfaces;

import game.World;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public interface IObject {
  void update(final GameTime time, final World world);
  void render(final Graphics g);
}
