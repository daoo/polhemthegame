/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.Message;
import game.entities.Entity;
import game.entities.IEntity;
import game.entities.InvisibleRectangle;
import game.events.impl.DamagePlayerEvent;
import game.events.impl.RemoveEvent;
import game.triggers.Trigger;
import game.triggers.condition.AllDeadCondition;
import game.triggers.condition.AlwaysTrueCondition;
import game.triggers.condition.AnyDeadCondition;
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
import states.GameState;

public class WorldFactory {
  private final Rectangle rect;
  private final List<Entity> players;

  private EntityFactory entityFactory;

  private World world;

  public WorldFactory(EntityFactory entityFactory, Rectangle rect,
      List<Entity> players) {
    this.entityFactory = entityFactory;
    this.rect = rect;
    this.players = players;
  }

  /**
   * Adds two basic rectangles to the world. rectBig, the rectangle that kills
   * anything that goes to far away from the visible area (to save memory).
   * rectCreep kills creeps and deals damage to the players when the creeps have
   * reached their goal.
   */
  private void addRectangles() {
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
      -1 * rect.getWidth(), -1 * rect.getHeight(),
       3 * rect.getWidth(),  3 * rect.getHeight()
    );

    InvisibleRectangle rectCreepKiller = new InvisibleRectangle(
      -rect.getWidth(), 0,
       rect.getWidth(), rect.getHeight()
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
  private List<Entity> addCreepTriggers(List<CreepSpawnData> spawnsData)
      throws DataException, ParserException, IOException {
    assert spawnsData != null;

    CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());
    LinkedList<Entity> result = new LinkedList<>();

    for (CreepSpawnData spawnData : spawnsData) {
      Trigger t = new Trigger(false);
      t.addCondition(new TimerCondition(0, spawnData.spawnTime));

      CreepData creepData = creepsData.getCreep(spawnData.creep);
      Entity creep = entityFactory.makeCreep(
        rect.getX2() + creepData.hitbox.width,
        Locator.getRandom().nextFloat(rect.getY1(), rect.getY2() - creepData.hitbox.height),
        (float) -Math.PI,
        creepData
      );
      result.add(creep);

      t.addEffect(
        new SpawnWithSend(creep, Message.START_ANIMATION, null));

      world.addTrigger(t);
    }

    return result;
  }

  private void addLevelTriggers(GameState gameMode, List<Entity> creeps) {
    Trigger levelComplete = new Trigger(false);
    levelComplete.addCondition(new AlwaysTrueCondition());
    levelComplete.addEffect(new LevelCompleteEffect(gameMode));

    Trigger levelCompleteDelay = new Trigger(false);
    levelCompleteDelay.addEffect(new DelayedActivateTriggerEffect(5.0f, levelComplete));
    levelCompleteDelay.addCondition(new AllDeadCondition(creeps));
    world.addTrigger(levelCompleteDelay);

    Trigger gameOver = new Trigger(false);
    gameOver.addCondition(new AnyDeadCondition(players));
    // TODO: gameOver.addEffect(new MainMenuEffect());
    // TODO: gameOver.addEffect(new GameOverEffect());
    world.addTrigger(gameOver);
  }

  public World makeLevel(GameState gameMode, LevelData level)
      throws DataException, ParserException, IOException {

    world = new World();

    addRectangles();

    for (IEntity p : players) {
      world.addLast(p);
    }

    List<Entity> creeps = addCreepTriggers(level.creeps);

    addLevelTriggers(gameMode, creeps);

    return world;
  }
}
