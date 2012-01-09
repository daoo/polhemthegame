/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects.spawn;

import game.pods.GameTime;
import game.pods.Unit;
import game.triggers.IEffect;
import game.world.World;
import main.Locator;

public class SpawnUnitEffect implements IEffect {
  private final Unit unit;

  public SpawnUnitEffect(Unit unit) {
    assert unit != null;

    this.unit = unit;
  }

  @Override
  public void execute(GameTime time, World world) {
    world.addUnit(unit.entity);
    Locator.getUI().addDynamic(unit.infoBar);
  }
}
