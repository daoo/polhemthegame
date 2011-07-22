package components.actions;

import java.util.Collection;


public interface IActions {
  public boolean hasActions();
  public Collection<IAction> getActions();
  public void clearActions();
}
