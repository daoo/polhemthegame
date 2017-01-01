/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.course.World;
import game.states.StateManager;
import game.triggers.Effect;
import game.types.GameTime;

public class MainMenuEffect implements Effect {
  private final StateManager mStateManager;

  public MainMenuEffect(StateManager stateManager) {
    assert stateManager != null;
    mStateManager = stateManager;
  }

  @Override
  public void execute(GameTime time, World world) {
    mStateManager.enterMainMenu();
  }
}
