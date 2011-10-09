/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.IRenderComponent;
import game.entities.interfaces.IEntity;
import math.Vector2;
import math.time.GameTime;

import org.newdawn.slick.Graphics;


public class Hand implements IRenderComponent {
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

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void setOwner(IEntity owner) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
