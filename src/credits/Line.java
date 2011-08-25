/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package credits;

import math.Vector2;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


class Line {
  private final Vector2 pos, vel;
  private final Image   img;

  public Line(final float x, final float y, final float speed, final Image img) {
    pos = new Vector2(x, y);
    vel = new Vector2(0, speed);
    this.img = img;
  }

  public void update(final float dt) {
    pos.addSelf(vel.multiply(dt));
  }

  public void draw(final Graphics g) {
    g.drawImage(img, pos.x, pos.y);
  }

  public float getY() {
    return pos.y;
  }

  public float getHeight() {
    return img.getHeight();
  }
  
  public void addVelocity(final Vector2 v) {
    vel.addSelf(v);
  }
}
