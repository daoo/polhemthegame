/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.triggers.conditions;

import game.Entities;
import game.World;
import math.Rectangle;
import math.time.GameTime;

import components.interfaces.IEntity;

public class RectContains implements ICondition {
  private final Rectangle rect;
  private final Entities entities;

  public RectContains(final Rectangle rect, final Entities entities) {
    this.rect = rect;
    this.entities = entities;
  }
  
  @Override
  public boolean evaluate(final GameTime time, final World world) {
    for (final IEntity e : world.get(entities)) {
      if (rect.isContaining(e.getBody())) {
        return true;
      }
    }
    
    return false;
  }
}
