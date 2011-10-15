/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.actions.IAction;
import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.interfaces.IRenderComponent;
import game.components.physics.AABB;
import game.entities.groups.EntityType;
import game.world.World;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

/**
 * An interface for objects that can exist in the world.
 * More specifically it's an component container.
 */
public interface IEntity {
  void setWorld(World world);

  /**
   * Body getter.
   * All entities should have a physical size/body.
   * @return the body for this entity
   */
  AABB getBody();

  /**
   * Entity type getter.
   * @return the type of this entity
   */
  EntityType getType();

  /**
   * Returns the component with the specified type.
   * @param componentType the component type to search for
   * @return a component matching the specified type, or null if there is no
   *         such component
   */
  ILogicComponent getComponent(ComponentType componentType);

  /**
   * Updates logic.
   * @param time the current game time
   */
  void update(GameTime time);

  /**
   * Render the entity.
   * @param g the graphics context to use
   */
  void render(Graphics g);

  /**
   * Sends a message to all components that belong to this entity.
   * @param message the message to send
   * @param args pass along some arguments, can be anything
   */
  void sendMessage(ComponentMessage message, Object args);

  void addRenderComponent(IRenderComponent comp);
  void addLogicComponent(ILogicComponent comp);

  void addAction(IAction action);
}
