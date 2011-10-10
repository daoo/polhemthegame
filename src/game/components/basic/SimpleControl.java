/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.basic;


import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import math.Vector2;
import math.time.GameTime;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;

public class SimpleControl implements ILogicComponent {
  private static final int KEY_COUNT = 1024;

  enum TOGGLED {
    NO, OFF, ON
  }

  boolean[] keyStatus;

  private IEntity owner;

  private float       upOrDown, rightOrLeft;
  private final float speed;

  public SimpleControl(final float speed) {
    keyStatus = new boolean[SimpleControl.KEY_COUNT];

    this.speed = speed;

    upOrDown = 0;
    rightOrLeft = 0;
  }

  private TOGGLED isKeyToggled(final int key) {
    final boolean keyNewStatus = Keyboard.isKeyDown(key);
    if (keyStatus[key] != keyNewStatus) {
      keyStatus[key] = keyNewStatus;

      return keyNewStatus ? TOGGLED.ON : TOGGLED.OFF;
    }

    return TOGGLED.NO;
  }

  @Override
  public void update(final GameTime time) {
    if (((Life)owner.getComponent(ComponentType.HEALTH)).isAlive()) {
      TOGGLED t;

      // Walking
      t = isKeyToggled(Input.KEY_W);
      if (t == TOGGLED.ON) {
        upOrDown += -1;
      } else if (t == TOGGLED.OFF) {
        upOrDown += 1;
      }

      t = isKeyToggled(Input.KEY_S);
      if (t == TOGGLED.ON) {
        upOrDown += 1;
      } else if (t == TOGGLED.OFF) {
        upOrDown += -1;
      }

      t = isKeyToggled(Input.KEY_A);
      if (t == TOGGLED.ON) {
        rightOrLeft += -1;
      } else if (t == TOGGLED.OFF) {
        rightOrLeft += 1;
      }

      t = isKeyToggled(Input.KEY_D);
      if (t == TOGGLED.ON) {
        rightOrLeft += 1;
      } else if (t == TOGGLED.OFF) {
        rightOrLeft += -1;
      }

      if ((upOrDown != 0) || (rightOrLeft != 0)) {
        owner.sendMessage(ComponentMessage.START_ANIMATION, null);
      } else {
        owner.sendMessage(ComponentMessage.STOP_ANIMATION, null);
      }

      owner.getBody().setVelocity(new Vector2(rightOrLeft * speed, upOrDown * speed));

      // Shooting
      t = isKeyToggled(Input.KEY_SPACE);
      if (t == TOGGLED.ON) {
        owner.sendMessage(ComponentMessage.START_HOLDABLE, null);
      } else if (t == TOGGLED.OFF) {
        owner.sendMessage(ComponentMessage.STOP_HOLDABLE, null);
      }
    }
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.CONTROL;
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner = owner;
  }
}
