/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import game.components.Message;
import game.triggers.IEffect;
import game.types.GameTime;
import game.types.Unit;
import game.world.World;
import main.Locator;

public class SpawnBossEffect implements IEffect {
  private final Unit unit;

  public SpawnBossEffect(Unit boss) {
    assert boss != null;

    this.unit = boss;
  }

  @Override
  public void execute(GameTime time, World world) {
    world.addUnit(unit.entity);
    Locator.getUI().addDynamic(unit.infoBar);

    unit.entity.sendMessage(Message.START_BOSS, time);
  }
}
