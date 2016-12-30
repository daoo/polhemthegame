package util;

public class Pair<A, B> {
  public final A fst;
  public final B snd;

  /**
   * Construct a new pair with the specific values.
   *
   * @param a the first value
   * @param b the second value
   */
  public Pair(A a, B b) {
    fst = a;
    snd = b;
  }
}
