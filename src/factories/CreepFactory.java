package factories;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CreepsData.CreepData;
import loader.parser.ParserException;
import main.Launcher;

import org.newdawn.slick.SlickException;

import other.CacheTool;

import components.graphics.RSheet;
import components.graphics.RSheetOnce;

import entities.Creep;

public class CreepFactory {

  public static Creep Make(final float x, final float y, final float ang,
                           final float spawnTime, final CreepData data)
    throws SlickException, ParserException, DataException, IOException {
    final RSheet walk = CacheTool.getRSheet(Launcher.cache, data.getSheet("walk"));
    final RSheetOnce death = CacheTool.getRSheetOnce(Launcher.cache, data.getSheet("death"));

    return new Creep(x, y, data.hitBox[0], data.hitBox[1],
                     ang, data.speed, data.moneyGain, data.damage,
                     data.hitPoints, spawnTime, walk, death);
  }

}
