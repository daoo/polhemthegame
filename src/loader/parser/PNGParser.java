/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import java.io.InputStream;

import loader.data.ClosableImage;
import loader.data.IClosable;

import org.newdawn.slick.SlickException;

public class PNGParser implements IParser {
  @Override
  public IClosable parse(final InputStream is) throws ParserException {
    try {
      // NOTE: ref is set to is.toString() because it seemed as a logic decision
      return new ClosableImage(is, is.toString(), false);
    } catch (final SlickException e) {
      throw new ParserException(e);
    }
  }

}
