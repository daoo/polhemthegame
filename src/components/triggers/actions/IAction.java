/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.triggers.actions;

import com.daoo.game.World;
import com.daoo.math.time.GameTime;

public interface IAction {
  public void execute(final GameTime time, final World world);
}
