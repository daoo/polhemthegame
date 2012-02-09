/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.interfaces;

import game.types.GameTime;
import game.types.Message;

public interface ILogicComponent {
  void update(GameTime time);

  void reciveMessage(Message message, Object args);
}
