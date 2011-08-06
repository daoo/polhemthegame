package components.graphics.animations;

import components.graphics.Tile;

public interface IAnimator {
  public boolean isFinished();
  
  public Tile next(final Tile tile);
}
