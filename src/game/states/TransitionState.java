/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.world.World;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class TransitionState implements IRoundState {
  private final float x, y;
  private final Image img;
  private final float duration;
  private float       tSinceStart;

  public TransitionState(final Image img, final float duration, final Rectangle rect) {
    this.img = img;

    x = (rect.getWidth() / 2.0f) - (img.getWidth() / 2.0f);
    y = (rect.getHeight() / 2.0f) - (img.getWidth() / 2.0f);

    this.duration = duration;

    tSinceStart = 0.0f;
  }

  @Override
  public void render(final Graphics g) {
    g.drawImage(img, x, y);
  }

  @Override
  public void update(final GameTime time, final World world) {
    tSinceStart += time.getFrameLength();
  }

  @Override
  public boolean isFinished() {
    return tSinceStart >= duration;
  }
}
