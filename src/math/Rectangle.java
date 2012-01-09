/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package math;

public class Rectangle {
  private Vector2 min, max, center;
  private Vector2 size;

  public Rectangle(Rectangle rect) {
    this.min    = rect.min;
    this.max    = rect.max;
    this.center = rect.center;
    this.size   = rect.size;
  }

  public Rectangle(float x1, float y1, float width, float height) {
    float x2 = x1 + width;
    float y2 = y1 + height;

    min    = new Vector2(x1, y1);
    max    = new Vector2(x2, y2);
    size   = new Vector2(width, height);
    center = Vector2.add(min, Vector2.divide(size, 2.0f));
  }

  public Rectangle(Vector2 upperLeft, Vector2 bottomRight) {
    min  = upperLeft;
    max  = bottomRight;
    size = new Vector2(bottomRight.x - upperLeft.x,
                          bottomRight.y - upperLeft.x);
    center = Vector2.add(min, Vector2.divide(size, 2.0f));

  }

  public void setPosition(float x, float y) {
    min = new Vector2(x, y);
    max = Vector2.add(min, size);
  }

  public void setPosition(Vector2 v) {
    min = v;
    max = Vector2.add(v, size);
  }

  public void addPosition(Vector2 v) {
    min = Vector2.add(min, v);
    max = Vector2.add(max, v);
  }

  /**
   * Top left corner of the rectangle.
   * @return a vector2
   */
  public Vector2 getMin() {
    return min;
  }

  /**
   * Bottom right corner of the rectangle.
   * @return a vector2
   */
  public Vector2 getMax() {
    return max;
  }

  public Vector2 getCenter() {
    return center;
  }

  public Vector2 getSize() {
    return size;
  }

  public float getWidth() {
    return size.x;
  }

  public float getHeight() {
    return size.y;
  }

  public float getX1() {
    return min.x;
  }

  public float getY1() {
    return min.y;
  }

  public float getX2() {
    return max.x;
  }

  public float getY2() {
    return max.y;
  }

  @Override
  public String toString() {
    return String.format("(%f, %f, %f, %f) %dx%d",
        min.x, min.y, max.x, max.y, (int) size.x, (int) size.y);
  }

  /**
   * Check if two rectangles are intersecting.
   * @param a the first rectangle
   * @param b the second rectangle
   * @return true or false depending on if they intersecting or not
   */
  public static boolean intersects(Rectangle a, Rectangle b) {
    return !((a.min.x > b.max.x) || (a.min.y > b.max.y) ||
             (a.max.x < b.min.x) || (a.max.y < b.min.y));
  }

  /**
   * Check if a rectangle (a) contains another rectangle (b). That is, the
   * entire rectangle is in inside another.
   * @param a the outer rectangle
   * @param b the inner rectangle
   * @return true or false depending on if b is contained by a or not
   */
  public static boolean contains(Rectangle a, Rectangle b) {
    return ((b.min.x > a.min.x && b.max.x < a.max.x) &&
            (b.min.y > a.min.y && b.max.y < a.max.y));
  }
}
