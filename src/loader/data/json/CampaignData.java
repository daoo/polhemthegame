/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.IOException;
import java.util.ArrayList;

import loader.IData;

public class CampaignData implements IData {
  public String            name;
  public ArrayList<String> levels;

  public int[]  constraints;
  public String background;

  @Override
  public void close() throws IOException {
    // Do nothing
  }
}
