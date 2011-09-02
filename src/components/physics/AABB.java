/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.physics;

import math.Rectangle;
import math.Vector2;

public class AABB extends Rectangle {
  private final Vector2 vel;

  public AABB(final float x, final float y, final float w, final float h,
              final float dx, final float dy) {
    super(x, y, w, h);

    vel = new Vector2(dx, dy);
  }

  public void integrate(final float dt) {
    addPosition(vel.multiply(dt));
  }

  public Vector2 getVelocity() {
    return vel;
  }

  public void setVelocity(final Vector2 v) {
    vel.set(v);
  }

  public void addVelocity(final Vector2 v) {
    vel.addSelf(v);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("min: ");
    sb.append(min.toString());
    sb.append(", max: ");
    sb.append(max.toString());
    sb.append(", size: ");
    sb.append(size.toString());

    return sb.toString();
  }
}
