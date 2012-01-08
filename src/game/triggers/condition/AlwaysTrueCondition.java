/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.pods.GameTime;
import game.triggers.ICondition;
import game.world.World;

public class AlwaysTrueCondition implements ICondition {
  public static final AlwaysTrueCondition INSTANCE = new AlwaysTrueCondition();

  private AlwaysTrueCondition() {
    // Do nothing
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    return true;
  }
}
