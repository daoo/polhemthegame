/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package math;

public class Rectangle {
  private Vector2 mMin;
  private Vector2 mMax;
  private Vector2 mCenter;
  private final int mWidth;
  private final int mHeight;
  private final int mHalfWidth;
  private final int mHalfHeight;

  /**
   * Copy constructor.
   *
   * @param rect the rectangle to copy from, can not be null
   */
  public Rectangle(Rectangle rect) {
    assert rect != null;

    mMin = rect.mMin;
    mMax = rect.mMax;
    mCenter = rect.mCenter;
    mWidth = rect.mWidth;
    mHeight = rect.mHeight;
    mHalfWidth = rect.mHalfWidth;
    mHalfHeight = rect.mHalfHeight;
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

    mMin = new Vector2(x1, y1);
    mMax = new Vector2(x1 + width, y1 + height);

    mWidth = width;
    mHeight = height;
    mHalfWidth = width / 2;
    mHalfHeight = height / 2;

    mCenter = Vector2.add(mMin, mHalfWidth, mHalfHeight);
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
    mMin = v;
    mMax = Vector2.add(v, mWidth, mHeight);
    mCenter = Vector2.add(v, mHalfWidth, mHalfHeight);
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
    mMin = Vector2.add(mMin, x, y);
    mMax = Vector2.add(mMax, x, y);
    mCenter = Vector2.add(mMin, mHalfWidth, mHalfHeight);
  }

  /**
   * Top left corner of the rectangle.
   *
   * @return the top left of the rectangle
   */
  public Vector2 getMin() {
    return mMin;
  }

  /**
   * Bottom right corner of the rectangle.
   *
   * @return the bottom right of the rectangle
   */
  public Vector2 getMax() {
    return mMax;
  }

  /**
   * Return the position of the center of the rectangle.
   *
   * @return the center position as a vector
   */
  public Vector2 getCenter() {
    return mCenter;
  }

  /**
   * Return the width of the rectangle.
   * Note that the width is constant.
   *
   * @return the width of the rectangle, greater than zero
   */
  public int getWidth() {
    return mWidth;
  }

  /**
   * Return the height of the rectangle.
   * Note that the height is constant.
   *
   * @return the height of the rectangle, greater than zero
   */
  public int getHeight() {
    return mHeight;
  }

  /**
   * Return the upper left x coordinate.
   *
   * @return the upper left x coordinate
   */
  public float getX1() {
    return mMin.x;
  }

  /**
   * Return the upper left y coordinate.
   *
   * @return the upper left y coordinate
   */
  public float getY1() {
    return mMin.y;
  }

  /**
   * Return the bottom right x coordinate.
   *
   * @return the bottom right x coordinate
   */
  public float getX2() {
    return mMax.x;
  }

  /**
   * Return the bottom right y coordinate.
   *
   * @return the bottom right y coordinate
   */
  public float getY2() {
    return mMax.y;
  }

  /**
   * Return a string representation of the rectangle.
   *
   * @return a string
   */
  @Override
  public String toString() {
    return String.format("(%f, %f, %f, %f) %dx%d", mMin.x, mMin.y, mMax.x, mMax.y, mWidth, mHeight);
  }

  /**
   * Check if two rectangles are intersecting.
   *
   * @param a the first rectangle
   * @param b the second rectangle
   * @return true or false depending on if they intersecting or not
   */
  public static boolean intersects(Rectangle a, Rectangle b) {
    return !(a.mMin.x > b.mMax.x || a.mMin.y > b.mMax.y || a.mMax.x < b.mMin.x || a.mMax.y < b.mMin.y);
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
    return b.mMin.x > a.mMin.x && b.mMax.x < a.mMax.x && b.mMin.y > a.mMin.y && b.mMax.y < a.mMax.y;
  }

  /**
   * Check if rectangle (a) contains a point (p).
   *
   * @param a the rectangle
   * @param p the point (as a vector)
   * @return true or false depending on if p is inside a or not
   */
  public static boolean contains(Rectangle a, Vector2 p) {
    return ExtraMath.inRange(p.x, a.mMin.x, a.mMax.x) && ExtraMath.inRange(p.y, a.mMin.y, a.mMax.y);
  }
}
