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
import game.modes.GameMode;
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

import loader.data.DataException;
import loader.data.json.CreepsData;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.LevelData;
import loader.data.json.LevelData.CreepSpawnData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

public class WorldFactory {
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
    rectBig.onNotContainsEvent.add(new RemoveEvent());
    world.addLast(rectBig);

    InvisibleRectangle rectCreepKiller = new InvisibleRectangle(
      -rectWorld.getWidth(), 0,
       rectWorld.getWidth(), rectWorld.getHeight()
    );

    rectCreepKiller.onContainsEvent.add(new RemoveEvent());
    rectCreepKiller.onContainsEvent.add(new DamagePlayerEvent());
    world.addLast(rectCreepKiller);
  }

  private static List<IEntity> makeCreepTriggers(World world, Rectangle rectWorld,
      List<CreepSpawnData> spawnsData)
      throws DataException, ParserException, IOException {
    CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());
    LinkedList<IEntity> result = new LinkedList<>();

    for (CreepSpawnData spawnData : spawnsData) {
      Trigger t = new Trigger(false);
      t.addCondition(new TimerCondition(0, spawnData.spawnTime));

      CreepData creepData = creepsData.getCreep(spawnData.creep);
      IEntity creep = EntityFactory.makeCreep(
        getCreepX(rectWorld, creepData.hitbox.width),
        getCreepY(rectWorld, creepData.hitbox.height),
        (float) -Math.PI,
        creepData
      );
      result.add(creep);

      t.addEffect(
        new SpawnWithSend(
          creep, ComponentMessage.START_ANIMATION, null
        )
      );

      world.addTrigger(t);
    }

    return result;
  }

  private static float getCreepX(Rectangle rect, int width) {
    return rect.getX2() + width;
  }

  private static float getCreepY(Rectangle rect, int height) {
    return Locator.getRandom().nextFloat(rect.getY1(), rect.getY2() - height);
  }

  public static World makeLevel(GameMode gameMode, Players players,
      Rectangle rect, LevelData level)
      throws DataException, ParserException, IOException {

    World world = new World();

    // Add some basic rects
    WorldFactory.addRectangles(world, rect);

    // Add players
    players.reposition(rect);
    for (IEntity p : players) {
      world.addLast(p);
    }

    // Setup creep triggers
    List<IEntity> creeps =
      WorldFactory.makeCreepTriggers(world, rect, level.creeps);

    // Setup level triggers
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

    return world;
  }
}
