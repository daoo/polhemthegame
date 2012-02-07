/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json.types;

import java.util.Map;

public class UnitData {
  public Size hitbox;
  public int speed;
  public int hitpoints;

  public Orientation orientation;

  public Map<String, SpriteData> sprites;
}
