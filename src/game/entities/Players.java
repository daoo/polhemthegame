/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.CacheTool;
import game.components.ComponentType;
import game.components.life.Life;
import game.factories.EntityFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import loader.data.DataException;
import loader.data.json.PlayersData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;
import math.Vector2;

public class Players implements Iterable<IEntity> {
  private static final float STARING_X = 0.1f;
  private final int count;
  private final LinkedList<IEntity> players;

  public Players(int count)
    throws ParserException, DataException, IOException {
    super();

    players = new LinkedList<>();
    this.count = count;

    PlayersData data = CacheTool.getPlayers(Locator.getCache());
    int j = 0;
    for (int i = 0; i < count; i++) {
      players.add(EntityFactory.makePlayer(data.players.get(i)));
      j = (j + 1) % data.players.size();
    }
  }

  /**
   * Reposition players in the rectangle.
   * Places the players evenly spaced on the y axis with 10% of the width
   * as margin on the left side of the players.
   * @param rect the rectangle to use for alignment
   */
  public void reposition(Rectangle rect) {
    float dx = 0;
    float dy = rect.getHeight() / (count * 2);

    float x = rect.getX1() + rect.getWidth() * Players.STARING_X;
    float y = rect.getY1() + dy;

    for (IEntity p : players) {
      p.getBody().setPosition(new Vector2(x, y - p.getBody().getHeight() / 2.0f));

      x += dx;
      y += dy;
    }
  }

  public boolean isAlive() {
    for (IEntity p : players) {
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
