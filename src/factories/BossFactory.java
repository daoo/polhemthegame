/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package factories;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.BossesData.BossData;
import loader.parser.ParserException;
import main.CacheTool;
import main.Locator;

import components.graphics.RSheet;

import entities.Boss;

public class BossFactory {
  public static Boss Make(final float x, final float y, final BossData data)
  throws ParserException, DataException, IOException {
    final RSheet walk = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    final RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    return new Boss(x, y, data.hitbox.width, data.hitbox.height,
                    0, 0, data.hitpoints, walk, death);
  }
}
