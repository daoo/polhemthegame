/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.types.GameTime;

public interface BossState {
  void start(GameTime time);

  void update(GameTime time);

  boolean isFinished();
}
