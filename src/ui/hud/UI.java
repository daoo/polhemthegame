/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package ui.hud;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.Graphics;

import ui.IDynamicUIElement;
import ui.IStaticUIElement;
import ui.IUI;

public class UI implements IUI {
  private final LinkedList<IStaticUIElement> statics;
  private final LinkedList<IDynamicUIElement> elements;

  private final LinkedList<IDynamicUIElement> toAdd;

  public UI() {
    elements = new LinkedList<IDynamicUIElement>();
    statics  = new LinkedList<IStaticUIElement>();
    toAdd    = new LinkedList<IDynamicUIElement>();
  }

  public void addStatic(IStaticUIElement element) {
    statics.add(element);
  }

  @Override
  public void addElement(IDynamicUIElement element) {
    toAdd.add(element);
  }

  @Override
  public void clearElements() {
    elements.clear();
  }

  @Override
  public void update() {
    Iterator<IDynamicUIElement> it = elements.iterator();
    while (it.hasNext()) {
      IDynamicUIElement e = it.next();
      if (e.isActive()) {
        e.update();
      } else {
        it.remove();
      }
    }

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
