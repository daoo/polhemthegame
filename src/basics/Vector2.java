/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package basics;

public class Vector2 {
  public static final Vector2 ZERO = new Vector2(0, 0);

  public float                x, y;

  private float sq(final float a) {
    return a * a;
  }

  public Vector2() {
    x = 0;
    y = 0;
  }

  public Vector2(final float x, final float y) {
    this.x = x;
    this.y = y;
  }

  public Vector2(final Vector2 v) {
    x = v.x;
    y = v.y;
  }

  public float theta() {
    if (x != 0) {
      return (float) Math.atan(y / x);
    }

    return (float) (Math.PI / 2.0f);
  }

  public float magnitude() {
    return (float) Math.sqrt(magnitudeSquared());
  }

  public float magnitudeSquared() {
    return (x * x) + (y * y);
  }

  public float dot(final Vector2 v) {
    return (x * v.x) + (y * v.y);
  }

  public float distance(final Vector2 v) {
    return (float) Math.sqrt(distanceSquared(v));
  }

  public float distanceSquared(final Vector2 v) {
    return sq(v.x - x) + sq(v.y - y);
  }

  public void normalizeSelf() {
    final float mag2 = magnitudeSquared();
    if (mag2 != 0) {
      final float mag = (float) Math.sqrt(mag2);
      x /= mag;
      y /= mag;
    }
  }

  public Vector2 normalize() {
    final float mag2 = magnitudeSquared();
    if (mag2 != 0) {
      final float mag = (float) Math.sqrt(mag2);
      return new Vector2(x / mag, y / mag);
    }

    return new Vector2(x, y);
  }

  public Vector2 add(final Vector2 v) {
    return new Vector2(x + v.x, y + v.y);
  }

  public Vector2 add(final float x2, final float y2) {
    return new Vector2(x + x2, y + y2);
  }

  public void addSelf(final Vector2 v) {
    x += v.x;
    y += v.y;
  }

  public void addSelf(final float x2, final float y2) {
    x += x2;
    y += y2;
  }

  public Vector2 subtract(final Vector2 v) {
    return new Vector2(x - v.x, y - v.y);
  }

  public Vector2 multiply(final float scalar) {
    return new Vector2(x * scalar, y * scalar);
  }

  public Vector2 divide(final float scalar) {
    return new Vector2(x / scalar, y / scalar);
  }

  public void set(final float x, final float y) {
    this.x = x;
    this.y = y;
  }

  public void set(final Vector2 v) {
    x = v.x;
    y = v.y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
