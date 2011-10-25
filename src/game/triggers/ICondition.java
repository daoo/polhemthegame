package game.triggers;

import game.time.GameTime;
import game.world.World;

public interface ICondition {
  boolean evaluate(GameTime time, World world);
}
