/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.menu;

import java.io.IOException;

import loader.parser.ParserException;

public class MenuButton extends MenuItem {
  private final Runnable onClick;

  public MenuButton(String name, int x, int y, Runnable onClick)
    throws IOException, ParserException {
    super(name, x, y);

    assert onClick != null;

    this.onClick = onClick;
  }

  public void click() {
    onClick.run();
  }
}
