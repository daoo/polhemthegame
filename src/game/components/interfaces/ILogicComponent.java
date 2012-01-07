/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.interfaces;

import game.components.Message;
import game.pods.GameTime;

public interface ILogicComponent {
  void update(GameTime time);

  void reciveMessage(Message message, Object args);
}
