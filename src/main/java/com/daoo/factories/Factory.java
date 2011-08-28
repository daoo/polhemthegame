/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.factories;

import java.io.IOException;

import com.daoo.components.graphics.RSheet;
import com.daoo.entities.Boss;
import com.daoo.entities.Creep;
import com.daoo.entities.InvisibleRectangle;
import com.daoo.entities.Player;
import com.daoo.entities.Players;
import com.daoo.game.World;
import com.daoo.loader.data.DataException;
import com.daoo.loader.data.json.BossesData.BossData;
import com.daoo.loader.data.json.CreepsData.CreepData;
import com.daoo.loader.data.json.PlayersData.PlayerData;
import com.daoo.loader.parser.ParserException;
import com.daoo.math.Rectangle;
import com.daoo.ptg.CacheTool;
import com.daoo.ptg.Locator;

public class Factory {

  public static World MakeWorld(final Rectangle rectWorld, final Players players) {
    /**
     * The layout of rectangles:
     * |-----------------------------|
     * | bigRect                     |
     * |                             |
     * |                             |
     * |---------|---------|         |
     * | cKiller |smallRect|         |
     * |---------|---------|         |
     * |                             |
     * |                             |
     * |                             |
     * |-----------------------------|
     */

    final World w = new World();

    // TODO: Big rect
    final InvisibleRectangle rectBig = new InvisibleRectangle(-rectWorld.getWidth(),
                                                              -rectWorld.getHeight(),
                                                              3 * rectWorld.getWidth(),
                                                              3 * rectWorld.getHeight());
    rectBig.onNotContainsEvent.add(null);
    // TODO: Restrict players
    // TODO: Creep killer

    for (final Player p : players) {
      w.addPlayer(p);
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
