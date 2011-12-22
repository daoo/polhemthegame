/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.interfaces.IRenderComponent;
import game.pods.GameTime;
import game.world.World;
import math.Rectangle;

import org.newdawn.slick.Graphics;

import util.Node;

/**
 * An interface for objects that can exist in the world.
 * More specifically it's an component container.
 */
public interface IEntity {
  void addLogicComponent(ILogicComponent comp);
  void addRenderComponent(IRenderComponent comp);

  /**
   * Returns the component with the specified type.
   * @param componentType the component type to search for
   * @return a component matching the specified type, or null if there is no
   *         such component
   */
  ILogicComponent getComponent(ComponentType componentType);

  /**
   * Body getter.
   * All entities should have a physical size/body.
   * @return the body for this entity
   */
  Rectangle getBody();

  /**
   * Entity type getter.
   * @return the type of this entity
   */
  EntityType getType();

  World getWorld();
  void setWorld(World world);

  /**
   * Sends a message to all components that belong to this entity.
   * @param message the message to send
   * @param args pass along some arguments, can be anything
   */
  void sendMessage(ComponentMessage message, Object args);

  /**
   * Render the entity.
   * @param g the graphics context to use
   */
  void render(Graphics g);

  /**
   * Updates logic.
   * @param time the current game time
   */
  void update(GameTime time);

  boolean isActive();
  void remove();

  Node<Object> debugInfo();
}
