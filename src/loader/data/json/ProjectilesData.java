/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.ArrayList;

import loader.IData;
import loader.data.DataException;
import loader.data.json.types.ProjectileData;

public class ProjectilesData implements IData {
  public ArrayList<ProjectileData> projectiles;

  public ProjectileData getProjectile(String name) throws DataException {
    for (ProjectileData p : projectiles) {
      if (p.name.equals(name)) {
        return p;
      }
    }

    throw new DataException("No projectile by the name \"" + name + "\".");
  }

  @Override
  public void close() throws IOException {
    // Do nothing
  }
}
