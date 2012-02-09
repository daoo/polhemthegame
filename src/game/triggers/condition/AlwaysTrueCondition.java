/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.triggers.ICondition;
import game.types.GameTime;
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
