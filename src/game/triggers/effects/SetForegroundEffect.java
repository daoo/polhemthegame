package game.triggers.effects;

import game.pods.GameTime;
import game.triggers.IEffect;
import game.world.World;

import org.newdawn.slick.Image;

import ui.IUI;

public class SetForegroundEffect implements IEffect {
  private final IUI ui;
  private final Image image;

  public SetForegroundEffect(IUI ui, Image image) {
    assert ui != null;

    this.ui = ui;
    this.image = image;
  }

  @Override
  public void execute(GameTime time, World world) {
    ui.setForegroundImage(image);
  }
}
