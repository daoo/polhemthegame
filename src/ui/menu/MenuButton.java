/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.ui.menu;

import java.io.IOException;

import com.daoo.loader.parser.ParserException;
import com.daoo.ui.IUiEvent;

public class MenuButton extends MenuItem {
  public final IUiEvent onClick;

  public MenuButton(final String name, final int x, final int y, final IUiEvent e)
    throws IOException, ParserException {
    super(name, x, y);

    assert (e != null);

    onClick = e;
  }

  public void click() {
    onClick.fire();
  }
}
