/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.util.ArrayList;

import loader.data.DataException;
import loader.data.IClosable;

public class ProjectilesData implements IClosable {
  public class ProjectileData {
    public class AOE {
      public float      radius;
      public boolean    keepEffect;
      public SpriteData explosionSprite;
      public float      damage;
    }

    public String     name;

    public boolean    collides, gravity;
    public Size       hitbox;

    public AOE        aoe;
    public float      damage;

    public int        targets;
    public float      duration;
    public float      range;
    public float      speed;

    public SpriteData sprite;
    public String     texture;
  }

  public ArrayList<ProjectileData> projectiles;

  public ProjectileData getProjectile(final String name) throws DataException {
    for (final ProjectileData p : projectiles) {
      if (p.name.equals(name)) {
        return p;
      }
    }

    throw new DataException("No projectile by the name \"" + name + "\".");
  }

  @Override
  public void close() throws DataException {
    // Do nothing
  }
}
