/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.course;

import game.misc.CacheTool;
import game.misc.Locator;

import java.io.IOException;
import java.util.Iterator;

import loader.data.json.CampaignData;
import loader.data.json.LevelData;
import loader.parser.ParserException;

public class Campaign {
  private LevelData currentLevel;
  private final Iterator<String> iterator;

  public Campaign(CampaignData data) {
    iterator = data.levels.iterator();
  }

  public void nextLevel() throws ParserException, IOException {
    if (iterator.hasNext()) {
      currentLevel = CacheTool.getLevel(Locator.getCache(), iterator.next());
    }
  }

  public LevelData getCurrentLevel() {
    return currentLevel;
  }

  public boolean hasMoreLevels() {
    return iterator.hasNext();
  }
}
