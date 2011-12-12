/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
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
  private final LinkedList<IDynamicUIElement> dynamics;

  private final LinkedList<IDynamicUIElement> toAdd;

  public UI() {
    dynamics = new LinkedList<>();
    statics  = new LinkedList<>();
    toAdd    = new LinkedList<>();
  }

  @Override
  public void addStatic(IStaticUIElement element) {
    statics.add(element);
  }

  @Override
  public void addDynamic(IDynamicUIElement element) {
    toAdd.add(element);
  }

  @Override
  public void update() {
    Iterator<IDynamicUIElement> it = dynamics.iterator();
    while (it.hasNext()) {
      IDynamicUIElement e = it.next();
      if (e.isActive()) {
        e.update();
      } else {
        it.remove();
      }
    }

    dynamics.addAll(toAdd);
    toAdd.clear();
  }

  @Override
  public void renderDynamics(Graphics g) {
    for (IDynamicUIElement e : dynamics) {
      e.render(g);
    }
  }

  @Override
  public void renderStatics(Graphics g) {
      for (IStaticUIElement e : statics) {
      e.render(g);
    }
  }
}
