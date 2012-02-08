/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package loader.parser;

import java.io.InputStream;

import loader.IData;
import loader.data.DataSpriteSheet;

import org.newdawn.slick.Image;

public class SpriteSheetParser implements IParser {
  private final int tileWidth;
  private final int tileHeight;
  private final int spacing;

  private final PNGParser pngParser;

  public SpriteSheetParser(int tileWidth, int tileHeight, int spacing,
      PNGParser pngParser) {
    this.tileWidth = tileWidth;
    this.tileHeight = tileHeight;
    this.spacing = spacing;
    this.pngParser = pngParser;
  }

  @Override
  public IData parse(InputStream br) throws ParserException {
    Image img = (Image) pngParser.parse(br);

    return new DataSpriteSheet(img, tileWidth, tileHeight, spacing);
  }
}
