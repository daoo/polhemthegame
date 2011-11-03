/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;
import states.StateManager;

public class MainMenuEffect implements IEffect {
  private final StateManager stateManager;

  public MainMenuEffect(StateManager stateManager) {
    assert (stateManager != null);
    this.stateManager = stateManager;
  }

  @Override
  public void execute(GameTime time, World world) {
    stateManager.enterMainMenu();
  }
}
