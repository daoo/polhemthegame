/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.holdables;

import components.interfaces.ICompUpRend;

public interface IHoldable extends ICompUpRend {
  public void useOnce();

  public void toggleUse();

  public void startUse();

  public void stopUse();

  public boolean isInUse();

  public float getProgress();
}
