/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;

import ui.IUI;
import ui.IDynamicUIElement;

public class UI implements IUI {
  private final LinkedList<IDynamicUIElement> elements;

  private final LinkedList<IDynamicUIElement> toRemove, toAdd;

  public UI() {
    elements = new LinkedList<IDynamicUIElement>();
    toRemove = new LinkedList<IDynamicUIElement>();
    toAdd    = new LinkedList<IDynamicUIElement>();
  }

  @Override
  public void addElement(IDynamicUIElement element) {
    toAdd.add(element);
  }

  @Override
  public void removeElement(IDynamicUIElement element) {
    toRemove.add(element);
  }

  @Override
  public void clearElements() {
    elements.clear();
  }

  @Override
  public void update() {
    for (IDynamicUIElement e : elements) {
      e.update();
    }

    elements.removeAll(toRemove);
    toRemove.clear();

    elements.addAll(toAdd);
    toAdd.clear();
  }

  @Override
  public void render(Graphics g) {
    for (IDynamicUIElement e : elements) {
      e.render(g);
    }
  }
}
