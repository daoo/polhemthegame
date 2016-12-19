/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json.types;

public class ProjectileData {
  public boolean collides;
  public boolean gravity;
  public Size hitbox;

  public AOEData aoe;
  public float damage;

  public int targets;
  public int duration;
  public int range;
  public float speed;

  public SpriteData sprite;
  public String texture;
}
