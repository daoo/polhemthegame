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

  private final BossesData mBossesData;

  private final LevelManager mGameMode;
  private final StateManager mStateManager;

  private final Rectangle mRect;
  private final List<Entity> mPlayers;

  private final EntityFactory mEntityFactory;

  private World mWorld;

  private Trigger mCreepsSpawnTrigger;
  private Trigger mCreepsDeadTrigger;

  public WorldFactory(
      LevelManager gameMode, StateManager stateManager, EntityFactory entityFactory, Rectangle rect,
      List<Entity> players) throws ParserException, IOException {
    mGameMode = gameMode;
    mStateManager = stateManager;
    mEntityFactory = entityFactory;
    mRect = rect;
    mPlayers = players;

    mBossesData = CacheTool.getBosses(Locator.getCache());
  }

  private void setupCreeps(List<CreepSpawnData> spawnsData) throws ParserException, IOException {
    assert spawnsData != null;

    CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());

    ArrayList<Entity> creeps = new ArrayList<>(spawnsData.size());

    for (CreepSpawnData spawnData : spawnsData) {
      // Make creep
      CreepData creepData = creepsData.getCreep(spawnData.creep);
      float x = mRect.getX2() + creepData.unit.hitbox.width;
      float y = Locator.getRandom()
          .nextFloat(mRect.getY1(), mRect.getY2() - creepData.unit.hitbox.height);

      Unit creep = mEntityFactory.makeCreep(x, y, creepData);

      creep.entity.addLogicComponent(new KillCreep(creep.entity, (int) mRect.getX1(),
          new DamageEntitiesEffect(mPlayers, PLAYER_DAMAGE)));

      // Add trigger
      mCreepsSpawnTrigger
          .addEffect(new ExecuteWithDelayEffect(spawnData.spawnTime, new SpawnCreepEffect(creep)));

      creeps.add(creep.entity);
    }

    mCreepsDeadTrigger.addCondition(new AllInactiveCondition(creeps));
  }

  public World makeLevel(LevelData level) throws ParserException, IOException {
    Image imgLevelStart = CacheTool.getImage(Locator.getCache(), level.loading);
    Image imgLevelComplete = CacheTool.getImage(Locator.getCache(), level.completed);
    Image imgGameOver = CacheTool.getImage(Locator.getCache(), GAME_OVER_IMAGE);

    mWorld = new World();

    mCreepsSpawnTrigger = new Trigger();
    mCreepsDeadTrigger = new Trigger();
    Trigger levelStartTrigger = new Trigger();
    Trigger playersDeadTrigger = new Trigger();

    mCreepsSpawnTrigger.addCondition(AlwaysTrueCondition.INSTANCE);
    levelStartTrigger.addCondition(AlwaysTrueCondition.INSTANCE);
    playersDeadTrigger.addCondition(new AllInactiveCondition(mPlayers));

    levelStartTrigger.addAllEffects(Arrays
        .asList(new SetForegroundEffect(Locator.getUI(), imgLevelStart),
            new ExecuteWithDelayEffect(TRIGGER_DELAY, new AddTriggerEffect(mCreepsSpawnTrigger))));

    mCreepsSpawnTrigger.addAllEffects(Arrays.asList(new SetForegroundEffect(Locator.getUI(), null),
        new AddTriggerEffect(mCreepsDeadTrigger)));

    List<IEffect> levelCompleteEffects = Arrays
        .asList(new SetForegroundEffect(Locator.getUI(), imgLevelComplete),
            new ExecuteWithDelayEffect(TRIGGER_DELAY, new LevelCompleteEffect(mGameMode)));

    if (level.boss == null) {
      // No boss, level complete after all creeps dead
      mCreepsDeadTrigger.addAllEffects(levelCompleteEffects);
    } else {
      Image imgBoss = CacheTool.getImage(Locator.getCache(), level.preBossImage);

      Unit boss = mEntityFactory.makeBoss(mBossesData.getBoss(level.boss));

      Trigger bossDeadTrigger = new Trigger();
      bossDeadTrigger.addCondition(new AllInactiveCondition(Arrays.asList(boss.entity)));
      bossDeadTrigger.addEffect(new ExecuteWithDelayEffect(TRIGGER_DELAY, levelCompleteEffects));

      mCreepsDeadTrigger.addEffect(new ExecuteWithDelayEffect(
          TRIGGER_DELAY, Arrays.asList(new SetForegroundEffect(Locator.getUI(), imgBoss),
          new ExecuteWithDelayEffect(
              TRIGGER_DELAY, Arrays
              .asList(new SetForegroundEffect(Locator.getUI(), null), new SpawnBossEffect(boss),
                  new AddTriggerEffect(bossDeadTrigger))))));
    }

    playersDeadTrigger.addEffect(new ExecuteWithDelayEffect(
        TRIGGER_DELAY, Arrays.asList(new SetForegroundEffect(Locator.getUI(), imgGameOver),
        new ExecuteWithDelayEffect(TRIGGER_DELAY, new MainMenuEffect(mStateManager)))));

    mWorld.addTrigger(levelStartTrigger);
    mWorld.addTrigger(playersDeadTrigger);

    for (Entity p : mPlayers) {
      mWorld.addUnit(p);
    }

    setupCreeps(level.creeps);

    return mWorld;
  }
}