/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import java.util.Collection;
import java.util.Collections;

import math.time.GameTime;

import org.newdawn.slick.Graphics;

import components.triggers.actions.IAction;

public class DoubleState implements IState {
  private final IState fgState;
  private IState       bgState;

  public DoubleState(final IState fgState) {
    this.fgState = fgState;
    this.bgState = null;
  }

  @Override
  public boolean hasActions() {
    return false;
  }

  @Override
  public Collection<IAction> getActions() {
    return Collections.emptyList();
  }

  @Override
  public void clearActions() {
    // Do nothing
  }

  @Override
  public void update(final GameTime time) {
    bgState.update(time);
    fgState.update(time);
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

  public void start(final IState state) {
    this.bgState = state;
  }
}
