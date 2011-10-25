/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.modes;

import game.GameCampaign;
import game.GameLevel;
import game.entities.Players;
import game.time.GameTime;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CampaignData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Graphics;

import ui.hud.UI;

public class Game implements IMode {
  private final UI ui;

  private GameLevel          level;
  private final GameCampaign campaign;

  private final Players      players;

  /**
   * Describes the entire area availible to the game (non-ui stuff).
   * Relative to the upper left corner of the game window.
   */
  private final Rectangle    arenaRect;

  private boolean            finished;
  private float              elapsed;

  public Game(CampaignData data, int x, int y, int w, int h)
    throws IOException, ParserException, DataException {
    ui = new UI();
    Locator.registerUI(ui);

    finished = false;
    elapsed = 0;

    arenaRect = new Rectangle(x, y, w, h);
    campaign = new GameCampaign(data);

    players = new Players(1);

    nextLevel();
  }

  private void nextLevel()
    throws DataException, ParserException, IOException {

    if (players.isAlive() && campaign.hasMoreLevels()) {
      campaign.nextLevel();
      level = new GameLevel(campaign.getCurrentLevel(), players,
                            arenaRect.getWidth(), arenaRect.getHeight());
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
  public void update(float dt) {
    if (!finished) {
      elapsed += dt;
      GameTime time = new GameTime(dt, elapsed);

      if (level.isFinished()) {
        try {
          nextLevel();
        } catch (DataException e) {
          e.printStackTrace();
          System.exit(1);
        } catch (ParserException e) {
          e.printStackTrace();
          System.exit(1);
        } catch (IOException e) {
          e.printStackTrace();
          System.exit(1);
        }
      } else {
        level.update(time);
        ui.update();
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(arenaRect.getX1(), arenaRect.getY1());

    level.render(g);
    ui.render(g);

    g.popTransform();
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}
