/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.pods.GameTime;

public interface IBossState {
  void start(GameTime time);

  void update(GameTime time);

  boolean isFinished();

  BossState getNextState();
}