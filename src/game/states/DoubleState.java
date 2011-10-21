/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.time.GameTime;
import game.world.World;

import org.newdawn.slick.Graphics;


public class DoubleState implements IRoundState {
  private final IRoundState fgState;
  private IRoundState       bgState;

  public DoubleState(IRoundState fgState) {
    this.fgState = fgState;
    this.bgState = null;
  }

  @Override
  public void update(GameTime time, World world) {
    bgState.update(time, world);
    fgState.update(time, world);
  }

  @Override
  public void render(Graphics g) {
    bgState.render(g);
    fgState.render(g);
  }

  @Override
  public boolean isFinished() {
    return fgState.isFinished();
  }

  public void start(IRoundState state) {
    this.bgState = state;
  }
}
