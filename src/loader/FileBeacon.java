/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileBeacon {
  private File fDataDir;

  public FileBeacon(File dir) throws FileNotFoundException {
    assert dir != null;

    if (!dir.exists()) {
      throw new FileNotFoundException(dir.getAbsolutePath());
    }

    fDataDir = new File(dir, "data/");
    if (!fDataDir.exists()) {
      throw new FileNotFoundException(fDataDir.getAbsolutePath());
    }
  }

  public FileInputStream getReader(String id) throws FileNotFoundException {
    assert id != null;

    File f = new File(fDataDir, id);
    if (f.exists()) {
      // Read file from directory
      return new FileInputStream(f);
    }

    throw new FileNotFoundException(id);
  }
}
