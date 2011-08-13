/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package states;

import loader.data.json.LevelData.BossStateData;

import org.newdawn.slick.Graphics;

import other.GameTime;

public class BossState implements ICompState {
  public BossState(final BossStateData sd) {
    // TODO Implement Boss State
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void render(final Graphics g) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void update(final GameTime time) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void start() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
