/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
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

  public GsonParser(final Type typeOf) {
    this.typeOf = typeOf;
  }

  @Override
  public IClosable parse(final InputStream br) throws ParserException {
    try {
      return new Gson().fromJson(new InputStreamReader(br), typeOf);
    } catch (final JsonParseException e) {
      throw new ParserException(e);
    }
  }

}
