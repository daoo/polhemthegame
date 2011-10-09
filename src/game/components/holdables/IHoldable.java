/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.components.interfaces.IRenderComponent;

public interface IHoldable extends IRenderComponent {
  public void toggleOn();
  public void toggleOff();

  public boolean isInUse();

  public float getProgress();
}
