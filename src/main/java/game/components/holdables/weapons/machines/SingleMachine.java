/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons.machines;

import game.components.graphics.AnimatedSheet;
import game.components.graphics.animations.IdleAnimator;
import game.components.graphics.animations.RunToAnimator;
import game.components.holdables.weapons.Magazine;
import game.components.holdables.weapons.ProjectileQueue;
import game.types.GameTime;
import util.Timer;

/**
 * State machine for a single-mode weapon.
 */
public class SingleMachine implements WeaponMachine {
  private final int mReloadLength;
  private final int mCooldownLength;
  private final Magazine mMagazine;
  private final ProjectileQueue mQueue;
  private final AnimatedSheet mAnim;

  private Timer mTimer;

  private boolean mFire;
  private WeaponStates mState;

  public SingleMachine(
      int reloadLength, int cooldownLength, Magazine magazine, ProjectileQueue queue,
      AnimatedSheet anim) {
    mReloadLength = reloadLength;
    mCooldownLength = cooldownLength;
    mMagazine = magazine;
    mQueue = queue;
    mAnim = anim;

    mFire = false;
    mTimer = null;
    mState = WeaponStates.IDLE;
  }

  @Override
  public void update(GameTime time) {
    switch (mState) {
      case IDLE:
        if (mMagazine.isEmpty()) {
          mTimer = new Timer(time.elapsedMilli, mReloadLength);
          mState = WeaponStates.RELOADING;
        } else if (mFire) {
          mState = WeaponStates.FIRE;
        }
        break;
      case RELOADING:
        mTimer.update(time.elapsedMilli);

        if (mTimer.isFinished()) {
          mMagazine.reload();

          mTimer = null;
          mState = WeaponStates.IDLE;
        }
        break;
      case COOLDOWN:
        mTimer.update(time.elapsedMilli);

        if (mTimer.isFinished()) {
          mFire = false;
          mAnim.setAnimator(new IdleAnimator());

          mTimer = null;
          mState = WeaponStates.IDLE;
        }
        break;
      case FIRE:
        mMagazine.takeOne();
        mQueue.queueUp();

        mAnim.setAnimator(new RunToAnimator(mAnim.getTileCount(), 0));

        mTimer = new Timer(time.elapsedMilli, mCooldownLength);
        mState = WeaponStates.COOLDOWN;
        break;
    }
  }

  @Override
  public void startFiring() {
    mFire = true;
  }

  @Override
  public void stopFiring() {
    mFire = false;
  }

  @Override
  public float getProgress() {
    if (mTimer == null) {
      return mMagazine.getFractionFilled();
    }

    return mTimer.getProgress();
  }
}
