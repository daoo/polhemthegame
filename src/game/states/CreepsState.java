/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.CacheTool;
import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.life.Life;
import game.entities.IEntity;
import game.factories.Factory;
import game.world.World;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import loader.data.DataException;
import loader.data.json.CreepsData;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.LevelData.CreepSpawnData;
import loader.data.json.LevelData.CreepStateData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

public class CreepsState implements IRoundState {
  private final class ToBeCreep {
    public final float spawnTime;
    public final IEntity creep;

    public ToBeCreep(float spawnTime, IEntity creep) {
      this.spawnTime = spawnTime;
      this.creep     = creep;
    }
  }

  private final LinkedList<ToBeCreep> toBeSpawned;
  private final LinkedList<IEntity> spawned;

  private float getCreepX(Rectangle rect, int width) {
    return rect.getX2() + width;
  }

  private float getCreepY(Rectangle rect, int height) {
    return Locator.getRandom().nextFloat(0.0f, rect.getY2() - height);
  }

  public CreepsState(Rectangle rect, CreepStateData sd)
    throws IOException, ParserException, DataException {
    spawned = new LinkedList<IEntity>();
    toBeSpawned = new LinkedList<ToBeCreep>();

    CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());
    for (CreepSpawnData spawnData : sd.creeps) {
      CreepData data = creepsData.getCreep(spawnData.creep);

      toBeSpawned.add(new ToBeCreep(
        spawnData.spawnTime,
        Factory.makeCreep(getCreepX(rect, data.hitbox.width),
                          getCreepY(rect, data.hitbox.height),
                          (float) -Math.PI, data)));
    }
  }

  @Override
  public void update(GameTime time, World world) {
    Iterator<ToBeCreep> itc = toBeSpawned.iterator();
    while (itc.hasNext()) {
      ToBeCreep tmp = itc.next();

      if (tmp.spawnTime < time.elapsed) {
        tmp.creep.sendMessage(ComponentMessage.START_ANIMATION, null);
        world.add(tmp.creep);

        spawned.add(tmp.creep);
        itc.remove();
      }
    }

    Iterator<IEntity> itr = spawned.iterator();
    while (itr.hasNext()) {
      IEntity c = itr.next();

      if (!((Life) c.getComponent(ComponentType.HEALTH)).isAlive()) {
        itr.remove();
      }
    }
  }

  @Override
  public void render(Graphics g) {
    // No rendering done by this state
  }

  @Override
  public boolean isFinished() {
    return toBeSpawned.isEmpty() && spawned.isEmpty();
  }
}
