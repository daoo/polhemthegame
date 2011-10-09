/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.world.World;
import math.time.GameTime;

import org.newdawn.slick.Graphics;


public class DoubleState implements IRoundState {
  private final IRoundState fgState;
  private IRoundState       bgState;

  public DoubleState(final IRoundState fgState) {
    this.fgState = fgState;
    this.bgState = null;
  }

  @Override
  public void update(final GameTime time, final World world) {
    bgState.update(time, world);
    fgState.update(time, world);
  }

  @Override
  public void render(final Graphics g) {
    bgState.render(g);
    fgState.render(g);
  }

  @Override
  public boolean isFinished() {
    return fgState.isFinished();
  }

  public void start(final IRoundState state) {
    this.bgState = state;
  }
}
