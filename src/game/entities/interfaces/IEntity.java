/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities.interfaces;

import game.components.physics.AABB;

public interface IEntity extends IObject {
  public AABB getBody();
}
