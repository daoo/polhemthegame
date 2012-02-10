/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.graphics;

import game.components.interfaces.IRenderComponent;
import game.types.GameTime;
import game.types.Message;

import org.newdawn.slick.Graphics;

public class DummyAnimation implements IRenderComponent {
  public DummyAnimation() {
    // Do nothing
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public void render(Graphics g) {
    // Do nothing
  }

  @Override
  public void update(GameTime time) {
    // Do nothing
  }
}
