/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package factories;

import entities.Boss;
import entities.Creep;
import entities.Player;
import entities.Players;
import game.World;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.BossesData.BossData;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.PlayersData.PlayerData;
import loader.parser.ParserException;
import main.CacheTool;
import main.Locator;
import math.Rectangle;

import components.graphics.RSheet;

public class Factory {
  public static World MakeWorld(final Rectangle rect, final Players players) {
    final World w = new World(rect.getWidth(), rect.getHeight());
    for (final Player p : players) {
      w.add(p);
    }

    return w;
  }
  
  public static Boss MakeBoss(final float x, final float y, final BossData data)
  throws ParserException, DataException, IOException {
    final RSheet walk = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    final RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    return new Boss(x, y, data.hitbox.width, data.hitbox.height,
                    0, 0, data.hitpoints, walk, death);
  }
  
  public static Creep MakeCreep(final float x, final float y, final float ang,
                                final float spawnTime, final CreepData data)
    throws ParserException, DataException, IOException {
    final RSheet walk = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    final RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    return new Creep(x, y, data.hitbox.width, data.hitbox.height,
                     ang, data.speed, data.moneyGain, data.damage,
                     data.hitpoints, spawnTime, walk, death);
  }
  
  public static Player MakePlayer(final float x, final float y, final PlayerData data)
    throws ParserException, DataException, IOException {
    final RSheet walk = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    final RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    final Player player = new Player(x, y, data.hitbox.width, data.hitbox.height,
                                     data.handOffset.x, data.handOffset.y,
                                     data.speed, data.hitpoints, data.startMoney,
                                     walk, death);

    player.giveWeapon(CacheTool.getWeapon(Locator.getCache(), data.startWeapon));

    return player;
  }
}
