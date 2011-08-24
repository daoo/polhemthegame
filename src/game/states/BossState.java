/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import java.io.IOException;
import java.util.Collection;

import loader.data.DataException;
import loader.data.json.LevelData.BossStateData;
import loader.parser.ParserException;
import main.CacheTool;
import main.Launcher;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;


import components.triggers.actions.IAction;

import entities.Boss;
import factories.BossFactory;

public class BossState implements ICompState {
  private final Boss boss;
  private boolean    finished;

  public BossState(final Rectangle rect, final BossStateData sd)
    throws ParserException, DataException, IOException {
    boss = BossFactory.Make(0, 0, CacheTool.getBoss(Launcher.cache, sd.boss));

    finished = true;
  }

  @Override
  public void render(final Graphics g) {
  }

  @Override
  public void update(final GameTime time) {
    if (!boss.isAlive()) {
      // TODO: When animiation has finished, we're done here 
    }
  }

  @Override
  public void start() {
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
