/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LineStream extends FileInputStream {
  public LineStream(File file) throws FileNotFoundException {
    super(file);
  }

  public String readLine() throws IOException {
    int b;
    StringBuilder sb = new StringBuilder();
    do {
      b = read();

      if ((b != '\n') && (b != -1)) {
        sb.append((char) b);
      }
    } while (b != -1);

    return sb.toString();
  }
}
