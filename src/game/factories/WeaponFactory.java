/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.graphics.DummyAnimation;
import game.components.holdables.weapons.Weapon;
import game.components.holdables.weapons.WeaponMode;
import game.components.interfaces.IAnimatedComponent;
import game.types.Orientation;

import java.io.IOException;

import loader.data.json.ProjectilesData;
import loader.data.json.WeaponsData;
import loader.data.json.types.ProjectileData;
import loader.data.json.types.WeaponData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;
import math.Vector2;

/**
 * Factory for weapons.
 */
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

  /**
   * Make a new weapon by fetching data from the cache.
   * @param name the name of the weapon
   * @param orientation the orientation of the weapon (affects direction of projectiles)
   * @return a weapon
   * @throws ParserException if parsing fails
   * @throws IOException if file IO fails
   */
  public Weapon makeWeapon(String name, Orientation orientation)
      throws ParserException, IOException {
    WeaponData data = weapons.getWeapon(name);

    Vector2 muzzleOffset =
      new Vector2(data.muzzleOffset.x, data.muzzleOffset.y);

    ProjectileData projectileData = projectiles.getProjectile(data.projectile);

    IAnimatedComponent anim = null;
    if (data.sprite == null) {
      anim = new DummyAnimation();
    } else {
      anim = CacheTool.getAnimatedSheet(Locator.getCache(), orientation, 0, data.sprite);
    }

    return new Weapon(
      muzzleOffset,
      data.automatic ? WeaponMode.AUTOMATIC : WeaponMode.SINGLE,
      data.reloadTime,
      60.0f / data.rpm,
      data.clipSize,
      orientation,
      // FIXME: data.launchAngle,
      anim,
      new ProjectileFactory(bounds, projectileData)
    );
  }
}
