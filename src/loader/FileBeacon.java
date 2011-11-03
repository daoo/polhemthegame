/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileBeacon {
  private File fDataDir;

  public FileBeacon(File dir) throws FileNotFoundException {
    if (!dir.exists()) {
      throw new FileNotFoundException(dir.getAbsolutePath());
    }

    fDataDir = new File(dir, "data/");
    if (!fDataDir.exists()) {
      throw new FileNotFoundException(fDataDir.getAbsolutePath());
    }
  }

  public InputStream getReader(String id) throws FileNotFoundException {
    File f = new File(fDataDir, id);
    if (f.exists()) {
      // Read file from directory
      return new LineStream(f);
    }

    throw new FileNotFoundException(id);
  }
}
