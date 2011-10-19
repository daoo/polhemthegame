/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game;

import game.components.graphics.RSheet;
import game.components.graphics.animations.Idle;
import game.components.holdables.weapons.AutomaticWeapon;
import game.components.holdables.weapons.SingleWeapon;
import game.components.holdables.weapons.Weapon;
import game.entities.ProjectileTemplate;

import java.io.File;
import java.io.IOException;

import loader.ICache;
import loader.data.DataException;
import loader.data.json.BossesData;
import loader.data.json.BossesData.BossData;
import loader.data.json.CreepsData;
import loader.data.json.LevelData;
import loader.data.json.PlayersData;
import loader.data.json.ProjectilesData;
import loader.data.json.ProjectilesData.ProjectileData;
import loader.data.json.SpriteData;
import loader.data.json.WeaponsData;
import loader.data.json.WeaponsData.WeaponData;
import loader.parser.GsonParser;
import loader.parser.PNGParser;
import loader.parser.ParserException;
import loader.parser.SpriteSheetParser;
import math.Vector2;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class CacheTool {
  public static final String EXT_JS = ".js";

  public static final String DIR_LEVELS       = "levels";
  public static final String FILE_BOSSES      = "bosses.js";
  public static final String FILE_CREEPS      = "creeps.js";
  public static final String FILE_PLAYERS     = "players.js";
  public static final String FILE_PROJECTILES = "projectiles.js";
  public static final String FILE_WEAPONS     = "weapons.js";

  public static Image getImage(ICache cache, String id)
    throws ParserException, IOException {
    return (Image) cache.getCold(id, new PNGParser());
  }

  public static SpriteSheet getSpriteSheet(ICache cache, SpriteData sprite)
    throws ParserException, IOException {
    return (SpriteSheet) cache.getCold(sprite.sprite,
                                       new SpriteSheetParser(sprite.tileSize.width,
                                                             sprite.tileSize.height));
  }

  public static RSheet getRSheet(ICache cache, SpriteData sprite)
    throws ParserException, IOException {
    SpriteSheet sheet = CacheTool.getSpriteSheet(cache, sprite);
    return new RSheet(sprite.framerate,
                      sprite.offset.x, sprite.offset.y,
                      sheet, new Idle());
  }

  public static LevelData getLevel(ICache cache, String level)
    throws ParserException, IOException {
    return (LevelData) cache.getCold(DIR_LEVELS + File.separator + level + EXT_JS, new GsonParser(LevelData.class));
  }

  public static Weapon getWeapon(ICache cache, String name)
    throws DataException, ParserException, IOException {
    WeaponsData weapons = (WeaponsData) cache.getCold(FILE_WEAPONS, new GsonParser(WeaponsData.class));
    WeaponData weapon = weapons.getWeapon(name);

    Vector2 m = new Vector2(weapon.muzzleOffset.x, weapon.muzzleOffset.y);
    ProjectileData p = CacheTool.getProjectile(cache, weapon.projectile);
    RSheet anim = CacheTool.getRSheet(cache, weapon.sprite);
    if (weapon.automatic) {
      return new AutomaticWeapon(m, weapon.reloadTime, 60.0f / weapon.rpm,
                                 weapon.clipSize, weapon.launchAngle, anim,
                                 new ProjectileTemplate(p));
    }
    else {
      return new SingleWeapon(m, weapon.reloadTime, 60.0f / weapon.rpm,
                              weapon.clipSize, weapon.launchAngle, anim,
                              new ProjectileTemplate(p));
    }
  }

  public static ProjectileData getProjectile(ICache cache, String projectile)
    throws DataException, ParserException, IOException {
    ProjectilesData data = (ProjectilesData) cache.getCold(FILE_PROJECTILES, new GsonParser(ProjectilesData.class));
    return data.getProjectile(projectile);
  }

  public static PlayersData getPlayers(ICache cache)
    throws ParserException, IOException {
    return (PlayersData) cache.getCold(FILE_PLAYERS, new GsonParser(PlayersData.class));
  }

  public static CreepsData getCreeps(ICache cache)
    throws ParserException, IOException {
    return (CreepsData) cache.getCold(FILE_CREEPS, new GsonParser(CreepsData.class));
  }

  public static BossesData getBosses(ICache cache)
    throws ParserException, IOException {
    return (BossesData) cache.getCold(FILE_BOSSES, new GsonParser(BossesData.class));
  }

  public static BossData getBoss(ICache cache, String boss)
  throws ParserException, IOException, DataException {
    BossesData data = CacheTool.getBosses(cache);

    return data.getBoss(boss);
  }
}
