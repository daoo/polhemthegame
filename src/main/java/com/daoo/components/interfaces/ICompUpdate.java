/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.interfaces;

import com.daoo.math.time.GameTime;

public interface ICompUpdate extends IComp {
  public void update(final GameTime time);
}
