/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.misc;

import org.newdawn.slick.Image;

import java.io.File;
import java.io.IOException;

import game.components.graphics.AnimatedSheet;
import game.types.Orientation;
import loader.Cache;
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
import loader.parser.ParserException;
import util.SpriteSheet;

public class CacheTool {
  private static final String EXT_JS = ".js";

  private static final String DIR_LEVELS = "levels";

  private static final String FILE_BOSSES      = "bosses.js";
  private static final String FILE_CREEPS      = "creeps.js";
  private static final String FILE_PLAYERS     = "players.js";
  private static final String FILE_PROJECTILES = "projectiles.js";
  private static final String FILE_WEAPONS     = "weapons.js";
  private static final String FILE_SHOP        = "shop.js";

  private static final GsonParser BOSSES_PARSER      = new GsonParser(BossesData.class);
  private static final GsonParser CREEPS_PARSER      = new GsonParser(CreepsData.class);
  private static final GsonParser LEVEL_PARSER       = new GsonParser(LevelData.class);
  private static final GsonParser PLAYERS_PARSER     = new GsonParser(PlayersData.class);
  private static final GsonParser PROJECTILES_PARSER = new GsonParser(ProjectilesData.class);
  private static final GsonParser WEAPONS_PARSER     = new GsonParser(WeaponsData.class);
  private static final GsonParser SHOP_PARSER        = new GsonParser(ShopData.class);
  private static final PNGParser PNG_PARSER          = new PNGParser();

  public static BossesData getBosses(Cache cache)
      throws ParserException, IOException {
    return (BossesData) cache.get(FILE_BOSSES, BOSSES_PARSER);
  }

  public static CreepsData getCreeps(Cache cache)
      throws ParserException, IOException {
    return (CreepsData) cache.get(FILE_CREEPS, CREEPS_PARSER);
  }

  public static Image getImage(Cache cache, String id)
      throws ParserException, IOException {
    return (Image) cache.get(id, PNG_PARSER);
  }

  public static LevelData getLevel(Cache cache, String level)
      throws ParserException, IOException {
    return (LevelData) cache.get(
      DIR_LEVELS + File.separator + level + EXT_JS, LEVEL_PARSER);
  }

  public static PlayersData getPlayers(Cache cache)
      throws ParserException, IOException {
    return (PlayersData) cache.get(FILE_PLAYERS, PLAYERS_PARSER);
  }

  public static WeaponsData getWeapons(Cache cache)
      throws ParserException, IOException {
    return (WeaponsData) cache.get(FILE_WEAPONS, WEAPONS_PARSER);
  }

  public static ProjectilesData getProjectiles(Cache cache)
      throws ParserException, IOException {
    return (ProjectilesData) cache.get(FILE_PROJECTILES, PROJECTILES_PARSER);
  }

  /**
   * Create a new animated sprite sheet based on some sprite data.
   * @param cache the cache to use for retrieval
   * @param orientation the orientation of the sheet (when rendering)
   * @param angle the rotation of the sheet (when rendering)
   * @param sprite the sprite data
   * @return a new animated sprite sheet
   * @throws ParserException if parsing fails
   * @throws IOException if IO fails
   */
  public static AnimatedSheet getAnimatedSheet(Cache cache,
      Orientation orientation, int angle, SpriteData sprite)
      throws ParserException, IOException {
    SpriteSheet sheet = getSpriteSheet(cache, sprite);
    return new AnimatedSheet(
      sprite.framerate,
      sprite.offset.x,
      sprite.offset.y,
      orientation,
      angle,
      sheet
    );
  }

  public static ShopData getShop(Cache cache)
      throws ParserException, IOException {
    return (ShopData) cache.get(FILE_SHOP, SHOP_PARSER);
  }

  public static SpriteSheet getSpriteSheet(Cache cache, SpriteData sprite)
      throws ParserException, IOException {
    Image img = (Image) cache.get(sprite.sprite, PNG_PARSER);

    return new SpriteSheet(img, sprite.tileSize.width, sprite.tileSize.height, sprite.spacing);
  }
}
