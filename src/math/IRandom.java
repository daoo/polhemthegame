package math;

public interface IRandom {
  int nextInt();
  int nextInt(int max);
  int nextInt(int min, int max);

  float nextFloat();
  float nextFloat(float max);
  float nextFloat(float f, float g);
}
