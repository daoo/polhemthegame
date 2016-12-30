/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.components.IRenderComponent;
import game.ui.hud.infobar.IProgress;

public interface IHoldable extends IRenderComponent, IProgress {
  void toggleOn();

  void toggleOff();
}
