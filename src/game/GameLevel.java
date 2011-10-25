/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game;

import game.entities.Players;
import game.factories.WorldFactory;
import game.time.GameTime;
import game.triggers.Trigger;
import game.triggers.condition.AnyPlayerDeadCondition;
import game.world.World;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.LevelData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class GameLevel {
  /**
   * The world, rendering is happening relative to what's actually availible.
   * I.e. all constraints, HUD stuff have been accounted for. Adding something at
   * (0, 0) would make it appear in the top left, just below the HUD.
   */
  private final World   world;
  private final Image   background;

  /**
   * The area availible for the current level.
   * I.e. with constraints for the active level. Relative to the visible background.
   */
  private final Rectangle rect;

  /**
   * The area actually used.
   * Has the same size as rect but top left will always be (0, 0).
   */
  private final Rectangle availible;

  public GameLevel(LevelData level, Players players, float width, float height)
      throws DataException, IOException, ParserException {
    float left   = level.constraints[0];
    float top    = level.constraints[1];
    float bottom = level.constraints[2];
    float right  = level.constraints[3];

    rect      = new Rectangle(left , top , width - left - right , height - top - bottom);
    availible = new Rectangle(0    , 0   , rect.getWidth()      , rect.getHeight());

    background = CacheTool.getImage(Locator.getCache(), level.background);

    world = WorldFactory.makeWorld(availible, players);

    if (level.creeps != null) {
      WorldFactory.makeCreepTriggers(level.creeps, availible, world);
    }

    Trigger gameOver = new Trigger(false);
    gameOver.addCondition(new AnyPlayerDeadCondition());
    // TODO: gameOver.addEffect(new GameOverEffect);
  }

  public void render(Graphics g) {
    g.drawImage(background, 0, 0);

    g.pushTransform();
    g.translate(rect.getX1(), rect.getY1());

    world.render(g);

    g.popTransform();
  }

  public void update(GameTime time) {
    world.update(time);
  }

  public boolean isFinished() {
    return false; // TODO
  }
}
