/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import org.newdawn.slick.Graphics;

import java.io.IOException;

import game.components.graphics.AnimatedSheet;
import game.components.holdables.weapons.Weapon;
import game.components.holdables.weapons.WeaponMode;
import game.misc.CacheTool;
import game.misc.Locator;
import game.types.Orientation;
import loader.data.json.ProjectilesData;
import loader.data.json.WeaponsData;
import loader.data.json.types.ProjectileData;
import loader.data.json.types.WeaponData;
import loader.parser.ParserException;
import math.Aabb;
import math.Vector2;

/**
 * Factory for weapons.
 */
public class WeaponFactory {
  private final Aabb mBounds;
  private final WeaponsData mWeapons;
  private final ProjectilesData mProjectiles;
  private final Graphics mStatics;

  /**
   * Construct a new weapon factory that can make any weapon.
   *
   * @param bounds the bounds within projectiles can exists
   * @param statics graphics context were statics can be drawn
   * @throws ParserException when parsing file data fails
   * @throws IOException when reading files fails
   */
  public WeaponFactory(Aabb bounds, Graphics statics) throws ParserException, IOException {
    mBounds = bounds;
    mStatics = statics;

    mWeapons = CacheTool.getWeapons(Locator.getCache());
    mProjectiles = CacheTool.getProjectiles(Locator.getCache());
  }

  /**
   * Make a new weapon by fetching data from the cache.
   *
   * @param name the name of the weapon
   * @param orientation the orientation of the weapon (affects direction of projectiles)
   * @return a weapon
   * @throws ParserException if parsing fails
   * @throws IOException if file IO fails
   */
  public Weapon makeWeapon(String name, Orientation orientation) throws ParserException,
      IOException {
    WeaponData data = mWeapons.weapons.get(name);

    Vector2 muzzle = new Vector2(data.muzzleOffset.x, data.muzzleOffset.y);

    ProjectileData projectileData = mProjectiles.getProjectile(data.projectile);

    AnimatedSheet anim = CacheTool
        .getAnimatedSheet(Locator.getCache(), orientation, 0, data.sprite);

    ProjectileFactory factory = new ProjectileFactory(mBounds, data.launchAngle, data.spread,
        orientation, projectileData, mStatics);

    return new Weapon(muzzle, data.automatic ? WeaponMode.AUTOMATIC : WeaponMode.SINGLE,
        data.reloadTime, (int) (60000 / data.rpm), data.clipSize, orientation, anim, factory);
  }
}
