/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.basic;


import math.Vector2;
import math.time.GameTime;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;


import components.holdables.Hand;
import components.interfaces.ICompUpdate;

import entities.Player;

public class SimpleControl implements ICompUpdate {
  private static final int KEY_COUNT = 1024;

  enum TOGGLED {
    NO, OFF, ON
  }

  boolean[]            keyStatus;

  private float        upOrDown, rightOrLeft;
  private final Player player;
  private final Hand   hand;
  private final float  speed;

  public SimpleControl(final Player player, final float speed) {
    this.player = player;
    this.speed = speed;
    hand = player.getHand();

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

      player.setVelocity(new Vector2(rightOrLeft * speed, upOrDown * speed));

      // Shooting
      t = isKeyToggled(Input.KEY_SPACE);
      if (t == TOGGLED.ON) {
        hand.startUse();
      } else if (t == TOGGLED.OFF) {
        hand.stopUse();
      }

      // Other
    }
  }
}
