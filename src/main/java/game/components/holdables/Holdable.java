/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.components.RenderComponent;
import game.ui.hud.infobar.Progressing;

public interface Holdable extends RenderComponent, Progressing {
  void toggleOn();

  void toggleOff();
}
