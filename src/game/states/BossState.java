/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.CacheTool;
import game.entities.interfaces.IEntity;
import game.factories.Factory;
import game.world.World;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.LevelData.BossStateData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class BossState implements IRoundState {
  private final boolean finished;

  public BossState(final Rectangle rect, final BossStateData sd)
    throws ParserException, DataException, IOException {
    finished = true;
  }

  @Override
  public void render(final Graphics g) {
    // TODO
  }

  @Override
  public void update(final GameTime time, final World world) {
    // TODO
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}
