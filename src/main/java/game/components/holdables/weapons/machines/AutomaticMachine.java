/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables.weapons.machines;

import game.components.graphics.AnimatedSheet;
import game.components.graphics.animations.ContinuousAnimator;
import game.components.graphics.animations.RunToAnimator;
import game.components.holdables.weapons.Magazine;
import game.components.holdables.weapons.ProjectileQueue;
import game.types.GameTime;
import util.Timer;

/**
 * State machine for a automatic weapon.
 */
public class AutomaticMachine implements WeaponMachine {
  private final int mReloadLength;
  private final int mCooldownLength;
  private final Magazine mMagazine;
  private final ProjectileQueue mQueue;
  private final AnimatedSheet mSheet;

  private Timer mTimer;
  private boolean mFire;
  private WeaponStates mState;

  public AutomaticMachine(
      int reloadLength, int cooldownLength, Magazine magazine, ProjectileQueue queue,
      AnimatedSheet sheet) {
    mReloadLength = reloadLength;
    mCooldownLength = cooldownLength;
    mMagazine = magazine;
    mQueue = queue;
    mSheet = sheet;

    mFire = false;
    mTimer = null;
    mState = WeaponStates.IDLE;
  }

  @Override
  public void update(GameTime time) {
    switch (mState) {
      case IDLE:
        if (mFire) {
          mSheet.setAnimator(new ContinuousAnimator(mSheet.getTileCount()));
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
          if (mMagazine.isEmpty()) {
            mSheet.setAnimator(new RunToAnimator(mSheet.getTileCount(), 0));
            mTimer = new Timer(time.elapsedMilli, mReloadLength);
            mState = WeaponStates.RELOADING;
          } else if (mFire) {
            mTimer = null;
            mState = WeaponStates.FIRE;
          } else {
            mSheet.setAnimator(new RunToAnimator(mSheet.getTileCount(), 0));
            mTimer = null;
            mState = WeaponStates.IDLE;
          }
        }
        break;
      case FIRE:
        mMagazine.takeOne();
        mQueue.queueUp();

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
