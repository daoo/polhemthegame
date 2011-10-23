/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.pods;

import game.entities.IEntity;

public class Damage {
  public final float ammount;
  public final IEntity source;

  public Damage(IEntity source, float ammount) {
    this.source  = source;
    this.ammount = ammount;
  }
}