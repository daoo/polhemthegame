/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;

import ui.IUI;
import ui.IUIElement;

public class UI implements IUI {
  private final LinkedList<IUIElement> elements;

  private final LinkedList<IUIElement> toRemove, toAdd;

  public UI() {
    elements = new LinkedList<IUIElement>();
    toRemove = new LinkedList<IUIElement>();
    toAdd = new LinkedList<IUIElement>();
  }

  @Override
  public void addElement(IUIElement element) {
    toAdd.add(element);
  }

  @Override
  public void removeElement(IUIElement element) {
    toRemove.add(element);
  }

  @Override
  public void update() {
    for (IUIElement e : elements) {
      e.update();
    }

    elements.removeAll(toRemove);
    toRemove.clear();

    elements.addAll(toAdd);
    toAdd.clear();
  }

  @Override
  public void render(Graphics g) {
    for (IUIElement e : elements) {
      e.render(g);
    }
  }
}
