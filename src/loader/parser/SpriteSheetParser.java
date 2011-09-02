/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.loader.parser;

import java.io.InputStream;

import org.newdawn.slick.SlickException;

import com.daoo.loader.data.ClosableSpriteSheet;
import com.daoo.loader.data.IClosable;

public class SpriteSheetParser implements IParser {
  private final int tw, th;

  public SpriteSheetParser(final int tw, final int th) {
    this.tw = tw;
    this.th = th;
  }

  @Override
  public IClosable parse(final InputStream is) throws ParserException {
    try {
      // NOTE: ref is set to is.toString() because it seemed as a logic decision
      return new ClosableSpriteSheet(is.toString(), is, tw, th);
    } catch (final SlickException e) {
      throw new ParserException(e);
    }
  }

}
