/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package states;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import loader.data.json.LevelData.TextStateData;
import loader.parser.ParserException;
import main.Launcher;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import components.triggers.actions.IAction;

import other.CacheTool;
import other.GameTime;
import basics.Rectangle;

public class TransitionState implements ICompState {
  private final float x, y;
  private final Image text;
  private final float duration;
  private float       tSinceStart;

  public TransitionState(final TextStateData data, final Rectangle rect)
    throws IOException, ParserException {
    text = CacheTool.getImage(Launcher.cache, data.text);
    x = (rect.getWidth() / 2.0f) - (text.getWidth() / 2.0f);
    y = (rect.getHeight() / 2.0f) - (text.getWidth() / 2.0f);
    duration = data.duration;
  }

  @Override
  public void start() {
    tSinceStart = 0.0f;
  }

  @Override
  public void render(final Graphics g) {
    g.drawImage(text, x, y);
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
