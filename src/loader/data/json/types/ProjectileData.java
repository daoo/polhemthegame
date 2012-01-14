/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json.types;

public class ProjectileData {
  public boolean collides, gravity;
  public Size hitbox;

  public AOEData aoe;
  public float damage;

  public int targets;
  public float duration;
  public float range;
  public float speed;

  public SpriteData sprite;
  public String texture;
}
