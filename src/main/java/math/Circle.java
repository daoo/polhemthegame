package math;

public class Circle {
  @SuppressWarnings("InstanceVariableNamingConvention")
  public final Vector2 center;
  @SuppressWarnings("InstanceVariableNamingConvention")
  public final float radius;

  public Circle(Vector2 center, float radius) {
    this.center = center;
    this.radius = radius;
  }

  public boolean contains(Vector2 point) {
    return Vector2.distanceSquared(center, point) <= ExtraMath.square(radius);
  }
}
