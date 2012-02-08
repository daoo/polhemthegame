/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json.types;


public class WeaponData {
  public String name;

  public boolean automatic;
  public int clipSize;
  public float rpm;
  public int reloadTime;

  public int spread;
  public int launchAngle;

  public SpriteData sprite;
  public String texture;

  public String projectile;
  public Offset muzzleOffset;
}
