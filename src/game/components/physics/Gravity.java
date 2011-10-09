/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.interfaces.IEntity;
import math.Vector2;
import math.time.GameTime;

public class Gravity implements ILogicComponent {
  public static final float FACTOR = 100.0f;

  private IEntity owner;

  public Gravity() {
  }

  @Override
  public void update(final GameTime time) {
    final float g = time.getFrameLength() * FACTOR;
    owner.getBody().addVelocity(new Vector2(0, g));
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.GRAVITY;
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner = owner;
  }
}
