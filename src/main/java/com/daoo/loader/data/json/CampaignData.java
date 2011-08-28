/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.util.ArrayList;

import loader.data.DataException;
import loader.data.IClosable;

public class CampaignData implements IClosable {
  public String            name;
  public ArrayList<String> levels;

  @Override
  public void close() throws DataException {
    // Do nothing
  }
}
