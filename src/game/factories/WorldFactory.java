/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.ComponentMessage;
import game.components.misc.MovementConstraint;
import game.entities.IEntity;
import game.entities.InvisibleRectangle;
import game.entities.Players;
import game.events.impl.DamagePlayerEvent;
import game.events.impl.KillEvent;
import game.triggers.Trigger;
import game.triggers.condition.TimerCondition;
import game.triggers.effects.SpawnWithSend;
import game.world.World;

import java.io.IOException;
import java.util.LinkedList;

import loader.data.DataException;
import loader.data.json.CreepsData;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.LevelData.CreepSpawnData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

public class WorldFactory {
  public static World makeWorld(Rectangle rectWorld, Players players) {
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

    World w = new World();

    InvisibleRectangle rectBig = new InvisibleRectangle(
      -1 * rectWorld.getWidth(), -1 * rectWorld.getHeight(),
       3 * rectWorld.getWidth(),  3 * rectWorld.getHeight()
    );
    rectBig.onNotContainsEvent.add(new KillEvent());
    w.addLast(rectBig);

    InvisibleRectangle rectCreepKiller = new InvisibleRectangle(
      -rectWorld.getWidth(), 0,
       rectWorld.getWidth(), rectWorld.getHeight()
    );

    rectCreepKiller.onContainsEvent.add(new KillEvent());
    rectCreepKiller.onContainsEvent.add(new DamagePlayerEvent());
    w.addLast(rectCreepKiller);

    players.reposition(rectWorld);
    for (IEntity p : players) {
      p.addLogicComponent(new MovementConstraint(rectWorld));
      w.addLast(p);
    }

    return w;
  }

  public static Iterable<IEntity> makeCreepTriggers(Iterable<CreepSpawnData> spawnsData,
                                                    Rectangle rectWorld, World world)
      throws DataException, ParserException, IOException {
    CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());
    LinkedList<IEntity> result = new LinkedList<IEntity>();

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
}
