package game.factories;

import game.CacheTool;
import game.components.graphics.RSheet;
import game.components.holdables.weapons.AutomaticWeapon;
import game.components.holdables.weapons.SingleWeapon;
import game.components.holdables.weapons.Weapon;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.ProjectilesData.ProjectileData;
import loader.data.json.WeaponsData.WeaponData;
import loader.parser.ParserException;
import main.Locator;
import math.Vector2;

public class MiscFactory {
  public static Weapon makeWeapon(WeaponData weapon)
      throws DataException, ParserException, IOException {
    Vector2 m        = new Vector2(weapon.muzzleOffset.x, weapon.muzzleOffset.y);
    ProjectileData p = CacheTool.getProjectile(Locator.getCache(), weapon.projectile);
    RSheet anim      = CacheTool.getRSheet(Locator.getCache(), weapon.sprite);
    if (weapon.automatic) {
      return new AutomaticWeapon(
        m,
        weapon.reloadTime,
        60.0f / weapon.rpm,
        weapon.clipSize,
        weapon.launchAngle,
        anim,
        new ProjectileTemplate(p)
      );
    }
    else {
      return new SingleWeapon(
        m,
        weapon.reloadTime,
        60.0f / weapon.rpm,
        weapon.clipSize,
        weapon.launchAngle,
        anim,
        new ProjectileTemplate(p)
      );
    }
  }
}
