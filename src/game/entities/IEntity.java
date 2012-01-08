/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.Message;
import game.pods.GameTime;
import game.world.World;
import math.Rectangle;

import org.newdawn.slick.Graphics;

import debug.IDebuggable;

/**
 * An interface for objects that can exist in the world.
 * Specifies what a entity is/has.
 */
public interface IEntity extends IDebuggable {
  /**
   * Body getter.
   * All entities should have a physical size/body.
   * @return the body for this entity
   */
  Rectangle getBody();

  World getWorld();
  void setWorld(World world);

  /**
   * Sends a message to all components that belong to this entity.
   * @param message the message to send
   * @param args pass along some arguments, can be anything
   */
  void sendMessage(Message message, Object args);

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
}
