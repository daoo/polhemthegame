/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import loader.archive.Archive;
import loader.archive.ArchiveReader;

public class FileBeacon {
  private final File    fDataDir;
  private final Archive archive;

  public FileBeacon(final File dir) throws FileNotFoundException {
    if (!dir.exists()) {
      throw new FileNotFoundException(dir.getAbsolutePath());
    }

    fDataDir = new File(dir, "data/");
    if (!fDataDir.exists()) {
      throw new FileNotFoundException(fDataDir.getAbsolutePath());
    }

    final File fDataArchive = new File(fDataDir, "data.rft");
    if (fDataArchive.exists()) {
      archive = new Archive(fDataArchive);
    } else {
      archive = null;
    }
  }

  public InputStream getReader(final String id) throws FileNotFoundException {
    final File f = new File(fDataDir, id);
    if (f.exists()) {
      // Read file from directory
      return new LineStream(f);
    } else if ((archive != null) && archive.hasFile(id)) {
      return new ArchiveReader(archive, id);
    }

    throw new FileNotFoundException(id);
  }
}
