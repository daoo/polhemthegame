/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package states.credits;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ImageWithLocation {
  private final float x, y;
  private final Image img;

  public ImageWithLocation(float x, float y, Image img) {
    this.x = x;
    this.y = y;
    this.img = img;
  }

  public void render(Graphics g) {
    g.drawImage(img, x, y);
  }

  public int getHeight() {
    return img.getHeight();
  }
}
