/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.interfaces;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.pods.GameTime;

public interface ILogicComponent {
  void update(GameTime time);

  void reciveMessage(ComponentMessage message, Object args);

  ComponentType getComponentType();
}
