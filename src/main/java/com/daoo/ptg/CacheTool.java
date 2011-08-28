/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.ptg;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import com.daoo.components.graphics.RSheet;
import com.daoo.components.graphics.animations.Idle;
import com.daoo.components.holdables.weapons.AutomaticWeapon;
import com.daoo.components.holdables.weapons.SingleWeapon;
import com.daoo.components.holdables.weapons.Weapon;
import com.daoo.entities.projectiles.ProjectileTemplate;
import com.daoo.loader.ICache;
import com.daoo.loader.data.DataException;
import com.daoo.loader.data.json.BossesData;
import com.daoo.loader.data.json.BossesData.BossData;
import com.daoo.loader.data.json.CreepsData;
import com.daoo.loader.data.json.LevelData;
import com.daoo.loader.data.json.PlayersData;
import com.daoo.loader.data.json.ProjectilesData;
import com.daoo.loader.data.json.ProjectilesData.ProjectileData;
import com.daoo.loader.data.json.SpriteData;
import com.daoo.loader.data.json.WeaponsData;
import com.daoo.loader.data.json.WeaponsData.WeaponData;
import com.daoo.loader.parser.GsonParser;
import com.daoo.loader.parser.PNGParser;
import com.daoo.loader.parser.ParserException;
import com.daoo.loader.parser.SpriteSheetParser;
import com.daoo.math.Vector2;

public class CacheTool {
  public static final String EXT_JS = ".js";

  public static final String DIR_LEVELS       = "levels";
  public static final String FILE_BOSSES      = "bosses.js";
  public static final String FILE_CREEPS      = "creeps.js";
  public static final String FILE_PLAYERS     = "players.js";
  public static final String FILE_PROJECTILES = "projectiles.js";
  public static final String FILE_WEAPONS     = "weapons.js";

  public static Image getImage(final ICache cache, final String id)
    throws ParserException, IOException {
    return (Image) cache.getCold(id, new PNGParser());
  }

  public static SpriteSheet getSpriteSheet(final ICache cache, final SpriteData sprite)
    throws ParserException, IOException {
    return (SpriteSheet) cache.getCold(sprite.sprite,
                                       new SpriteSheetParser(sprite.tileSize.width,
                                                             sprite.tileSize.height));
  }

  public static RSheet getRSheet(final ICache cache, final SpriteData sprite)
    throws ParserException, IOException {
    final SpriteSheet sheet = CacheTool.getSpriteSheet(cache, sprite);
    return new RSheet(sprite.framerate,
                      sprite.offset.x, sprite.offset.y,
                      sheet, new Idle());
  }

  public static LevelData getLevel(final ICache cache, final String level)
    throws ParserException, IOException {
    return (LevelData) cache.getCold(DIR_LEVELS + File.separator + level + EXT_JS, new GsonParser(LevelData.class));
  }

  public static Weapon getWeapon(final ICache cache, final String name)
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

  public static ProjectileData getProjectile(final ICache cache, final String projectile)
    throws DataException, ParserException, IOException {
    final ProjectilesData data = (ProjectilesData) cache.getCold(FILE_PROJECTILES, new GsonParser(ProjectilesData.class));
    return data.getProjectile(projectile);
  }

  public static PlayersData getPlayers(final ICache cache)
    throws ParserException, IOException {
    return (PlayersData) cache.getCold(FILE_PLAYERS, new GsonParser(PlayersData.class));
  }

  public static CreepsData getCreeps(final ICache cache)
    throws ParserException, IOException {
    return (CreepsData) cache.getCold(FILE_CREEPS, new GsonParser(CreepsData.class));
  }

  public static BossesData getBosses(final ICache cache)
    throws ParserException, IOException {
    return (BossesData) cache.getCold(FILE_BOSSES, new GsonParser(BossesData.class));
  }

  public static BossData getBoss(final ICache cache, final String boss)
  throws ParserException, IOException, DataException {
    final BossesData data = CacheTool.getBosses(cache);

    return data.getBoss(boss);
  }
}
