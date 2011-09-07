/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.physics;

import game.components.ComponentMessages;
import game.components.interfaces.ICompUpdate;
import math.Vector2;
import math.time.GameTime;


public class Gravity implements ICompUpdate {
  public static final float FACTOR = 100.0f;
  
  private final AABB body;
  private final float factor;
  
  public Gravity(final AABB body, final float factor) {
    this.body = body;
    this.factor = factor;
  }

  @Override
  public void update(GameTime time) {
    final float g = time.getFrameLength() * factor;
    body.addVelocity(new Vector2(0, g));
  }

  @Override
  public void reciveMessage(ComponentMessages message) {
    // Do nothing
  }
}
