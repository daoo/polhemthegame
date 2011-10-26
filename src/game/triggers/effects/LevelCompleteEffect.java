package game.triggers.effects;

import game.Level;
import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;

public class LevelCompleteEffect implements IEffect {
  private final Level level;

  public LevelCompleteEffect(Level level) {
    this.level = level;
  }

  @Override
  public void execute(GameTime time, World world) {
    level.setFinished();
  }
}
