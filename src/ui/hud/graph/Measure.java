package ui.hud.graph;

public class Measure {
  // NOTE: All times are in nanoseconds

  private final long timeDelta;

  private long total, measures;
  private long firstMeasure, lastMeasure;

  private boolean finished;

  /**
   * Construct a new measure with the specified interval.
   * @param timeDelta the time difference between total measures, in nanoseconds
   */
  public Measure(long timeDelta) {
    this.timeDelta = timeDelta;

    reset();
  }

  public void startMeasure() {
    long timeCurrent = System.nanoTime();

    if (firstMeasure == 0)
      firstMeasure = timeCurrent;

    lastMeasure = timeCurrent;
  }

  public void stopMeasure() {
    long timeCurrent = System.nanoTime();
    long delta = timeCurrent - lastMeasure;

    total += delta;
    ++measures;

    if (timeCurrent - firstMeasure > timeDelta) {
      finished = true;
    }
  }

  public void reset() {
    firstMeasure = 0;
    lastMeasure  = 0;

    total    = 0;
    measures = 0;

    finished = false;
  }

  public long getAverage() {
    return total / measures;
  }

  public boolean isFinished() {
    return finished;
  }
}
