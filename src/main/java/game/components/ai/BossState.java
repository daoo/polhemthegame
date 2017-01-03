/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.types.GameTime;

// TODO: Better naming
public interface BossState {
  void start(GameTime time);

  BossState update(GameTime time);
}
