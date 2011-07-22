package factories;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.PlayersData.PlayerData;
import loader.parser.ParserException;
import main.Launcher;

import org.newdawn.slick.SlickException;

import other.CacheTool;

import components.graphics.RSheet;

import entities.Player;

public class PlayerFactory {

  public static Player Make(float x, float y, PlayerData data)
    throws SlickException, ParserException, DataException, IOException {
    RSheet anim = CacheTool.getRSheet(Launcher.cache, data.getSheet("walk"));
    Player player = new Player(x, y, data.hitBox[0], data.hitBox[1],
                               data.handOffset[0], data.handOffset[1],
                               data.speed, data.hitPoints, data.startMoney, anim);
    
    player.giveWeapon(CacheTool.getWeapon(Launcher.cache, data.startWeapon));
    
    return player;
  }

}
