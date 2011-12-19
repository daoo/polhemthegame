/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.ComponentMessage;
import game.entities.IEntity;
import game.entities.InvisibleRectangle;
import game.entities.Players;
import game.events.impl.DamagePlayerEvent;
import game.events.impl.RemoveEvent;
import game.triggers.Trigger;
import game.triggers.condition.AllDeadCondition;
import game.triggers.condition.AnyPlayerDeadCondition;
import game.triggers.condition.TimerCondition;
import game.triggers.effects.DelayedActivateTriggerEffect;
import game.triggers.effects.LevelCompleteEffect;
import game.triggers.effects.SpawnWithSend;
import game.world.World;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import states.GameState;

import loader.data.DataException;
import loader.data.json.CreepsData;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.LevelData;
import loader.data.json.LevelData.CreepSpawnData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

public class WorldFactory {
  /**
   * Adds two basic rectangles to the world. Big rect, the rectangle that kills
   * anything that goes to far away from the visible area (to save memory). The
   * creep killer rect, kills creeps and deals damage to the players when the
   * creeps have reached their goal.
   * @param world the world to add the rectangles
   * @param rectWorld the rectangle to use as world boundaries
   */
  private static void addRectangles(World world, Rectangle rectWorld) {
    /**
     * The layout of rectangles:
     * |---------------------------------|
     * | rectBig                         |
     * |                                 |
     * |                                 |
     * |-----------|-----------|         |
     * | rectCreep | rectWorld |         |
     * |-----------|-----------|         |
     * |                                 |
     * |                                 |
     * |                                 |
     * |---------------------------------|
     */

    InvisibleRectangle rectBig = new InvisibleRectangle(
      -1 * rectWorld.getWidth(), -1 * rectWorld.getHeight(),
       3 * rectWorld.getWidth(),  3 * rectWorld.getHeight()
    );

    InvisibleRectangle rectCreepKiller = new InvisibleRectangle(
      -rectWorld.getWidth(), 0,
       rectWorld.getWidth(), rectWorld.getHeight()
    );

    rectBig.onNotContainsEvent.add(new RemoveEvent());
    rectCreepKiller.onContainsEvent.add(new RemoveEvent());
    rectCreepKiller.onContainsEvent.add(new DamagePlayerEvent());

    world.addLast(rectBig);
    world.addLast(rectCreepKiller);
  }

  /**
   * Adds creep spawning triggers to the world.
   * Also returns a list of the creeps that will be spawned.
   * @param world the world to add the triggers to
   * @param rect the bounding rectangle of the world
   * @param spawnsData the data used for creating the creeps
   * @return a list of the creeps that will be spawned
   */
  private static List<IEntity> addCreepTriggers(World world,
      Rectangle rect, List<CreepSpawnData> spawnsData)
      throws DataException, ParserException, IOException {
    assert spawnsData != null;

    CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());
    LinkedList<IEntity> result = new LinkedList<>();

    for (CreepSpawnData spawnData : spawnsData) {
      Trigger t = new Trigger(false);
      t.addCondition(new TimerCondition(0, spawnData.spawnTime));

      CreepData creepData = creepsData.getCreep(spawnData.creep);
      IEntity creep = EntityFactory.makeCreep(
        rect.getX2() + creepData.hitbox.width,
        Locator.getRandom().nextFloat(rect.getY1(), rect.getY2() - creepData.hitbox.height),
        (float) -Math.PI,
        creepData
      );
      result.add(creep);

      t.addEffect(
        new SpawnWithSend(creep, ComponentMessage.START_ANIMATION, null));

      world.addTrigger(t);
    }

    return result;
  }

  private static void addPlayers(World world, Rectangle rect, Players players) {
    players.reposition(rect);
    for (IEntity p : players) {
      world.addLast(p);
    }
  }

  private static void addLevelTriggers(World world, GameState gameMode,
      List<IEntity> creeps, Players players) {
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
    gameOver.addCondition(new AnyPlayerDeadCondition(players));
    // TODO: gameOver.addEffect(new MainMenuEffect());
    // TODO: gameOver.addEffect(new GameOverEffect());
    world.addTrigger(gameOver);
  }

  public static World makeLevel(GameState gameMode, Players players,
      Rectangle rect, LevelData level)
      throws DataException, ParserException, IOException {

    World world = new World();

    WorldFactory.addRectangles(world, rect);
    WorldFactory.addPlayers(world, rect, players);

    List<IEntity> creeps =
      WorldFactory.addCreepTriggers(world, rect, level.creeps);

    WorldFactory.addLevelTriggers(world, gameMode, creeps, players);

    return world;
  }
}
