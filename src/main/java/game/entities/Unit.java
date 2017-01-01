/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.misc.Life;
import game.components.physics.Movement;
import game.ui.hud.infobar.InfoBar;

/**
 * Simple class for keeping track of components in the factories.
 */
@SuppressWarnings("InstanceVariableNamingConvention")
public class Unit {
  public final EntityImpl entity;
  public final Movement movement;
  public final Life life;
  public final InfoBar infoBar;

  public Unit(EntityImpl entity, Movement movement, Life life, InfoBar infoBar) {
    this.entity = entity;
    this.movement = movement;
    this.life = life;
    this.infoBar = infoBar;
  }
}
