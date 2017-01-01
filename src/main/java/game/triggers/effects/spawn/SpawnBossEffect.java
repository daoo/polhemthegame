/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import game.course.World;
import game.entities.Unit;
import game.misc.Locator;
import game.triggers.Effect;
import game.types.GameTime;
import game.types.Message;

public class SpawnBossEffect implements Effect {
  private final Unit mUnit;

  public SpawnBossEffect(Unit boss) {
    assert boss != null;
    mUnit = boss;
  }

  @Override
  public void execute(GameTime time, World world) {
    world.addUnit(mUnit.entity);
    Locator.getUI().addDynamic(mUnit.infoBar);

    mUnit.entity.sendMessage(Message.START_BOSS, time);
  }
}
