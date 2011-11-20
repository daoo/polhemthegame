/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import java.io.Closeable;
import java.io.InputStream;

import loader.data.ClosableSpriteSheet;

import org.newdawn.slick.SlickException;

public class SpriteSheetParser implements IParser {
  private final int tw, th;

  public SpriteSheetParser(int tw, int th) {
    this.tw = tw;
    this.th = th;
  }

  @Override
  public Closeable parse(InputStream is) throws ParserException {
    try {
      // NOTE: ref is set to is.toString() because it seemed as a logic decision
      return new ClosableSpriteSheet(is.toString(), is, tw, th);
    } catch (SlickException ex) {
      throw new ParserException(ex);
    }
  }
}
