package game.triggers.effects;

import ui.IUIElement;
import main.Locator;
import game.time.GameTime;
import game.triggers.IEffect;
import game.world.World;

public class RemoveUIElement implements IEffect {
  private final IUIElement element;

  public RemoveUIElement(IUIElement element) {
    this.element = element;
  }

  @Override
  public void execute(GameTime time, World world) {
    Locator.getUI().removeElement(element);
  }
}
