package game.triggers;

import game.time.GameTime;
import game.world.World;

public interface ITrigger {
  void addCondition(ICondition condition);
  void addEffect(IEffect effect);

  void update(GameTime time);

  void setWorld(World world);

  boolean runAgain();
}
