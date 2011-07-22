/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.graphics;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import other.GameTime;

import components.ICompAnim;

public class TexturedQuad implements ICompAnim {
  private final Image img;

  public TexturedQuad(final Image img) {
    this.img = img;
  }

  @Override
  public void update(final GameTime time) {}

  @Override
  public void render(final Graphics g) {
    g.drawImage(img, 0, 0);
  }

  @Override
  public Image getLastFrame() {
    return img;
  }

  @Override
  public void start() {}

  @Override
  public void stop() {}

  @Override
  public boolean isRunning() {
    return false;
  }

  @Override
  public void goToFirstFrame() {}

  @Override
  public void restart() {}
}
