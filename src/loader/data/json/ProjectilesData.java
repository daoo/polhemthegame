/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.Map;

import loader.IData;
import loader.data.json.types.ProjectileData;

public class ProjectilesData implements IData {
  private Map<String, ProjectileData> projectiles;

  public ProjectileData getProjectile(String name) {
    return projectiles.get(name);
  }

  @Override
  public void close() throws IOException {
    // Do nothing
  }
}
