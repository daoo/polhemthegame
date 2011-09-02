/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.game.states;

import java.util.Collection;
import java.util.Collections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.daoo.components.triggers.actions.IAction;
import com.daoo.math.Rectangle;
import com.daoo.math.time.GameTime;

public class TransitionState implements IState {
  private final float x, y;
  private final Image img;
  private final float duration;
  private float       tSinceStart;

  public TransitionState(final Image img, final float duration, final Rectangle rect) {
    this.img = img;

    x = (rect.getWidth() / 2.0f) - (img.getWidth() / 2.0f);
    y = (rect.getHeight() / 2.0f) - (img.getWidth() / 2.0f);

    this.duration = duration;

    tSinceStart = 0.0f;
  }

  @Override
  public void render(final Graphics g) {
    g.drawImage(img, x, y);
  }

  @Override
  public void update(final GameTime time) {
    tSinceStart += time.getFrameLength();
  }

  @Override
  public boolean isFinished() {
    return tSinceStart >= duration;
  }

  @Override
  public boolean hasActions() {
    return false;
  }

  @Override
  public Collection<IAction> getActions() {
    return Collections.emptyList();
  }

  @Override
  public void clearActions() {
    // No actions to clear
  }
}
