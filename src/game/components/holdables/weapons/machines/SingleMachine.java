/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons.machines;

import game.components.graphics.animations.Idle;
import game.components.graphics.animations.RunTo;
import game.components.holdables.weapons.Magazine;
import game.components.holdables.weapons.OutOfAmmoException;
import game.components.holdables.weapons.ProjectileQueue;
import game.components.interfaces.IAnimatedComponent;
import game.types.GameTime;
import util.Timer;

/**
 * State machine for a single-mode weapon.
 */
public class SingleMachine implements IWeaponMachine {
  enum States { IDLE, RELOAD, COOLDOWN }

  private final int reloadLength, cooldownLength;
  private final Magazine magazine;
  private final ProjectileQueue queue;
  private final IAnimatedComponent anim;

  private Timer timer;

  private boolean fire;
  private WeaponStates state;

  public SingleMachine(int reloadLength, int cooldownLength,
      Magazine magazine, ProjectileQueue queue, IAnimatedComponent anim) {
    this.reloadLength = reloadLength;
    this.cooldownLength = cooldownLength;
    this.magazine = magazine;
    this.queue = queue;
    this.anim = anim;

    fire = false;
    timer = null;
    state = WeaponStates.IDLE;
  }

  @Override
  public void update(GameTime time) {
    switch (state) {
      case IDLE:
        if (magazine.isEmpty()) {
          timer = new Timer(time.elapsedMilli, reloadLength);
          state = WeaponStates.RELOADING;
        } else if (fire) {
          state = WeaponStates.FIRE;
        }
        break;
      case RELOADING:
        timer.update(time.elapsedMilli);

        if (timer.isFinished()) {
          magazine.reload();
          state = WeaponStates.IDLE;
          timer = null;
        }
        break;
      case COOLDOWN:
        timer.update(time.elapsedMilli);

        if (timer.isFinished()) {
          state = WeaponStates.IDLE;
          anim.setAnimator(new Idle());
          fire = false;

          timer = null;
        }
        break;
      case FIRE:
        try {
          magazine.takeOne();

          anim.setAnimator(new RunTo(anim.getTileCount(), anim.getLastTile()));

          timer = new Timer(time.elapsedMilli, cooldownLength);
          state = WeaponStates.COOLDOWN;

          queue.queueUp();
        } catch (OutOfAmmoException e) {
          // This should not happen
          e.printStackTrace();

          // Go back to idle
          state = WeaponStates.IDLE;
        }
        break;
    }
  }

  @Override
  public void startFiring() {
    fire = true;
  }

  @Override
  public void stopFiring() {
    fire = false;
  }

  @Override
  public float getProgress() {
    if (timer == null) {
      return magazine.getFractionFilled();
    } else {
      return timer.getProgress();
    }
  }
}
