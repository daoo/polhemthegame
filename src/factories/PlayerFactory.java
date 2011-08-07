package factories;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.PlayersData.PlayerData;
import loader.parser.ParserException;
import main.Launcher;
import other.CacheTool;

import components.graphics.RSheet;

import entities.Player;

public class PlayerFactory {

  public static Player Make(final float x, final float y, final PlayerData data)
    throws ParserException, DataException, IOException {
    final RSheet walk = CacheTool.getRSheet(Launcher.cache, data.getSheet("walk"));
    final RSheet death = CacheTool.getRSheet(Launcher.cache, data.getSheet("death"));

    final Player player = new Player(x, y, data.hitbox.width, data.hitbox.height,
                                     data.handOffset.x, data.handOffset.y,
                                     data.speed, data.hitpoints, data.startMoney,
                                     walk, death);

    player.giveWeapon(CacheTool.getWeapon(Launcher.cache, data.startWeapon));

    return player;
  }

}
