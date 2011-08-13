/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.menu;

import java.io.IOException;

import loader.parser.ParserException;
import events.IEvent;

public class MenuButton extends MenuItem {
  public final IEvent onClick;

  public MenuButton(final String name, final int x, final int y,
                    final IEvent e, final boolean disabled) throws IOException,
    ParserException {
    super(name, x, y, disabled);

    onClick = e;
  }

  public void click() {
    onClick.fire();
  }
}
