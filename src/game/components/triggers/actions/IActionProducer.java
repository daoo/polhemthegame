/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.triggers.actions;

import java.util.Collection;

public interface IActionProducer {
  public boolean hasActions();
  public Collection<IAction> getActions();
  public void clearActions();
}
