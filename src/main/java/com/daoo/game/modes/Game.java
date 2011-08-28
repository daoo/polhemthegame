/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.game.modes;

import java.io.IOException;

import org.newdawn.slick.Graphics;

import com.daoo.entities.Players;
import com.daoo.game.GameCampaign;
import com.daoo.game.GameLevel;
import com.daoo.game.states.DoubleState;
import com.daoo.game.states.TransitionState;
import com.daoo.loader.data.DataException;
import com.daoo.loader.data.json.CampaignData;
import com.daoo.loader.parser.ParserException;
import com.daoo.math.Rectangle;
import com.daoo.math.time.GameTime;
import com.daoo.ptg.CacheTool;
import com.daoo.ptg.Locator;

public class Game implements IMode {
  private GameLevel          level;
  private final GameCampaign campaign;
  
  private final DoubleState  gameOverState;
  
  private final Players      players;

  /**
   * Describes the entire area availible to the game (non-ui stuff).
   * Relative to the upper left corner of the game window.
   */
  private final Rectangle    arenaRect;
  
  private boolean            finished;
  private float              elapsed;

  public Game(final CampaignData data, final int x, final int y, final int w, final int h)
    throws IOException, ParserException, DataException {
    finished = false;
    elapsed = 0;

    arenaRect = new Rectangle(x, y, w, h);
    campaign = new GameCampaign(data);

    players = new Players(1);
    
    gameOverState = new DoubleState(
      new TransitionState(
        CacheTool.getImage(Locator.getCache(), "textures/text/gameover.png"),
        2.5f, arenaRect));

    nextLevel();
  }

  private void nextLevel()
    throws DataException, ParserException, IOException {
    
    if (players.isAlive() && campaign.hasMoreLevels()) {
      campaign.nextLevel();
      level = new GameLevel(campaign.getCurrentLevel(), players,
                            arenaRect.getWidth(), arenaRect.getHeight(),
                            gameOverState);

      level.start();
    } else {
      finished = true;
    }
  }

  /**
   * The update iteration of the game loop.
   * Updates entities, switches levels, etc.
   *
   * @param dt Approximate length of the current frame in seconds.
   */
  @Override
  public void update(final float dt) {
    if (!finished) {
      elapsed += dt;
      final GameTime time = new GameTime(dt, elapsed);
  
      if (level.isFinished()) {
        try {
          nextLevel();
        } catch (final DataException e) {
          e.printStackTrace();
          System.exit(0);
        } catch (final ParserException e) {
          e.printStackTrace();
          System.exit(0);
        } catch (final IOException e) {
          e.printStackTrace();
          System.exit(0);
        }
      } else {
        level.update(time);
      }
    }
  }

  @Override
  public void render(final Graphics g) {
    g.pushTransform();
    g.translate(arenaRect.getX1(), arenaRect.getY1());

    level.render(g);

    g.popTransform();
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}
