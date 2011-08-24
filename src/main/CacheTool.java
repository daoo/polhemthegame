/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package main;

import java.io.File;
import java.io.IOException;

import loader.Cache;
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

import components.graphics.RSheet;
import components.graphics.animations.Idle;
import components.holdables.weapons.AutomaticWeapon;
import components.holdables.weapons.SingleWeapon;
import components.holdables.weapons.Weapon;

import entities.projectiles.ProjectileTemplate;

public class CacheTool {
  public static final String EXT_JS = ".js";

  public static final String DIR_LEVELS       = "levels";
  public static final String FILE_BOSSES      = "bosses.js";
  public static final String FILE_CREEPS      = "creeps.js";
  public static final String FILE_PLAYERS     = "players.js";
  public static final String FILE_PROJECTILES = "projectiles.js";
  public static final String FILE_WEAPONS     = "weapons.js";

  public static Image getImage(final Cache cache, final String id)
    throws ParserException, IOException {
    return (Image) cache.getCold(id, new PNGParser());
  }

  public static SpriteSheet getSpriteSheet(final Cache cache, final SpriteData sprite)
    throws ParserException, IOException {
    return (SpriteSheet) cache.getCold(sprite.sprite,
                                       new SpriteSheetParser(sprite.tileSize.width,
                                                             sprite.tileSize.height));
  }

  public static RSheet getRSheet(final Cache cache, final SpriteData sprite)
    throws ParserException, IOException {
    final SpriteSheet sheet = CacheTool.getSpriteSheet(cache, sprite);
    return new RSheet(sprite.framerate,
                      sprite.offset.x, sprite.offset.y,
                      sheet, new Idle());
  }

  public static LevelData getLevel(final Cache cache, final String level)
    throws ParserException, IOException {
    return (LevelData) cache.getCold(DIR_LEVELS + File.separator + level + EXT_JS, new GsonParser(LevelData.class));
  }

  public static Weapon getWeapon(final Cache cache, final String name)
    throws DataException, ParserException, IOException {
    final WeaponsData weapons = (WeaponsData) cache.getCold(FILE_WEAPONS, new GsonParser(WeaponsData.class));
    final WeaponData weapon = weapons.getWeapon(name);

    final Vector2 m = new Vector2(weapon.muzzleOffset.x, weapon.muzzleOffset.y);
    final ProjectileData p = CacheTool.getProjectile(cache, weapon.projectile);
    final RSheet anim = CacheTool.getRSheet(cache, weapon.sprite);
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

  public static ProjectileData getProjectile(final Cache cache, final String projectile)
    throws DataException, ParserException, IOException {
    final ProjectilesData data = (ProjectilesData) cache.getCold(FILE_PROJECTILES, new GsonParser(ProjectilesData.class));
    return data.getProjectile(projectile);
  }

  public static PlayersData getPlayers(final Cache cache)
    throws ParserException, IOException {
    return (PlayersData) cache.getCold(FILE_PLAYERS, new GsonParser(PlayersData.class));
  }

  public static CreepsData getCreeps(final Cache cache)
    throws ParserException, IOException {
    return (CreepsData) cache.getCold(FILE_CREEPS, new GsonParser(CreepsData.class));
  }

  public static BossesData getBosses(final Cache cache)
    throws ParserException, IOException {
    return (BossesData) cache.getCold(FILE_BOSSES, new GsonParser(BossesData.class));
  }

  public static BossData getBoss(final Cache cache, final String boss)
  throws ParserException, IOException, DataException {
    final BossesData data = CacheTool.getBosses(cache);

    return data.getBoss(boss);
  }
}
