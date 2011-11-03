/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package ui.menu;

import java.io.IOException;

import loader.parser.ParserException;

public class MenuItemDisabled extends MenuItem {

  public MenuItemDisabled(String name, int x, int y)
    throws IOException, ParserException {
    super(name, x, y);

    setState(MenuItemState.DISABLED);
  }
}
