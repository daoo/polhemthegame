/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.CacheTool;
import game.entities.Creep;
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
    public final Creep creep;

    public ToBeCreep(final float spawnTime, final Creep creep) {
      this.spawnTime = spawnTime;
      this.creep     = creep;
    }
  }

  private final LinkedList<ToBeCreep> toBeSpawned;
  private final LinkedList<Creep> spawned;

  private float getCreepX(final Rectangle rect, final int width) {
    return rect.getX2() + width;
  }

  private float getCreepY(final Rectangle rect, final int height) {
    return Locator.getRandom().nextFloat(0.0f, rect.getY2() - height);
  }

  public CreepsState(final Rectangle rect, final CreepStateData sd)
    throws IOException, ParserException, DataException {
    spawned = new LinkedList<Creep>();
    toBeSpawned = new LinkedList<ToBeCreep>();

    final CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());
    for (final CreepSpawnData spawnData : sd.creeps) {
      final CreepData data = creepsData.getCreep(spawnData.creep);

      toBeSpawned.add(new ToBeCreep(
        spawnData.spawnTime,
        Factory.MakeCreep(getCreepX(rect, data.hitbox.width),
                          getCreepY(rect, data.hitbox.height),
                          (float) -Math.PI, data)));
    }
  }

  @Override
  public void update(final GameTime time, final World world) {
    final Iterator<ToBeCreep> itc = toBeSpawned.iterator();
    while (itc.hasNext()) {
      final ToBeCreep tmp = itc.next();

      if (tmp.spawnTime < time.getElapsed()) {
        tmp.creep.start();
        world.add(tmp.creep);

        spawned.add(tmp.creep);
        itc.remove();
      }
    }

    final Iterator<Creep> itr = spawned.iterator();
    while (itr.hasNext()) {
      final Creep c = itr.next();

      if (!c.isAlive()) {
        itr.remove();
      }
    }
  }

  @Override
  public void render(final Graphics g) {
    // No rendering done by this state
  }

  @Override
  public boolean isFinished() {
    return toBeSpawned.isEmpty() && spawned.isEmpty();
  }
}
