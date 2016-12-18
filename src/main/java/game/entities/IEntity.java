/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import org.newdawn.slick.Graphics;

import debug.IDebuggable;
import game.types.GameTime;
import game.types.Message;

/**
 * An interface for objects that can exist in the world.
 * Specifies what a entity is/has.
 */
public interface IEntity extends IDebuggable {
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
