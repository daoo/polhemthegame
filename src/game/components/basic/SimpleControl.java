/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.basic;


import game.components.ComponentMessages;
import game.components.holdables.Hand;
import game.components.interfaces.ICompUpdate;
import game.entities.Unit;
import math.Vector2;
import math.time.GameTime;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;

public class SimpleControl implements ICompUpdate {
  private static final int KEY_COUNT = 1024;

  enum TOGGLED {
    NO, OFF, ON
  }

  boolean[] keyStatus;

  private float       upOrDown, rightOrLeft;
  private final Unit  player;
  private final Hand  hand;
  private final float speed;

  public SimpleControl(final Unit player, final Hand hand, final float speed) {
    this.player = player;
    this.speed = speed;
    this.hand = hand;

    keyStatus = new boolean[SimpleControl.KEY_COUNT];

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
    if (player.isAlive()) {
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
        player.start();
      } else {
        player.stop();
      }

      player.getBody().setVelocity(new Vector2(rightOrLeft * speed, upOrDown * speed));

      // Shooting
      t = isKeyToggled(Input.KEY_SPACE);
      if (t == TOGGLED.ON) {
        hand.startUse();
      } else if (t == TOGGLED.OFF) {
        hand.stopUse();
      }
    }
  }

  @Override
  public void reciveMessage(final ComponentMessages message) {
    // Do nothing
  }
}
