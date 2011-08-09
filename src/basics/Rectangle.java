/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package basics;


public class Rectangle {
  protected final Vector2 min, max, center;
  protected final Vector2 size;

  public Rectangle(final float x1, final float y1, final float width, final float height) {
    final float x2 = x1 + width;
    final float y2 = y1 + height;
    min = new Vector2(x1, y1);
    max = new Vector2(x2, y2);
    size = new Vector2(width, height);
    center = min.add(size.divide(2.0f));
  }

  public Rectangle(final Vector2 upperLeft, final Vector2 bottomRight) {
    min = new Vector2(upperLeft);
    max = new Vector2(bottomRight);
    size = new Vector2(bottomRight.x - upperLeft.x,
                       bottomRight.y - upperLeft.x);
    center = min.add(size.divide(2.0f));

  }

  public boolean isIntersecting(final Rectangle other) {
    return !((min.x > other.max.x) || (min.y > other.max.y) ||
             (max.x < other.min.x) || (max.y < other.min.y));
  }

  public boolean isContaining(final Rectangle other) {
    return ((other.min.x > min.x && other.max.x < max.x) &&
            (other.min.y > min.y && other.max.y < max.y));
  }

  public void setPosition(final Vector2 v) {
    min.set(v);
    max.set(v.add(size));
  }

  public void addPosition(final Vector2 v) {
    min.addSelf(v);
    max.addSelf(v);
  }

  /**
   * Top left corner of the rectangle.
   * @return A Vector2
   */
  public Vector2 getMin() {
    return min;
  }

  /**
   * Bottom right corner of the rectangle.
   * @return A Vector2
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
}
