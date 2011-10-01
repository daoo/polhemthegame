/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import java.util.Iterator;

public class StateIterator implements Iterator<IRoundState> {
  private final Iterator<IRoundState> internal;
  private IRoundState current;
  private boolean finished;

  public StateIterator(Iterable<IRoundState> iterable) {
    internal = iterable.iterator();

    current = null;
    finished = false;
  }

  @Override
  public boolean hasNext() {
    return !finished && internal.hasNext();
  }

  @Override
  public IRoundState next() {
    if (!finished)
      current = internal.next();

    return current;
  }

  @Override
  public void remove() {
    internal.remove();
  }

  public IRoundState getCurrent() {
    return current;
  }

  /**
   * Stops the iterator from iterating and points it at state.
   * @param state
   */
  public void endNowWith(final IRoundState state) {
    current = state;
    finished = true;
  }

  public boolean isFinished() {
    return finished;
  }
}
