/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.graphics.RSheet;
import game.components.holdables.weapons.AutomaticWeapon;
import game.components.holdables.weapons.SingleWeapon;
import game.components.holdables.weapons.Weapon;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.ProjectilesData;
import loader.data.json.ProjectilesData.ProjectileData;
import loader.data.json.WeaponsData;
import loader.data.json.WeaponsData.WeaponData;
import loader.parser.ParserException;
import main.Locator;
import math.Vector2;

public class WeaponFactory {
  private final WeaponsData weapons;
  private final ProjectilesData projectiles;

  public WeaponFactory() throws ParserException, IOException {
    weapons = CacheTool.getWeapons(Locator.getCache());
    projectiles = CacheTool.getProjectiles(Locator.getCache());
  }

  public Weapon makeWeapon(String name)
      throws DataException, ParserException, IOException {
    WeaponData data = weapons.getWeapon(name);

    Vector2 muzzleOffset =
      new Vector2(data.muzzleOffset.x, data.muzzleOffset.y);

    ProjectileData projectileData = projectiles.getProjectile(data.projectile);
    RSheet anim = CacheTool.getRSheet(Locator.getCache(), data.sprite);
    if (data.automatic) {
      return new AutomaticWeapon(
        muzzleOffset,
        data.reloadTime,
        60.0f / data.rpm,
        data.clipSize,
        data.launchAngle,
        anim,
        new ProjectileFactory(projectileData)
      );
    }
    else {
      return new SingleWeapon(
        muzzleOffset,
        data.reloadTime,
        60.0f / data.rpm,
        data.clipSize,
        data.launchAngle,
        anim,
        new ProjectileFactory(projectileData)
      );
    }
  }
}
