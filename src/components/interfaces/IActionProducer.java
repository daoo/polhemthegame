/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.interfaces;

import java.util.Collection;

import components.triggers.actions.IAction;

public interface IActionProducer {
  public boolean hasActions();
  public Collection<IAction> getActions();
  public void clearActions();
}
