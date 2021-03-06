/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.LogicComponent;
import game.types.GameTime;
import game.types.Message;
import math.Vector2;

public class Gravity implements LogicComponent {
  private static final float FACTOR = 100.0f;

  private final Movement mMovement;

  public Gravity(Movement movement) {
    mMovement = movement;
  }

  @Override
  public void update(GameTime time) {
    float g = time.frame * FACTOR;
    mMovement.addVelocity(new Vector2(0, g));
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
