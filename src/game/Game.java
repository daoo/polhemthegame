/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package game;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CampaignData;
import loader.parser.ParserException;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import other.GameTime;
import basics.Rectangle;
import entities.Players;

public class Game implements IMode {
  private GameLevel          level;
  private final GameCampaign campaign;

  /**
   * Describes the entire area availible to the game (non-ui stuff). Relative to
   * the upper left corner of the game window.
   */
  private final Rectangle    arenaRect;
  private final Players      players;
  private boolean            finished;
  private float              elapsed;

  public Game(final CampaignData data, final int x, final int y, final int w, final int h)
    throws IOException, ParserException, SlickException, DataException {
    finished = false;
    elapsed = 0;

    arenaRect = new Rectangle(x, y, w, h);
    campaign = new GameCampaign(data);

    players = new Players(1);

    nextLevel();
  }

  private void nextLevel()
    throws DataException, ParserException, IOException, SlickException {
    if (campaign.hasMoreLevels()) {
      campaign.nextLevel();
      level = new GameLevel(campaign.getCurrentLevel(), players,
                            arenaRect.getWidth(), arenaRect.getHeight());

      level.start();

      // If there are no states, skip to the next level
      if (level.isFinished()) {
        nextLevel();
      }
    }
  }

  /**
   * The update iteration of the game loop. Updates entities, switches levels,
   * etc.
   *
   * @param dt
   *          Approximate length of the current frame in seconds.
   * @throws DataException
   * @throws ParserException
   * @throws IOException
   * @throws SlickException
   */
  @Override
  public void update(final float dt) {
    elapsed += dt;
    final GameTime time = new GameTime(dt, elapsed);

    if (level.isFinished()) {
      if (campaign.hasMoreLevels()) {
        try {
          nextLevel();
        } catch (final DataException e) {
          System.exit(0);
          e.printStackTrace();
        } catch (final ParserException e) {
          System.exit(0);
          e.printStackTrace();
        } catch (final IOException e) {
          System.exit(0);
          e.printStackTrace();
        } catch (final SlickException e) {
          System.exit(0);
          e.printStackTrace();
        }
      }
      else {
        finished = true;
      }
    } else {
      level.update(time);
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
