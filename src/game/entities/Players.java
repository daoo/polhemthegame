/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.CacheTool;
import game.components.ComponentType;
import game.components.misc.Life;
import game.factories.Factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import loader.data.DataException;
import loader.data.json.PlayersData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;
import math.Vector2;

public class Players implements Iterable<IEntity> {
  private static final float      STARING_X = 0.1f;
  private final int               count;
  private final ArrayList<IEntity> players;

  public Players(final int count)
    throws ParserException, DataException, IOException {
    super();

    players = new ArrayList<IEntity>();
    this.count = count;

    final PlayersData data = CacheTool.getPlayers(Locator.getCache());
    int j = 0;
    for (int i = 0; i < count; i++) {
      players.add(Factory.makePlayer(0, 0, data.players.get(i)));
      j = (j + 1) % data.players.size();
    }
  }

  public void reposition(final Rectangle rect) {
    final float dy = rect.getHeight() / (count * 2);
    final Vector2 v = new Vector2(rect.getWidth() * Players.STARING_X, dy);
    for (final IEntity p : players) {
      p.getBody().setPosition(v);
      v.y += dy;
    }
  }

  public boolean isAlive() {
    for (final IEntity p : players) {
      if (((Life) p.getComponent(ComponentType.HEALTH)).isAlive()) {
        return true;
      }
    }

    return false;
  }

  @Override
  public Iterator<IEntity> iterator() {
    return players.iterator();
  }
}
