package other;

public class GameTime {
  private final float frame;
  private final float elapsed;

  public GameTime(final float frame, final float elapsed) {
    this.frame   = frame;
    this.elapsed = elapsed;
  }

  public float getFrameLength() {
    return frame;
  }

  public float getElapsed() {
    return elapsed;
  }

  @Override
  public String toString() {
    return "Elapsed: " + elapsed + ", Delta: " + frame;
  }
}
