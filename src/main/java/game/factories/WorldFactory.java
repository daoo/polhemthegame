/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import org.newdawn.slick.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.components.misc.KillCreep;
import game.course.LevelManager;
import game.course.World;
import game.entities.Entity;
import game.entities.Unit;
import game.misc.CacheTool;
import game.misc.Locator;
import game.states.StateManager;
import game.triggers.IEffect;
import game.triggers.Trigger;
import game.triggers.condition.AllInactiveCondition;
import game.triggers.condition.AlwaysTrueCondition;
import game.triggers.effects.AddTriggerEffect;
import game.triggers.effects.DamageEntitiesEffect;
import game.triggers.effects.ExecuteWithDelayEffect;
import game.triggers.effects.LevelCompleteEffect;
import game.triggers.effects.MainMenuEffect;
import game.triggers.effects.SetForegroundEffect;
import game.triggers.effects.spawn.SpawnBossEffect;
import game.triggers.effects.spawn.SpawnCreepEffect;
import loader.data.json.BossesData;
import loader.data.json.CreepsData;
import loader.data.json.LevelData;
import loader.data.json.types.CreepData;
import loader.data.json.types.CreepSpawnData;
import loader.parser.ParserException;
import math.Rectangle;


public class WorldFactory {
  private static final int PLAYER_DAMAGE = 10;
  private static final int TRIGGER_DELAY = 2000;

  private static final String GAME_OVER_IMAGE = "textures/text/gameover.png";

  private final BossesData bossesData;

  private final LevelManager gameMode;
  private final StateManager stateManager;

  private final Rectangle rect;
  private final List<Entity> players;

  private final EntityFactory entityFactory;

  private World world;

  private Trigger creepsSpawnTrigger;
  private Trigger creepsDeadTrigger;

  public WorldFactory(LevelManager gameMode, StateManager stateManager,
                      EntityFactory entityFactory, Rectangle rect,
                      List<Entity> players) throws ParserException, IOException {
    this.gameMode      = gameMode;
    this.stateManager  = stateManager;
    this.entityFactory = entityFactory;
    this.rect          = rect;
    this.players       = players;

    bossesData = CacheTool.getBosses(Locator.getCache());
  }

  private void setupCreeps(List<CreepSpawnData> spawnsData)
      throws ParserException, IOException {
    assert spawnsData != null;

    CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());

    ArrayList<Entity> creeps = new ArrayList<>(spawnsData.size());

    for (CreepSpawnData spawnData : spawnsData) {
      // Make creep
      CreepData creepData = creepsData.getCreep(spawnData.creep);
      float x = rect.getX2() + creepData.unit.hitbox.width;
      float y = Locator.getRandom().nextFloat(
        rect.getY1(),
        rect.getY2() - creepData.unit.hitbox.height
      );

      Unit creep = entityFactory.makeCreep(x, y, creepData);

      creep.entity.addLogicComponent(
        new KillCreep(creep.entity, (int) rect.getX1(),
          new DamageEntitiesEffect(players, PLAYER_DAMAGE)));

      // Add trigger
      creepsSpawnTrigger.addEffect(
        new ExecuteWithDelayEffect(
          spawnData.spawnTime, new SpawnCreepEffect(creep)));

      creeps.add(creep.entity);
    }

    creepsDeadTrigger.addCondition(new AllInactiveCondition(creeps));
  }

  public World makeLevel(LevelData level) throws ParserException, IOException {
    Image imgLevelStart    = CacheTool.getImage(Locator.getCache(), level.loading);
    Image imgLevelComplete = CacheTool.getImage(Locator.getCache(), level.completed);
    Image imgGameOver      = CacheTool.getImage(Locator.getCache(), GAME_OVER_IMAGE);

    world = new World();

    creepsSpawnTrigger = new Trigger();
    creepsDeadTrigger  = new Trigger();
    Trigger levelStartTrigger  = new Trigger();
    Trigger playersDeadTrigger = new Trigger();

    creepsSpawnTrigger.addCondition(AlwaysTrueCondition.INSTANCE);
    levelStartTrigger.addCondition(AlwaysTrueCondition.INSTANCE);
    playersDeadTrigger.addCondition(new AllInactiveCondition(players));

    levelStartTrigger.addAllEffects(Arrays.asList(
      new SetForegroundEffect(Locator.getUI(), imgLevelStart),
      new ExecuteWithDelayEffect(
        TRIGGER_DELAY, new AddTriggerEffect(creepsSpawnTrigger))
    ));

    creepsSpawnTrigger.addAllEffects(Arrays.asList(
      new SetForegroundEffect(Locator.getUI(), null),
      new AddTriggerEffect(creepsDeadTrigger)
    ));

    List<IEffect> levelCompleteEffects = Arrays.asList(
      new SetForegroundEffect(Locator.getUI(), imgLevelComplete),
      new ExecuteWithDelayEffect(
        TRIGGER_DELAY, new LevelCompleteEffect(gameMode))
    );

    if (level.boss == null) {
      // No boss, level complete after all creeps dead
      creepsDeadTrigger.addAllEffects(levelCompleteEffects);
    } else {
      Image imgBoss = CacheTool.getImage(Locator.getCache(), level.preBossImage);

      Unit boss = entityFactory.makeBoss(bossesData.getBoss(level.boss));

      Trigger bossDeadTrigger = new Trigger();
      bossDeadTrigger.addCondition(new AllInactiveCondition(Arrays.asList(boss.entity)));
      bossDeadTrigger.addEffect(new ExecuteWithDelayEffect(TRIGGER_DELAY,
        levelCompleteEffects));

      creepsDeadTrigger.addEffect(
        new ExecuteWithDelayEffect(TRIGGER_DELAY, Arrays.asList(
          new SetForegroundEffect(Locator.getUI(), imgBoss),
          new ExecuteWithDelayEffect(TRIGGER_DELAY, Arrays.asList(
            new SetForegroundEffect(Locator.getUI(), null),
            new SpawnBossEffect(boss),
            new AddTriggerEffect(bossDeadTrigger)
          ))
        ))
      );
    }

    playersDeadTrigger.addEffect(new ExecuteWithDelayEffect(
      TRIGGER_DELAY,
      Arrays.asList(
        new SetForegroundEffect(Locator.getUI(), imgGameOver),
        new ExecuteWithDelayEffect(TRIGGER_DELAY,
          new MainMenuEffect(stateManager))
      ))
    );

    world.addTrigger(levelStartTrigger);
    world.addTrigger(playersDeadTrigger);

    for (Entity p : players) {
      world.addUnit(p);
    }

    setupCreeps(level.creeps);

    return world;
  }
}
