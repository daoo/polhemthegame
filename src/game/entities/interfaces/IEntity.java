/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities.interfaces;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.physics.AABB;
import game.entities.groups.EntityType;
import game.world.World;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public interface IEntity {
  EntityType getType();

  AABB getBody();

  ILogicComponent getComponent(ComponentType componentType);

  void update(GameTime time, World world);
  void render(Graphics g);

  void sendMessage(ComponentMessage message, Object args);
}
