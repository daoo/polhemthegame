/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

public class CampaignData implements Closeable {
  public String            name;
  public ArrayList<String> levels;

  public int[]  constraints;
  public String background;

  @Override
  public void close() throws IOException {
    // Do nothing
  }
}
