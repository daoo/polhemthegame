/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.course;

import java.io.IOException;
import java.util.Iterator;

import game.misc.CacheTool;
import game.misc.Locator;
import loader.data.json.CampaignData;
import loader.data.json.LevelData;
import loader.parser.ParserException;

public class Campaign {
  private LevelData mCurrentLevel;
  private final Iterator<String> mIterator;

  public Campaign(CampaignData data) {
    mIterator = data.levels.iterator();
  }

  public void nextLevel() throws ParserException, IOException {
    if (mIterator.hasNext()) {
      mCurrentLevel = CacheTool.getLevel(Locator.getCache(), mIterator.next());
    }
  }

  public LevelData getCurrentLevel() {
    return mCurrentLevel;
  }

  public boolean hasMoreLevels() {
    return mIterator.hasNext();
  }
}
