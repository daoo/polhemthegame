/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package loader.archive;

import java.io.IOException;
import java.io.InputStream;

// TODO: Implement ArchiveReader
public class ArchiveReader extends InputStream {
  private final String  file;
  private final Archive archive;

  public ArchiveReader(final Archive archive, final String file) {
    super();

    this.archive = archive;
    this.file = file;
  }

  @Override
  public int read() throws IOException {
    return 0;
  }
}
