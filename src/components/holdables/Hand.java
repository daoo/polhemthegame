/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.holdables;

import math.Vector2;
import math.time.GameTime;

import org.newdawn.slick.Graphics;


import components.holdables.weapons.Weapon;
import components.interfaces.ICompUpRend;

public class Hand implements ICompUpRend {
  private Weapon                   weapon;
  private final Vector2            offset;

  public Hand(final float handOffsetX, final float handOffsetY) {
    offset = new Vector2(handOffsetX, handOffsetY);

    weapon = null;
  }

  public void startUse() {
    if (weapon != null) {
      weapon.toggleOn();
    }
  }

  public void stopUse() {
    if (weapon != null) {
      weapon.toggleOff();
    }
  }

  public void grab(final Weapon newItem) {
    // To grab a new holdable we need to stop using the current one
    stopUse();

    weapon = newItem;
  }

  @Override
  public void update(final GameTime time) {
    weapon.update(time);
  }

  @Override
  public void render(final Graphics g) {
    g.pushTransform();
    g.translate(offset.x, offset.y);

    weapon.render(g);

    g.popTransform();
  }

  public Weapon getWeapon() {
    return weapon;
  }

  public Vector2 getOffset() {
    return offset;
  }
}
