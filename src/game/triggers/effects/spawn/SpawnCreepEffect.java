/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import game.triggers.IEffect;
import game.types.GameTime;
import game.types.Message;
import game.types.Unit;
import game.world.World;
import main.Locator;

public class SpawnCreepEffect implements IEffect {
  private final Unit unit;

  public SpawnCreepEffect(Unit unit) {
    assert unit != null;

    this.unit = unit;
  }

  @Override
  public void execute(GameTime time, World world) {
    unit.entity.sendMessage(Message.START_ANIMATION, null);
    world.addUnit(unit.entity);
    Locator.getUI().addDynamic(unit.infoBar);
  }
}
