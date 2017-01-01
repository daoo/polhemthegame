/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.condition;

import game.course.World;
import game.triggers.Condition;
import game.types.GameTime;

public class AlwaysTrueCondition implements Condition {
  public static final AlwaysTrueCondition INSTANCE = new AlwaysTrueCondition();

  private AlwaysTrueCondition() {
    // Do nothing
  }

  @Override
  public boolean evaluate(GameTime time, World world) {
    return true;
  }
}
