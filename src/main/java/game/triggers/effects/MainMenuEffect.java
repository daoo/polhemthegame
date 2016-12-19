/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.course.World;
import game.states.StateManager;
import game.triggers.IEffect;
import game.types.GameTime;

public class MainMenuEffect implements IEffect {
  private final StateManager stateManager;

  public MainMenuEffect(StateManager stateManager) {
    assert stateManager != null;
    this.stateManager = stateManager;
  }

  @Override
  public void execute(GameTime time, World world) {
    stateManager.enterMainMenu();
  }
}
