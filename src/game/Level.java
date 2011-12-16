/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game;

import game.entities.IEntity;
import game.entities.Players;
import game.factories.WorldFactory;
import game.modes.GameMode;
import game.time.GameTime;
import game.triggers.Trigger;
import game.triggers.condition.AllDeadCondition;
import game.triggers.condition.AnyPlayerDeadCondition;
import game.triggers.effects.DelayedActivateTriggerEffect;
import game.triggers.effects.LevelCompleteEffect;
import game.world.World;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.LevelData;
import loader.parser.ParserException;
import math.Rectangle;

import org.newdawn.slick.Graphics;

public class Level {
  public enum LEVEL_STATE {
    RUNNING, FINISHED, GAME_OVER
  }

  /**
   * The world, rendering is happening relative to what's actually availible.
   * I.e. all constraints, HUD stuff have been accounted for. Adding something at
   * (0, 0) would make it appear in the top left, just below the HUD.
   */
  private final World world;

  public Level(GameMode gameMode, LevelData level, Players players, Rectangle rect)
      throws DataException, IOException, ParserException {
    assert (level != null);
    assert (level.creeps != null);

    world = WorldFactory.makeWorld(rect, players);

    // Setup creep triggers
    Iterable<IEntity> creeps =
      WorldFactory.makeCreepTriggers(level.creeps, rect, world);

    Trigger levelComplete = new Trigger(false);
    levelComplete.addEffect(new LevelCompleteEffect(gameMode));

    Trigger levelCompleteDelay = new Trigger(false);
    levelCompleteDelay.addEffect(new DelayedActivateTriggerEffect(5.0f, levelComplete));
    world.addTrigger(levelCompleteDelay);

    /*if (level.boss != null) {
      // Boss trigger
      Trigger spawnBoss = new Trigger(false);
      spawnBoss.addCondition(new AllDeadCondition(creeps));
      // TODO: spawnBoss.addEffect(new SpawnBossEffect());
      world.addTrigger(spawnBoss);

      // TODO: levelComplete.addCondition(new EntityDeadCondition(boss));
    } else {*/
      levelCompleteDelay.addCondition(new AllDeadCondition(creeps));
    //}

    Trigger gameOver = new Trigger(false);
    gameOver.addCondition(new AnyPlayerDeadCondition());
    // TODO: gameOver.addEffect(new MainMenuEffect());
    // TODO: gameOver.addEffect(new GameOverEffect());
    world.addTrigger(gameOver);
  }

  public void render(Graphics g) {
    world.render(g);
  }

  public void update(GameTime time) {
    world.update(time);
  }
}
