/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.interfaces;

import org.newdawn.slick.Image;

public interface ICompAnim extends ICompUpRend {
  /**
   * Should not have any side effects besides starting a stopped animation.
   */
  void start();
  void stop();
  void restart();
  boolean isRunning();

  Image getLastFrame();
  
  void goToFirstFrame();
  
  int getTileWidth();
  int getTileHeight();
}
