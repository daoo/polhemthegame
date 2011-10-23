package game.actions;

import ui.IUIElement;
import main.Locator;
import game.time.GameTime;
import game.world.World;

public class RemoveUIElement implements IAction {
  private final IUIElement element;

  public RemoveUIElement(IUIElement element) {
    this.element = element;
  }

  @Override
  public void execute(GameTime time, World world) {
    Locator.getUI().removeElement(element);
  }
}
