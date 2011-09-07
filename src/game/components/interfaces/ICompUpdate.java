/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.interfaces;

import math.time.GameTime;

public interface ICompUpdate extends IComp {
  public void update(final GameTime time);
}
