/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package states;

import java.io.IOException;

import loader.data.json.LevelData.TextStateData;
import loader.parser.ParserException;
import main.Launcher;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import other.CacheTool;
import other.GameTime;
import basics.Rectangle;
import basics.Timer;

public class TransitionState implements ICompState {
  private final float x, y;
  private final Image text;
  private final Timer timer;

  public TransitionState(final TextStateData data, final Rectangle rect)
    throws IOException, ParserException {
    text = CacheTool.getImage(Launcher.cache, data.text);
    x = (rect.getWidth() / 2.0f) - (text.getWidth() / 2.0f);
    y = (rect.getHeight() / 2.0f) - (text.getWidth() / 2.0f);
    timer = new Timer(data.duration);
  }

  @Override
  public void start() {
    timer.start(0);
  }

  @Override
  public void render(final Graphics g) {
    g.drawImage(text, x, y);
  }

  @Override
  public void update(final GameTime time) {
    timer.update(time);
  }

  @Override
  public boolean isFinished() {
    return timer.isFinished();
  }

}
