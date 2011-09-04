package math;

public class Random implements IRandom {
  private final java.util.Random random;

  public Random() {
    random = new java.util.Random();
  }

  @Override
  public int nextInt() {
    return random.nextInt();
  }

  @Override
  public int nextInt(int max) {
    return random.nextInt(max);
  }

  @Override
  public int nextInt(final int min, final int max) {
    return min + random.nextInt(max - min);
  }
}
