package math;

public class Aabb {
  private Vector2 mMin;
  private Vector2 mMax;
  private final Rectangle mBound;

  public Aabb(Vector2 position, Rectangle bound) {
    mMin = position;
    mMax = Vector2.add(mMin, bound.size);
    mBound = bound;
  }

  public Aabb(Aabb other) {
    mMin = other.mMin;
    mMax = other.mMax;
    mBound = other.mBound;
  }

  public Vector2 getMin() {
    return mMin;
  }

  public Vector2 getMax() {
    return mMax;
  }

  public Vector2 getSize() {
    return mBound.size;
  }

  public boolean intersects(Aabb other) {
    return !(
        mMin.x > other.mMax.x || mMin.y > other.mMax.y || mMax.x < other.mMin.x ||
            mMax.y < other.mMin.y);
  }

  public boolean contains(Aabb other) {
    return other.mMin.x > mMin.x && other.mMax.x < mMax.x && other.mMin.y > mMin.y &&
        other.mMax.y < mMax.y;
  }

  public boolean contains(Vector2 p) {
    return ExtraMath.inRange(p.x, mMin.x, mMax.x) && ExtraMath.inRange(p.y, mMin.y, mMax.y);
  }

  public void setPosition(Vector2 position) {
    mMin = position;
    mMax = Vector2.add(position, mBound.size);
  }

  public void addPosition(Vector2 delta) {
    setPosition(Vector2.add(mMin, delta));
  }

  public Vector2 getCenter() {
    return Vector2.add(mMin, mBound.getHalfSize());
  }
}
