/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.misc.MovementConstraint;
import game.entities.IEntity;
import game.entities.InvisibleRectangle;
import game.entities.Players;
import game.events.impl.DamagePlayerEvent;
import game.events.impl.KillEvent;
import game.triggers.Trigger;
import game.triggers.condition.TimeCondition;
import game.triggers.effects.SpawnWithSend;
import game.world.World;

import java.io.IOException;

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
    w.add(rectBig);

    InvisibleRectangle rectCreepKiller = new InvisibleRectangle(
      -rectWorld.getWidth(), 0,
       rectWorld.getWidth(), rectWorld.getHeight()
    );

    rectCreepKiller.onContainsEvent.add(new KillEvent());
    rectCreepKiller.onContainsEvent.add(new DamagePlayerEvent());
    w.add(rectCreepKiller);

    players.reposition(rectWorld);
    for (IEntity p : players) {
      p.addLogicComponent(new MovementConstraint(rectWorld));
      w.add(p);
    }

    return w;
  }

  public static void makeCreepTriggers(Iterable<CreepSpawnData> spawnsData,
                                       Rectangle rectWorld, World world)
      throws DataException, ParserException, IOException {
    CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());
    for (CreepSpawnData spawnData : spawnsData) {
      Trigger t = new Trigger(false);
      t.addCondition(new TimeCondition(spawnData.spawnTime));

      CreepData creepData = creepsData.getCreep(spawnData.creep);
      IEntity creep = EntityFactory.makeCreep(
        getCreepX(rectWorld, creepData.hitbox.width),
        getCreepY(rectWorld, creepData.hitbox.height),
        (float) -Math.PI, creepData);

      t.addEffect(new SpawnWithSend(creep, null, null));

      world.addTrigger(t);
    }
  }

  private static float getCreepX(Rectangle rect, int width) {
    return rect.getX2() + width;
  }

  private static float getCreepY(Rectangle rect, int height) {
    return Locator.getRandom().nextFloat(rect.getY1(), rect.getY2() - height);
  }
}
