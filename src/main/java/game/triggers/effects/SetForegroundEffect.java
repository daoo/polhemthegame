/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import org.newdawn.slick.Image;

import game.course.World;
import game.triggers.Effect;
import game.types.GameTime;
import game.ui.hud.UI;


public class SetForegroundEffect implements Effect {
  private final UI mUi;
  private final Image mImage;

  public SetForegroundEffect(UI ui, Image image) {
    assert ui != null;

    mUi = ui;
    mImage = image;
  }

  @Override
  public void execute(GameTime time, World world) {
    mUi.setForegroundImage(mImage);
  }
}
