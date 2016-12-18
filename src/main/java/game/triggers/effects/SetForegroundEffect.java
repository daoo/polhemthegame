/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.course.World;
import game.triggers.IEffect;
import game.types.GameTime;
import game.ui.hud.UI;

import org.newdawn.slick.Image;


public class SetForegroundEffect implements IEffect {
  private final UI ui;
  private final Image image;

  public SetForegroundEffect(UI ui, Image image) {
    assert ui != null;

    this.ui = ui;
    this.image = image;
  }

  @Override
  public void execute(GameTime time, World world) {
    ui.setForegroundImage(image);
  }
}
