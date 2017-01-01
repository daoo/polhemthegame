/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.course.LevelManager;
import game.course.World;
import game.triggers.Effect;
import game.types.GameTime;

public class LevelCompleteEffect implements Effect {
  private final LevelManager mLevelManager;

  public LevelCompleteEffect(LevelManager gameMode) {
    mLevelManager = gameMode;
  }

  @Override
  public void execute(GameTime time, World world) {
    mLevelManager.nextLevel();
  }
}
