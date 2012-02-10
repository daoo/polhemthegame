/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons.machines;

import game.components.graphics.animations.Continuous;
import game.components.graphics.animations.RunTo;
import game.components.holdables.weapons.IMagazine;
import game.components.holdables.weapons.OutOfAmmoException;
import game.components.holdables.weapons.ProjectileQueue;
import game.components.interfaces.IAnimatedComponent;
import game.types.GameTime;
import util.Timer;

/**
 * State machine for a automatic weapon.
 */
public class AutomaticMachine implements IWeaponMachine {
  enum States { IDLE, RELOAD, COOLDOWN }

  private final int reloadLength, cooldownLength;
  private final IMagazine magazine;
  private final ProjectileQueue queue;
  private final IAnimatedComponent anim;

  private Timer timer;

  private boolean fire;
  private WeaponStates state;

  public AutomaticMachine(int reloadLength, int cooldownLength,
      IMagazine magazine, ProjectileQueue queue, IAnimatedComponent anim) {
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
          if (fire) {
            state = WeaponStates.FIRE;
          } else {
            state = WeaponStates.IDLE;
            anim.setAnimator(new RunTo(anim.getTileCount(), anim.getFirstTile()));
          }

          timer = null;
        }
        break;
      case FIRE:
        try {
          magazine.takeOne();

          anim.setAnimator(new Continuous(anim.getTileCount()));

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
