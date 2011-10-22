/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud;

import java.util.LinkedList;

import ui.IUI;
import ui.IUIElement;

public class UI implements IUI {
  private final LinkedList<IUIElement> elements;

  public UI() {
    elements = new LinkedList<IUIElement>();
  }

  @Override
  public void addElement(IUIElement element) {
    elements.add(element);
  }
}
