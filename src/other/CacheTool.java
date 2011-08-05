/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package other;

import java.io.IOException;

import loader.Cache;
import loader.data.DataException;
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

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import basics.Vector2;

import components.graphics.RSheet;
import components.graphics.RSheetOnce;
import components.holdables.weapons.AutomaticWeapon;
import components.holdables.weapons.SingleWeapon;
import components.holdables.weapons.Weapon;

import entities.projectiles.ProjectileTemplate;

public class CacheTool {
  public static Image getImage(final Cache cache, final String id)
    throws ParserException, IOException {
    return (Image) cache.getCold(id, new PNGParser());
  }

  public static SpriteSheet getSpriteSheet(final Cache cache, final SpriteData sprite)
    throws ParserException, IOException {
    return (SpriteSheet) cache.getCold(sprite.sprite, new SpriteSheetParser(sprite.tileSize[0], sprite.tileSize[1]));
  }

  public static RSheet getRSheet(final Cache cache, final SpriteData sprite)
    throws ParserException, IOException {
    final SpriteSheet sheet = CacheTool.getSpriteSheet(cache, sprite);
    return new RSheet(sprite.framerate, sprite.offset[0], sprite.offset[1], sheet);
  }

  public static RSheetOnce getRSheetOnce(final Cache cache, final SpriteData sprite)
    throws ParserException, IOException {
    final SpriteSheet sheet = CacheTool.getSpriteSheet(cache, sprite);
    return new RSheetOnce(sprite.framerate, sprite.offset[0], sprite.offset[1], sheet);
  }

  public static LevelData getLevel(final Cache cache, final String level)
    throws ParserException, IOException {
    return (LevelData) cache.getCold("levels/" + level + ".js", new GsonParser(LevelData.class));
  }

  public static Weapon getWeapon(final Cache cache, final String name)
    throws DataException, ParserException, IOException {
    final WeaponsData weapons = (WeaponsData) cache.getCold("weapons.js", new GsonParser(WeaponsData.class));
    final WeaponData weapon = weapons.getWeapon(name);

    final Vector2 m = new Vector2(weapon.muzzleOffset[0], weapon.muzzleOffset[1]);
    final ProjectileData p = CacheTool.getProjectile(cache, weapon.projectile);
    if (weapon.automatic) {
      final RSheet anim = CacheTool.getRSheet(cache, weapon.sprite);
      return new AutomaticWeapon(m, weapon.reloadTime, 60.0f / weapon.rpm,
                                 weapon.clipSize, weapon.launchAngle, anim,
                                 new ProjectileTemplate(p));
    }
    else {
      final RSheetOnce anim = CacheTool.getRSheetOnce(cache, weapon.sprite);
      return new SingleWeapon(m, weapon.reloadTime, 60.0f / weapon.rpm,
                              weapon.clipSize, weapon.launchAngle, anim,
                              new ProjectileTemplate(p));
    }
  }

  public static ProjectileData getProjectile(final Cache cache, final String projectile)
    throws DataException, ParserException, IOException {
    final ProjectilesData data = (ProjectilesData) cache.getCold("projectiles.js", new GsonParser(ProjectilesData.class));
    return data.getProjectile(projectile);
  }

  public static PlayersData getPlayers(final Cache cache)
    throws ParserException, IOException {
    return (PlayersData) cache.getCold("players.js", new GsonParser(PlayersData.class));
  }

  public static CreepsData getCreeps(final Cache cache)
    throws ParserException, IOException {
    return (CreepsData) cache.getCold("creeps.js", new GsonParser(CreepsData.class));
  }
}
