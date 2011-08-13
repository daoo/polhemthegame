/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package factories;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CreepsData.CreepData;
import loader.parser.ParserException;
import main.Launcher;
import other.CacheTool;

import components.graphics.RSheet;

import entities.Creep;

public class CreepFactory {

  public static Creep Make(final float x, final float y, final float ang,
                           final float spawnTime, final CreepData data)
    throws ParserException, DataException, IOException {
    final RSheet walk = CacheTool.getRSheet(Launcher.cache, data.getSheet("walk"));
    final RSheet death = CacheTool.getRSheet(Launcher.cache, data.getSheet("death"));

    return new Creep(x, y, data.hitbox.width, data.hitbox.height,
                     ang, data.speed, data.moneyGain, data.damage,
                     data.hitpoints, spawnTime, walk, death);
  }
}
