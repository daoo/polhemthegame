/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.components.holdables;

import com.daoo.components.interfaces.ICompUpRend;

public interface IHoldable extends ICompUpRend {
  public void toggleOn();
  public void toggleOff();

  public boolean isInUse();

  public float getProgress();
}
