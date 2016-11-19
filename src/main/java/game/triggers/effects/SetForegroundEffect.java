/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.course.World;
import game.triggers.IEffect;
import game.types.GameTime;
import game.ui.IUI;

import org.newdawn.slick.Image;


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
