/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.misc;

import org.newdawn.slick.Image;

import java.io.IOException;

import game.components.graphics.AnimatedSheet;
import game.types.Orientation;
import loader.Cache;
import loader.data.DataImage;
import loader.data.json.BossesData;
import loader.data.json.CreepsData;
import loader.data.json.LevelData;
import loader.data.json.PlayersData;
import loader.data.json.ProjectilesData;
import loader.data.json.ShopData;
import loader.data.json.WeaponsData;
import loader.data.json.types.SpriteData;
import loader.parser.GsonParser;
import loader.parser.PNGParser;
import loader.parser.Parser;
import loader.parser.ParserException;
import util.SpriteSheet;

public class CacheTool {
  private static final Parser<BossesData> BOSSES_PARSER = new GsonParser<>(
      Locator.getGson(), BossesData.class);
  private static final Parser<CreepsData> CREEPS_PARSER = new GsonParser<>(
      Locator.getGson(), CreepsData.class);
  private static final Parser<LevelData> LEVEL_PARSER = new GsonParser<>(
      Locator.getGson(), LevelData.class);
  private static final Parser<PlayersData> PLAYERS_PARSER = new GsonParser<>(
      Locator.getGson(), PlayersData.class);
  private static final Parser<ProjectilesData> PROJECTILES_PARSER = new GsonParser<>(
      Locator.getGson(), ProjectilesData.class);
  private static final Parser<WeaponsData> WEAPONS_PARSER = new GsonParser<>(
      Locator.getGson(), WeaponsData.class);
  private static final Parser<ShopData> SHOP_PARSER = new GsonParser<>(
      Locator.getGson(), ShopData.class);
  private static final Parser<DataImage> PNG_PARSER = new PNGParser();

  public static BossesData getBosses(Cache cache) throws ParserException, IOException {
    return cache.get("bosses.js", BOSSES_PARSER);
  }

  public static CreepsData getCreeps(Cache cache) throws ParserException, IOException {
    return cache.get("creeps.js", CREEPS_PARSER);
  }

  public static Image getImage(Cache cache, String id) throws ParserException, IOException {
    return cache.get(id, PNG_PARSER);
  }

  public static LevelData getLevel(Cache cache, String level) throws ParserException, IOException {
    return cache.get("levels/" + level + ".js", LEVEL_PARSER);
  }

  public static PlayersData getPlayers(Cache cache) throws ParserException, IOException {
    return cache.get("players.js", PLAYERS_PARSER);
  }

  public static WeaponsData getWeapons(Cache cache) throws ParserException, IOException {
    return cache.get("weapons.js", WEAPONS_PARSER);
  }

  public static ProjectilesData getProjectiles(Cache cache) throws ParserException, IOException {
    return cache.get("projectiles.js", PROJECTILES_PARSER);
  }

  /**
   * Create a new animated sprite sheet based on some sprite data.
   *
   * @param cache the cache to use for retrieval
   * @param orientation the orientation of the sheet (when rendering)
   * @param rotation the rotation of the sheet (when rendering)
   * @param sprite the sprite data
   * @return a new animated sprite sheet
   * @throws ParserException if parsing fails
   * @throws IOException if IO fails
   */
  public static AnimatedSheet getAnimatedSheet(
      Cache cache, Orientation orientation, int rotation, SpriteData sprite) throws ParserException,
      IOException {
    return new AnimatedSheet(sprite.framerate, sprite.offset.x, sprite.offset.y, orientation,
        rotation, getSpriteSheet(cache, sprite));
  }

  public static ShopData getShop(Cache cache) throws ParserException, IOException {
    return cache.get("shop.js", SHOP_PARSER);
  }

  public static SpriteSheet getSpriteSheet(Cache cache, SpriteData sprite) throws ParserException,
      IOException {
    return new SpriteSheet(
        cache.get(sprite.sprite, PNG_PARSER), sprite.tileSize.width, sprite.tileSize.height);
  }
}
