package loader;

import java.io.IOException;

import loader.data.IClosable;
import loader.parser.IParser;
import loader.parser.ParserException;

public interface ICache {

  public void close() throws CacheException;

  public void delete(final String id) throws CacheException;

  public IClosable getCold(final String id, final IParser parser)
    throws IOException, ParserException;

  public IClosable getWarm(final String id) throws ObjectNotInCacheException;

  public int count();

}
