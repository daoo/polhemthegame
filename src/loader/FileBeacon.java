/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileBeacon {
  private final File    fDataDir;

  public FileBeacon(final File dir) throws FileNotFoundException {
    if (!dir.exists()) {
      throw new FileNotFoundException(dir.getAbsolutePath());
    }

    fDataDir = new File(dir, "data/");
    if (!fDataDir.exists()) {
      throw new FileNotFoundException(fDataDir.getAbsolutePath());
    }
  }

  public InputStream getReader(final String id) throws FileNotFoundException {
    final File f = new File(fDataDir, id);
    if (f.exists()) {
      // Read file from directory
      return new LineStream(f);
    }

    throw new FileNotFoundException(id);
  }
}
