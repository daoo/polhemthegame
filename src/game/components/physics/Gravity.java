/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ComponentType;
import game.components.Message;
import game.components.interfaces.ILogicComponent;
import game.pods.GameTime;
import math.Vector2;

public class Gravity implements ILogicComponent {
  private static final float FACTOR = 100.0f;

  private final Movement movement;

  public Gravity(Movement movement) {
    this.movement = movement;
  }

  @Override
  public void update(GameTime time) {
    float g = time.frame * FACTOR;
    movement.addVelocity(new Vector2(0, g));
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.GRAVITY;
  }
}
