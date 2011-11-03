/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.components.interfaces.IRenderComponent;
import ui.hud.infobar.IProgress;

public interface IHoldable extends IRenderComponent, IProgress {
  public void toggleOn();
  public void toggleOff();

  public boolean isInUse();
}
