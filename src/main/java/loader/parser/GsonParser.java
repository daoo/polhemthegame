/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import loader.IData;

public class GsonParser implements IParser {
  private final Gson gson;
  private final Type typeOf;

  public GsonParser(Type typeOf) {
    this.gson = new Gson();
    this.typeOf = typeOf;
  }

  @Override
  public IData parse(InputStream br) throws ParserException {
    try {
      return gson.fromJson(new InputStreamReader(br), typeOf);
    } catch (JsonParseException e) {
      throw new ParserException(e);
    }
  }
}
