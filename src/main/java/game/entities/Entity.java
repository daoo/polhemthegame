/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import org.newdawn.slick.Graphics;

import debug.Debuggable;
import game.types.GameTime;
import game.types.Message;

/**
 * Describes objects that can exist within the game world.
 */
public interface Entity extends Debuggable {
  /**
   * Sends a message to all components that belong to this entity.
   *
   * @param message the message to send
   * @param args pass along some arguments, can be anything
   */
  void sendMessage(Message message, Object args);

  /**
   * Render the entity.
   *
   * @param g the graphics context to use
   */
  void render(Graphics g);

  /**
   * Updates logic.
   *
   * @param time the current game time
   */
  void update(GameTime time);

  /**
   * @return true if the the entity is active. Inactive entities does not interact with the rest of
   * the world and are eventually removed.
   */
  boolean isActive();

  /**
   * @return true if the render should be kept after becoming inactive
   */
  boolean keepRendering();

  void remove();
}
