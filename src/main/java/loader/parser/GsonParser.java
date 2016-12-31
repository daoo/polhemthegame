/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GsonParser<T extends Closeable> implements Parser<T> {
  private final Gson mGson;
  private final Class<T> mType;

  public GsonParser(Gson gson, Class<T> type) {
    mGson = gson;
    mType = type;
  }

  @Override
  public T parse(InputStream br) throws ParserException {
    try {
      return mGson.fromJson(new InputStreamReader(br), mType);
    } catch (JsonParseException e) {
      throw new ParserException(e);
    }
  }
}
