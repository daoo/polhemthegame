package components.interfaces;

import java.util.Collection;

import components.triggers.actions.IAction;

public interface IActions {
  public boolean hasActions();
  public Collection<IAction> getActions();
  public void clearActions();
}
