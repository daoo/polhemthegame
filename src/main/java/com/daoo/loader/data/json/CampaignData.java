/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader.data.json;

import java.util.ArrayList;

import com.daoo.loader.data.DataException;
import com.daoo.loader.data.IClosable;

public class CampaignData implements IClosable {
  public String            name;
  public ArrayList<String> levels;

  @Override
  public void close() throws DataException {
    // Do nothing
  }
}
