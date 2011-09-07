/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.states;

import game.CacheTool;
import game.components.triggers.actions.IAction;
import game.components.triggers.actions.SpawnCreep;
import game.entities.Creep;
import game.factories.Factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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

public class CreepsState implements IState {
  private final ArrayList<IAction> actions;
  private final ArrayList<Creep>   toBeSpawned, spawned;

  private float getCreepX(Rectangle rect, final int width) {
    return rect.getX2() + width;
  }

  private float getCreepY(Rectangle rect, final int height) {
    return Locator.getRandom().nextFloat(0.0f, rect.getY2() - height);
  }

  public CreepsState(final Rectangle rect, final CreepStateData sd)
    throws IOException, ParserException, DataException {
    actions = new ArrayList<IAction>();

    spawned = new ArrayList<Creep>(sd.creeps.size());
    toBeSpawned = new ArrayList<Creep>(sd.creeps.size());

    final CreepsData creepsData = CacheTool.getCreeps(Locator.getCache());
    for (final CreepSpawnData spawnData : sd.creeps) {
      final CreepData data = creepsData.getCreep(spawnData.creep);

      toBeSpawned.add(Factory.MakeCreep(getCreepX(rect, data.hitbox.width),
                                        getCreepY(rect, data.hitbox.height),
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
        actions.add(new SpawnCreep(c));
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
  public boolean isFinished() {
    return toBeSpawned.isEmpty() && spawned.isEmpty();
  }

  @Override
  public boolean hasActions() {
    return !actions.isEmpty();
  }

  @Override
  public Collection<IAction> getActions() {
    return actions;
  }

  @Override
  public void clearActions() {
    actions.clear();
  }
}
