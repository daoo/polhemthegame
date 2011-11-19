/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.data.json;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

public class CampaignData implements Closeable {
  public String            name;
  public ArrayList<String> levels;

  @Override
  public void close() throws IOException {
    // Do nothing
  }
}
