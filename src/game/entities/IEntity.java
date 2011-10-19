/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.actions.IAction;
import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.interfaces.IRenderComponent;
import game.entities.groups.EntityType;
import game.world.World;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

/**
 * An interface for objects that can exist in the world.
 * More specifically it's an component container.
 */
public interface IEntity {
  void addAction(IAction action);

  void addLogicComponent(ILogicComponent comp);
  void addRenderComponent(IRenderComponent comp);

  boolean equals(IEntity other);

  /**
   * Body getter.
   * All entities should have a physical size/body.
   * @return the body for this entity
   */
  Rectangle getBody();

  /**
   * Returns the component with the specified type.
   * @param componentType the component type to search for
   * @return a component matching the specified type, or null if there is no
   *         such component
   */
  ILogicComponent getComponent(ComponentType componentType);

  /**
   * Entity type getter.
   * @return the type of this entity
   */
  EntityType getType();

  World getWorld();

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
  void setWorld(World world);

  /**
   * Updates logic.
   * @param time the current game time
   */
  void update(GameTime time);
}
