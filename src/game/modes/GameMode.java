/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.modes;

import game.Campaign;
import game.Level;
import game.entities.Players;
import game.time.GameTime;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CampaignData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Graphics;

import ui.hud.ShopUI;
import ui.hud.UI;

public class GameMode implements IMode {
  private final UI ui;

  private final Campaign campaign;
  private Level level;

  private final Players players;

  /**
   * Describes the entire area availible to the game (non-ui stuff).
   * Relative to the upper left corner of the game window.
   */
  private final Rectangle    arenaRect;

  private boolean            levelFinished;
  private float              elapsed;

  public GameMode(CampaignData data, int width, int height)
    throws IOException, ParserException, DataException {
    ui = new UI();
    Locator.registerUI(ui);

    levelFinished = false;
    elapsed       = 0;

    arenaRect = new Rectangle(0, ShopUI.HEIGHT, width, height - ShopUI.HEIGHT * 2);
    campaign  = new Campaign(data);

    players = new Players(1);

    nextLevel();
  }

  private void nextLevel()
      throws DataException, ParserException, IOException {

    if (campaign.hasMoreLevels()) {
      campaign.nextLevel();
      level = new Level(campaign.getCurrentLevel(), players,
                            arenaRect.getWidth(), arenaRect.getHeight());
    } else {
      // TODO: Credits
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
    if (!levelFinished) {
      elapsed += dt;
      GameTime time = new GameTime(dt, elapsed);

      if (levelFinished) {
        try {
          nextLevel();
        } catch (DataException ex) {
          ex.printStackTrace();
          System.exit(1);
        } catch (ParserException ex) {
          ex.printStackTrace();
          System.exit(1);
        } catch (IOException ex) {
          ex.printStackTrace();
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
}
