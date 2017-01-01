/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components;

import game.types.GameTime;
import game.types.Message;

public interface LogicComponent {
  void update(GameTime time);

  void reciveMessage(Message message, Object args);
}
