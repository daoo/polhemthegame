/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components;

public enum Message {
  // Damage control
  KILL, DAMAGE,

  // Animation control
  START_ANIMATION, STOP_ANIMATION, START_HOLDABLE,
  STOP_HOLDABLE, START_AT,

  // Events
  COLLIDED_WITH, DEALT_DAMAGE, KILLED_ENTITY
}
