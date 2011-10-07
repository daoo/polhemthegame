/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.menu;

import java.io.IOException;

import loader.parser.ParserException;

public class MenuItemDisabled extends MenuItem {

  public MenuItemDisabled(final String name, final int x, final int y)
    throws IOException, ParserException {
    super(name, x, y);

    setState(MENU_ITEM_STATE.DISABLED);
  }
}
