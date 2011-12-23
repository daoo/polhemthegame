package game.triggers.condition;

import game.pods.GameTime;
import game.triggers.ICondition;
import game.world.World;

public class AlwaysTrueCondition implements ICondition {
  @Override
  public boolean evaluate(GameTime time, World world) {
    return true;
  }
}
