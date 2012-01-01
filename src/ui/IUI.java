/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public interface IUI {
  void addDynamic(IDynamicUIElement element);
  void addStatic(IStaticUIElement element);
  void setForegroundImage(Image image);

  void update();
  void renderDynamics(Graphics g);
  void renderStatics(Graphics g);
}
