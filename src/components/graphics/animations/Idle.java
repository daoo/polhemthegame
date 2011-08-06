package components.graphics.animations;

import components.graphics.Tile;

public class Idle implements IAnimator {
  public Idle() {
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public Tile next(Tile tile) {
    return tile;
  }
}
