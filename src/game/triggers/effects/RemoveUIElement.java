/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import ui.IDynamicUIElement;
import main.Locator;
import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;

public class RemoveUIElement implements IEffect {
  private final IDynamicUIElement element;

  public RemoveUIElement(IDynamicUIElement element) {
    this.element = element;
  }

  @Override
  public void execute(GameTime time, World world) {
    Locator.getUI().removeElement(element);
  }
}
