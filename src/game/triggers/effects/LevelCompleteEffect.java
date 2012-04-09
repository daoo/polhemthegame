/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.triggers.IEffect;
import game.types.GameTime;
import game.world.World;
import main.LevelManager;

public class LevelCompleteEffect implements IEffect {
  private final LevelManager levelManager;

  public LevelCompleteEffect(LevelManager gameMode) {
    this.levelManager = gameMode;
  }

  @Override
  public void execute(GameTime time, World world) {
    levelManager.nextLevel();
  }
}
