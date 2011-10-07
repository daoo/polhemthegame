/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.credits;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ImageWithLocation {
  final float x, y;
  final Image img;

  public ImageWithLocation(final float x, final float y, final Image img) {
    this.x = x;
    this.y = y;
    this.img = img;
  }

  public void render(final Graphics g) {
    g.drawImage(img, x, y);
  }

  public int getHeight() {
    return img.getHeight();
  }
}
