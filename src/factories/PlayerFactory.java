/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package factories;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.PlayersData.PlayerData;
import loader.parser.ParserException;
import main.CacheTool;
import main.Locator;

import components.graphics.RSheet;

import entities.Player;

public class PlayerFactory {
  public static Player Make(final float x, final float y, final PlayerData data)
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
