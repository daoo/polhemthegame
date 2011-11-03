/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import loader.data.IClosable;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class GsonParser implements IParser {
  private final Type typeOf;

  public GsonParser(Type typeOf) {
    this.typeOf = typeOf;
  }

  @Override
  public IClosable parse(InputStream br) throws ParserException {
    try {
      return new Gson().fromJson(new InputStreamReader(br), typeOf);
    } catch (JsonParseException e) {
      throw new ParserException(e);
    }
  }
}
