/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.holdables;


import org.newdawn.slick.Graphics;

import other.GameTime;
import basics.Vector2;

import components.interfaces.ICompUpRend;

public class Hand implements ICompUpRend {
  protected IHoldable     item;
  protected final Vector2 offset;

  public Hand(final float handOffsetX, final float handOffsetY) {
    offset = new Vector2(handOffsetX, handOffsetY);

    item = null;
  }

  public void startUse() {
    if (item != null) {
      item.toggleOn();
    }
  }

  public void stopUse() {
    if (item != null) {
      item.toggleOff();
    }
  }

  public void grab(final IHoldable newItem) {
    // To grab a new holdable we need to stop using the current one
    stopUse();

    this.item = newItem;
  }

  @Override
  public void update(final GameTime time) {
    item.update(time);
  }

  @Override
  public void render(final Graphics g) {
    g.pushTransform();
    g.translate(offset.x, offset.y);

    item.render(g);

    g.popTransform();
  }

  public IHoldable getHoldable() {
    return item;
  }

  public Vector2 getOffset() {
    return offset;
  }
}
