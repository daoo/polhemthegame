/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game;

import game.entities.Players;
import game.factories.WorldFactory;
import game.time.GameTime;
import game.triggers.Trigger;
import game.triggers.condition.AllCreepsDeadCondition;
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
  private final World world;
  private final Image background;

  /**
   * The area availible for the current level.
   * I.e. with constraints for the active level, relative to the visible
   * background.
   */
  private final Rectangle rect;

  public GameLevel(LevelData level, Players players, float width, float height)
      throws DataException, IOException, ParserException {
    background = CacheTool.getImage(Locator.getCache(), level.background);

    float left   = level.constraints[0];
    float top    = level.constraints[1];
    float bottom = level.constraints[2];
    float right  = level.constraints[3];

    rect  = new Rectangle(left, top, width - left - right, height - top - bottom);
    world = WorldFactory.makeWorld(rect, players);

    // Setup creep triggers
    if (level.creeps != null) {
      WorldFactory.makeCreepTriggers(level.creeps, rect, world);
    }

    // Setup eventual boss triggers
    Trigger levelComplete = new Trigger(false);
    // TODO: levelComplete.addEffect(new LevelCompleteEffect());
    world.addTrigger(levelComplete);
    if (level.boss != null) {
      Trigger spawnBoss = new Trigger(false);
      spawnBoss.addCondition(new AllCreepsDeadCondition());
      // TODO: spawnBoss.addEffect(new SpawnBossEffect());
      world.addTrigger(spawnBoss);

      // TODO: levelComplete.addCondition(new EntityDeadCondition(boss));
    } else {
      levelComplete.addCondition(new AllCreepsDeadCondition());
    }

    Trigger gameOver = new Trigger(false);
    gameOver.addCondition(new AnyPlayerDeadCondition());
    // TODO: gameOver.addEffect(new GameOverEffect());
    world.addTrigger(gameOver);
  }

  public void render(Graphics g) {
    g.drawImage(background, 0, 0);

    world.render(g);
  }

  public void update(GameTime time) {
    world.update(time);
  }
}
