/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.game;

import java.io.IOException;
import java.util.Iterator;

import com.daoo.loader.data.json.CampaignData;
import com.daoo.loader.data.json.LevelData;
import com.daoo.loader.parser.ParserException;
import com.daoo.ptg.CacheTool;
import com.daoo.ptg.Locator;

public class GameCampaign {
  private final CampaignData     campaign;
  private LevelData              currentLevel;
  private final Iterator<String> iterator;

  public GameCampaign(final CampaignData data) {
    campaign = data;

    iterator = campaign.levels.iterator();
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
