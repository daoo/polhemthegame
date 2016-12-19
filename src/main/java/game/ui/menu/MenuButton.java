/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.ui.menu;

import java.io.IOException;

import game.ui.IUIEvent;
import loader.parser.ParserException;

public class MenuButton extends MenuItem {
  public final IUIEvent onClick;

  public MenuButton(String name, int x, int y, IUIEvent e)
    throws IOException, ParserException {
    super(name, x, y);

    assert e != null;

    onClick = e;
  }

  public void click() {
    onClick.fire();
  }
}
