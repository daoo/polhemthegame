/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.CacheTool;
import game.components.triggers.actions.IAction;
import game.entities.Boss;
import game.factories.Factory;

import java.io.IOException;
import java.util.Collection;

import loader.data.DataException;
import loader.data.json.LevelData.BossStateData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class BossState implements IState {
  private final Boss boss;
  private boolean    finished;

  public BossState(final Rectangle rect, final BossStateData sd)
    throws ParserException, DataException, IOException {
    boss = Factory.MakeBoss(0, 0, CacheTool.getBoss(Locator.getCache(), sd.boss));

    finished = true;
  }

  @Override
  public void render(final Graphics g) {
    // TODO
  }

  @Override
  public void update(final GameTime time) {
    if (!boss.isAlive()) {
      // TODO: When animiation has finished, we're done here
    }
  }

  @Override
  public boolean isFinished() {
    return finished;
  }

  @Override
  public boolean hasActions() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Collection<IAction> getActions() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void clearActions() {
    // TODO Auto-generated method stub
  }
}
