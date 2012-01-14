package loader;

import java.io.IOException;

/**
 * Types that want to be held by an ICache shall implement this interface.
 */
public interface IData {
  void close() throws IOException;
}
