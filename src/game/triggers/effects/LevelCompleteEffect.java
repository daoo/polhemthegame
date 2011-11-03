/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.modes.GameMode;
import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;

public class LevelCompleteEffect implements IEffect {
  private final GameMode gameMode;

  public LevelCompleteEffect(GameMode gameMode) {
    this.gameMode = gameMode;
  }

  @Override
  public void execute(GameTime time, World world) {
    gameMode.goNextLevel();
  }
}
