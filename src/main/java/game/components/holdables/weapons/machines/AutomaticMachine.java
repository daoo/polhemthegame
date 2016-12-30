/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons.machines;

import game.components.graphics.AnimatedSheet;
import game.components.graphics.animations.ContinuousAnimator;
import game.components.graphics.animations.RunToAnimator;
import game.components.graphics.animations.Tile;
import game.components.holdables.weapons.IMagazine;
import game.components.holdables.weapons.ProjectileQueue;
import game.types.GameTime;
import util.Timer;

/**
 * State machine for a automatic weapon.
 */
public class AutomaticMachine implements IWeaponMachine {
  private final int reloadLength;
  private final int cooldownLength;
  private final IMagazine magazine;
  private final ProjectileQueue queue;
  private final AnimatedSheet anim;

  private Timer timer;

  private boolean fire;
  private WeaponStates state;

  public AutomaticMachine(int reloadLength, int cooldownLength,
      IMagazine magazine, ProjectileQueue queue, AnimatedSheet anim) {
    this.reloadLength   = reloadLength;
    this.cooldownLength = cooldownLength;
    this.magazine       = magazine;
    this.queue          = queue;
    this.anim           = anim;

    fire  = false;
    timer = null;
    state = WeaponStates.IDLE;
  }

  @Override
  public void update(GameTime time) {
    switch (state) {
      case IDLE:
        if (fire) {
          anim.setAnimator(new ContinuousAnimator(anim.getTileCount()));
          state = WeaponStates.FIRE;
        }
        break;
      case RELOADING:
        timer.update(time.elapsedMilli);

        if (timer.isFinished()) {
          magazine.reload();
          timer = null;
          state = WeaponStates.IDLE;
        }
        break;
      case COOLDOWN:
        timer.update(time.elapsedMilli);

        if (timer.isFinished()) {
          if (magazine.isEmpty()) {
            anim.setAnimator(new RunToAnimator(anim.getTileCount(), Tile.ZERO));
            timer = new Timer(time.elapsedMilli, reloadLength);
            state = WeaponStates.RELOADING;
          } else if (fire) {
            timer = null;
            state = WeaponStates.FIRE;
          } else {
            anim.setAnimator(new RunToAnimator(anim.getTileCount(), Tile.ZERO));
            timer = null;
            state = WeaponStates.IDLE;
          }
        }
        break;
      case FIRE:
        magazine.takeOne();
        queue.queueUp();

        timer = new Timer(time.elapsedMilli, cooldownLength);
        state = WeaponStates.COOLDOWN;
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
    }

    return timer.getProgress();
  }
}
