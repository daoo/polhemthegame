/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class GsonParser implements IParser {
  private final Gson mGson;
  private final Type mTypeOf;

  public GsonParser(Type typeOf) {
    mGson = new Gson();
    mTypeOf = typeOf;
  }

  @Override
  public Closeable parse(InputStream br) throws ParserException {
    try {
      return mGson.fromJson(new InputStreamReader(br), mTypeOf);
    } catch (JsonParseException e) {
      throw new ParserException(e);
    }
  }
}
