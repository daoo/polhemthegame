/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package math;

public class Rectangle {
  @SuppressWarnings("InstanceVariableNamingConvention")
  public final Vector2 size;

  /**
   * Construct a new rectangle with a specific size size.
   *
   * @param width the width of the rectangle, greater than zero
   * @param height the height of the rectangle, greater than zero
   */
  public Rectangle(float width, float height) {
    assert width > 0;
    assert height > 0;

    size = new Vector2(width, height);
  }

  public static Rectangle fromExtents(Vector2 min, Vector2 max) {
    return new Rectangle(max.x - min.x, max.y - min.y);
  }

  public Vector2 getHalfSize() {
    return Vector2.divide(size, 2.0f);
  }
}
