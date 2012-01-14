/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.graphics.DummyAnimation;
import game.components.holdables.weapons.AutomaticWeapon;
import game.components.holdables.weapons.SingleWeapon;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.IAnimatedComponent;

import java.io.IOException;

import loader.data.json.ProjectilesData;
import loader.data.json.WeaponsData;
import loader.data.json.types.ProjectileData;
import loader.data.json.types.WeaponData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;
import math.Vector2;

public class WeaponFactory {
  private final Rectangle bounds;
  private final WeaponsData weapons;
  private final ProjectilesData projectiles;

  /**
   * Construct a new weapon factory that can make any weapon.
   * @param bounds the bounds within projectiles can exists
   * @throws ParserException when parsing file data fails
   * @throws IOException when reading files fails
   */
  public WeaponFactory(Rectangle bounds) throws ParserException, IOException {
    this.bounds = bounds;

    weapons = CacheTool.getWeapons(Locator.getCache());
    projectiles = CacheTool.getProjectiles(Locator.getCache());
  }

  public Weapon makeWeapon(String name)
      throws ParserException, IOException {
    WeaponData data = weapons.getWeapon(name);

    Vector2 muzzleOffset =
      new Vector2(data.muzzleOffset.x, data.muzzleOffset.y);

    ProjectileData projectileData = projectiles.getProjectile(data.projectile);

    IAnimatedComponent anim = null;
    if (data.sprite == null) {
      anim = new DummyAnimation();
    } else {
      anim = CacheTool.getRSheet(Locator.getCache(), data.sprite);
    }

    if (data.automatic) {
      return new AutomaticWeapon(
        muzzleOffset,
        data.reloadTime,
        60.0f / data.rpm,
        data.clipSize,
        data.launchAngle,
        anim,
        new ProjectileFactory(bounds, projectileData)
      );
    } else {
      return new SingleWeapon(
        muzzleOffset,
        data.reloadTime,
        60.0f / data.rpm,
        data.clipSize,
        data.launchAngle,
        anim,
        new ProjectileFactory(bounds, projectileData)
      );
    }
  }
}
