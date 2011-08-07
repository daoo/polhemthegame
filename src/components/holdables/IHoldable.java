/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.holdables;

import components.interfaces.ICompUpRend;

public interface IHoldable extends ICompUpRend {
  public void toggleOn();
  public void toggleOff();

  public boolean isInUse();

  public float getProgress();
}
