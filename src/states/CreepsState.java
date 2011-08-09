/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package states;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import loader.data.DataException;
import loader.data.json.CreepsData;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.LevelData.CreepSpawnData;
import loader.data.json.LevelData.CreepStateData;
import loader.parser.ParserException;
import main.Launcher;
import main.World;

import org.newdawn.slick.Graphics;

import other.CacheTool;
import other.GameTime;
import basics.ExMath;
import entities.Creep;
import factories.CreepFactory;

public class CreepsState implements ICompState {
  private final World            world;
  private final ArrayList<Creep> toBeSpawned, spawned;

  private float getCreepX(final int width) {
    return world.getX2() + width;
  }

  private float getCreepY(final int height) {
    return ExMath.random(0, world.getY2() - height);
  }

  public CreepsState(final CreepStateData sd, final World world)
    throws IOException, ParserException, DataException {
    super();
    this.world = world;

    spawned     = new ArrayList<Creep>(sd.creeps.size());
    toBeSpawned = new ArrayList<Creep>(sd.creeps.size());

    final CreepsData creepsData = CacheTool.getCreeps(Launcher.cache);
    for (final CreepSpawnData spawnData : sd.creeps) {
      final CreepData data = creepsData.getCreep(spawnData.creep);

      toBeSpawned.add(CreepFactory.Make(getCreepX(data.hitbox.width),
                                        getCreepY(data.hitbox.height),
                                        (float) -Math.PI,
                                        spawnData.spawnTime, data));
    }
  }

  @Override
  public void update(final GameTime time) {
    final Iterator<Creep> itc = toBeSpawned.iterator();
    while (itc.hasNext()) {
      final Creep c = itc.next();

      if (c.getSpawnTime() < time.getElapsed()) {
        c.start();
        world.add(c);
        spawned.add(c);
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
  public void start() {
    // No special actions to take when starting this state
  }

  @Override
  public boolean isFinished() {
    return toBeSpawned.isEmpty() && spawned.isEmpty();
  }
}
