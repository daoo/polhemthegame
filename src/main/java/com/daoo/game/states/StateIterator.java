/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.game.states;

import java.util.Iterator;

public class StateIterator implements Iterator<IState> {
  private final Iterator<IState> internal;
  private IState current;
  private boolean finished;

  public StateIterator(Iterable<IState> iterable) {
    internal = iterable.iterator();
    
    current = null;
    finished = false;
  }

  @Override
  public boolean hasNext() {
    return !finished && internal.hasNext();
  }

  @Override
  public IState next() {
    if (!finished)
      current = internal.next();
       
    return current;
  }

  @Override
  public void remove() {
    internal.remove();
  }
  
  public IState getCurrent() {
    return current;
  }
  
  /**
   * Stops the iterator from iterating and points it at state.
   */
  public void endNowWith(final IState state) {
    current = state;
    finished = true;
  }

  public boolean isFinished() {
    return finished;
  }
}
