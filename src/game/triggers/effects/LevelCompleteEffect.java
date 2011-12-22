/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.pods.GameTime;
import game.triggers.IEffect;
import game.world.World;
import states.GameState;

public class LevelCompleteEffect implements IEffect {
  private final GameState gameMode;

  public LevelCompleteEffect(GameState gameMode) {
    this.gameMode = gameMode;
  }

  @Override
  public void execute(GameTime time, World world) {
    gameMode.goNextLevel();
  }
}
