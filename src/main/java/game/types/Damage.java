/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.types;

import game.entities.Entity;

@SuppressWarnings("InstanceVariableNamingConvention")
public class Damage {
  public final float ammount;
  public final Entity source;

  public Damage(Entity source, float ammount) {
    this.source = source;
    this.ammount = ammount;
  }
}
