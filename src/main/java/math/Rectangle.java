/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package math;

public class Rectangle {
  private Vector2 min;
  private Vector2 max;
  private Vector2 center;
  private final int width;
  private final int height;
  private final int halfWidth;
  private final int halfHeight;

  /**
   * Copy constructor.
   *
   * @param rect the rectangle to copy from, can not be null
   */
  public Rectangle(Rectangle rect) {
    assert rect != null;

    this.min = rect.min;
    this.max = rect.max;
    this.center = rect.center;
    this.width = rect.width;
    this.height = rect.height;
    this.halfWidth = rect.halfWidth;
    this.halfHeight = rect.halfHeight;
  }

  /**
   * Construct a new rectangle from a position and a size.
   *
   * @param x1 x coordinate for top left position
   * @param y1 y coordinate for top left position
   * @param width the width of the rectangle, greater than zero
   * @param height the height of the rectangle, greater than zero
   */
  public Rectangle(float x1, float y1, int width, int height) {
    assert width > 0;
    assert height > 0;

    min = new Vector2(x1, y1);
    max = new Vector2(x1 + width, y1 + height);

    this.width = width;
    this.height = height;
    this.halfWidth = width / 2;
    this.halfHeight = height / 2;

    center = Vector2.add(min, halfWidth, halfHeight);
  }

  /**
   * Sets the position of this rectangle.
   *
   * @param x the new x coordinate
   * @param y the new y coordinate
   */
  public void setPosition(float x, float y) {
    setPosition(new Vector2(x, y));
  }

  /**
   * Sets the position of this rectangle.
   *
   * @param v the new position
   */
  public void setPosition(Vector2 v) {
    min = v;
    max = Vector2.add(v, width, height);
    center = Vector2.add(v, halfWidth, halfHeight);
  }

  /**
   * Translate the rectangle.
   *
   * @param v the vector to use for translation
   */
  public void addPosition(Vector2 v) {
    addPosition(v.x, v.y);
  }

  /**
   * Translate the rectangle.
   *
   * @param x the translation on the x-axis
   * @param y the translation on the y-axis
   */
  public void addPosition(float x, float y) {
    min = Vector2.add(min, x, y);
    max = Vector2.add(max, x, y);
    center = Vector2.add(min, halfWidth, halfHeight);
  }

  /**
   * Top left corner of the rectangle.
   *
   * @return the top left of the rectangle
   */
  public Vector2 getMin() {
    return min;
  }

  /**
   * Bottom right corner of the rectangle.
   *
   * @return the bottom right of the rectangle
   */
  public Vector2 getMax() {
    return max;
  }

  /**
   * Return the position of the center of the rectangle.
   *
   * @return the center position as a vector
   */
  public Vector2 getCenter() {
    return center;
  }

  /**
   * Return the width of the rectangle.
   * Note that the width is constant.
   *
   * @return the width of the rectangle, greater than zero
   */
  public int getWidth() {
    return width;
  }

  /**
   * Return the height of the rectangle.
   * Note that the height is constant.
   *
   * @return the height of the rectangle, greater than zero
   */
  public int getHeight() {
    return height;
  }

  /**
   * Return the upper left x coordinate.
   *
   * @return the upper left x coordinate
   */
  public float getX1() {
    return min.x;
  }

  /**
   * Return the upper left y coordinate.
   *
   * @return the upper left y coordinate
   */
  public float getY1() {
    return min.y;
  }

  /**
   * Return the bottom right x coordinate.
   *
   * @return the bottom right x coordinate
   */
  public float getX2() {
    return max.x;
  }

  /**
   * Return the bottom right y coordinate.
   *
   * @return the bottom right y coordinate
   */
  public float getY2() {
    return max.y;
  }

  /**
   * Return a string representation of the rectangle.
   *
   * @return a string
   */
  @Override
  public String toString() {
    return String.format("(%f, %f, %f, %f) %dx%d", min.x, min.y, max.x, max.y, width, height);
  }

  /**
   * Check if two rectangles are intersecting.
   *
   * @param a the first rectangle
   * @param b the second rectangle
   * @return true or false depending on if they intersecting or not
   */
  public static boolean intersects(Rectangle a, Rectangle b) {
    return !(
        a.min.x > b.max.x || a.min.y > b.max.y || a.max.x < b.min.x || a.max.y < b.min.y);
  }

  /**
   * Check if a rectangle (a) contains another rectangle (b). That is, the
   * entire rectangle is in inside another.
   *
   * @param a the outer rectangle
   * @param b the inner rectangle
   * @return true or false depending on if b is contained by a or not
   */
  public static boolean contains(Rectangle a, Rectangle b) {
    return b.min.x > a.min.x && b.max.x < a.max.x && b.min.y > a.min.y && b.max.y < a.max.y;
  }

  /**
   * Check if rectangle (a) contains a point (p).
   *
   * @param a the rectangle
   * @param p the point (as a vector)
   * @return true or false depending on if p is inside a or not
   */
  public static boolean contains(Rectangle a, Vector2 p) {
    return ExtraMath.inRange(p.x, a.min.x, a.max.x) && ExtraMath.inRange(p.y, a.min.y, a.max.y);
  }
}
