/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.pods;

import game.components.misc.Life;
import game.components.physics.Movement;
import game.entities.Entity;

/**
 * Simple class for keeping track of components in the factories.
 */
public class Unit {
  public final Entity entity;
  public final Movement movement;
  public final Life life;

  public Unit(Entity entity, Movement movement, Life life) {
    this.entity = entity;
    this.movement = movement;
    this.life = life;
  }
}
