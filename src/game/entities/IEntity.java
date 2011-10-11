/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.interfaces.IRenderComponent;
import game.components.misc.IAction;
import game.components.physics.AABB;
import game.entities.groups.EntityType;
import game.world.World;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public interface IEntity {
  AABB getBody();
  EntityType getType();

  ILogicComponent getComponent(ComponentType componentType);

  void update(GameTime time, World world);
  void render(Graphics g);

  void sendMessage(ComponentMessage message, Object args);

  void addRenderComponent(IRenderComponent comp);
  void addLogicComponent(ILogicComponent comp);
  void clearComponents();
  
  void addAction(IAction action);
}