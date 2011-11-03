/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package ui.menu;


import java.io.IOException;

import loader.parser.ParserException;
import ui.IUIEvent;

public class MenuButton extends MenuItem {
  public final IUIEvent onClick;

  public MenuButton(String name, int x, int y, IUIEvent e)
    throws IOException, ParserException {
    super(name, x, y);

    assert (e != null);

    onClick = e;
  }

  public void click() {
    onClick.fire();
  }
}
