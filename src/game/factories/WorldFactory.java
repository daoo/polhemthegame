/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.Message;
import game.entities.Entity;
import game.entities.IEntity;
import game.entities.InvisibleRectangle;
import game.events.impl.DamageEntitiesEvent;
import game.events.impl.RemoveEvent;
import game.triggers.Trigger;
import game.triggers.condition.AbsoluteTimerCondition;
import game.triggers.condition.AllInactiveCondition;
import game.triggers.condition.AlwaysTrueCondition;
import game.triggers.effects.AddTriggersEffect;
import game.triggers.effects.ExecuteWithDelayEffect;
import game.triggers.effects.LevelCompleteEffect;
import game.triggers.effects.MainMenuEffect;
import game.triggers.effects.SetForegroundEffect;
import game.triggers.effects.SpawnWithSend;
import game.world.World;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import loader.ICache;
import loader.data.DataException;
import loader.data.json.CreepsData;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.LevelData;
import loader.data.json.LevelData.CreepSpawnData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Image;

import states.GameState;
import states.StateManager;

public class WorldFactory {
  private static final int PLAYER_DAMAGE = 10;
  private static final float TRIGGER_DELAY = 1.0f;

  private static final String GAME_OVER_IMAGE = "textures/text/gameover.png";

  private final GameState gameMode;
  private final StateManager stateManager;

  private final Rectangle rect;
  private final List<Entity> players;

  private EntityFactory entityFactory;

  private World world;

  private Trigger levelStartTrigger, creepsSpawnTrigger,
                  creepsDeadTrigger, playersDeadTrigger;

  public WorldFactory(GameState gameMode, StateManager stateManager,
                      EntityFactory entityFactory, Rectangle rect,
                      List<Entity> players) {
    this.gameMode      = gameMode;
    this.stateManager  = stateManager;
    this.entityFactory = entityFactory;
    this.rect          = rect;
    this.players       = players;
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
    rectCreepKiller.onContainsEvent.add(
      new DamageEntitiesEvent(players, PLAYER_DAMAGE));

    world.addLast(rectBig);
    world.addLast(rectCreepKiller);
  }

  private void setupCreeps(List<CreepSpawnData> spawnsData)
      throws DataException, ParserException, IOException {
    assert spawnsData != null;

    CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());

    ArrayList<Entity> creeps = new ArrayList<>(spawnsData.size());

    for (CreepSpawnData spawnData : spawnsData) {
      // Make creep
      CreepData creepData = creepsData.getCreep(spawnData.creep);
      float x = rect.getX2() + creepData.hitbox.width;
      float y = Locator.getRandom().nextFloat(
        rect.getY1(),
        rect.getY2() - creepData.hitbox.height
      );

      Entity creep = entityFactory.makeCreep(x, y, (float) -Math.PI, creepData);
      creeps.add(creep);

      // Add trigger
      creepsSpawnTrigger.addEffect(
        new ExecuteWithDelayEffect(spawnData.spawnTime,
          new SpawnWithSend(creep, Message.START_ANIMATION, null)));
    }

    creepsDeadTrigger.addCondition(new AllInactiveCondition(creeps));
  }

  public World makeLevel(LevelData level)
      throws DataException, ParserException, IOException {

    ICache cache           = Locator.getCache();
    Image imgLevelStart    = CacheTool.getImage(cache, level.loading);
    Image imgLevelComplete = CacheTool.getImage(cache, level.completed);
    Image imgGameOver      = CacheTool.getImage(cache, GAME_OVER_IMAGE);

    world = new World();

    addRectangles();

    levelStartTrigger  = new Trigger(false);
    creepsSpawnTrigger = new Trigger(false);
    creepsDeadTrigger  = new Trigger(false);
    playersDeadTrigger = new Trigger(false);

    levelStartTrigger.addCondition(new AlwaysTrueCondition());
    creepsSpawnTrigger.addCondition(new AlwaysTrueCondition());
    playersDeadTrigger.addCondition(new AllInactiveCondition(players));

    levelStartTrigger.addAllEffects(Arrays.asList(
      new SetForegroundEffect(Locator.getUI(), imgLevelStart),
      new ExecuteWithDelayEffect(
        TRIGGER_DELAY, new AddTriggersEffect(creepsSpawnTrigger))
    ));

    creepsSpawnTrigger.addAllEffects(Arrays.asList(
      new SetForegroundEffect(Locator.getUI(), null),
      new AddTriggersEffect(creepsDeadTrigger)
    ));

    creepsDeadTrigger.addAllEffects(Arrays.asList(
      new SetForegroundEffect(Locator.getUI(), imgLevelComplete),
      new ExecuteWithDelayEffect(
        TRIGGER_DELAY, new LevelCompleteEffect(gameMode))
    ));

    playersDeadTrigger.addAllEffects(Arrays.asList(
      new SetForegroundEffect(Locator.getUI(), imgGameOver),
      new ExecuteWithDelayEffect(
        TRIGGER_DELAY,  new MainMenuEffect(stateManager))
    ));

    world.addTrigger(levelStartTrigger);
    world.addTrigger(playersDeadTrigger);

    for (IEntity p : players) {
      world.addLast(p);
    }

    setupCreeps(level.creeps);

    return world;
  }
}
