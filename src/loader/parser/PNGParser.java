/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import java.io.InputStream;

import loader.IData;
import loader.data.DataImage;

import org.newdawn.slick.SlickException;

public class PNGParser implements IParser {
  @Override
  public IData parse(InputStream is) throws ParserException {
    try {
      // NOTE: ref is set to is.toString() because it seemed as a logic decision
      return new DataImage(is, is.toString(), false);
    } catch (SlickException e) {
      throw new ParserException(e);
    }
  }
}
