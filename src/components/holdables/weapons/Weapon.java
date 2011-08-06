package components.holdables.weapons;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import other.GameTime;
import basics.Vector2;

import components.graphics.RSheet;
import components.graphics.animations.Continuous;
import components.holdables.IHoldable;
import components.holdables.weapons.states.CoolDownState;
import components.holdables.weapons.states.IWeaponState;
import components.holdables.weapons.states.ReloadingState;

import entities.projectiles.ProjectileTemplate;

public abstract class Weapon implements IHoldable {
  protected final Vector2                    muzzleOffset;

  protected final float                      reloadTime, cooldownTime;
  protected final int                        magazineSize;

  protected final float                      angle;

  /**
   * Decides what the weapon should do next frame.
   */
  protected WEAPON_ACTION                    nextAction;
  /**
   * How many rounds there are left in the magazine.
   */
  protected int                              rounds;

  /**
   * Spawned projectiles, for owner to pass on to world.
   */
  public final ArrayList<ProjectileTemplate> projectiles;

  protected final RSheet                     anim;
  protected final ProjectileTemplate         projTemplate;

  protected IWeaponState                     currentState;

  public Weapon(final Vector2 muzzleOffset, final float reloadTime,
                final float cooldownTime, final int magazineSize,
                final float angle, final RSheet anim,
                final ProjectileTemplate projTemplate) {
    this.muzzleOffset = muzzleOffset;
    this.reloadTime   = reloadTime;
    this.cooldownTime = cooldownTime;
    this.magazineSize = magazineSize;
    this.angle        = angle;
    this.anim         = anim;
    this.projTemplate = projTemplate;

    nextAction  = WEAPON_ACTION.NONE;
    rounds      = magazineSize;
    projectiles = new ArrayList<ProjectileTemplate>();
  }

  @Override
  public void update(final GameTime time) {
    anim.update(time);
    
    if (currentState != null) {
      currentState.update(time);

      if (currentState.isFinished()) {
        currentState = null;

        if (isInUse()) {
          anim.setAnimator(new Continuous(anim.getTileCount()));
        }
      }
    }
    else {    
      if (isEmpty()) {
        startReload(time);
      } else if (nextAction == WEAPON_ACTION.FIRE_ONCE) {
        fire(time.getElapsed());
        nextAction = WEAPON_ACTION.NONE;
      } else if (isInUse() && isReadyToShoot()) {
        fire(time.getElapsed());
      }
    }
  }

  @Override
  public void render(final Graphics g) {
    anim.render(g);
  }

  @Override
  public void useOnce() {
    if (isReadyToShoot()) {
      nextAction = WEAPON_ACTION.FIRE_ONCE;
    }
  }

  @Override
  public float getProgress() {
    if (currentState != null) {
      return currentState.getProgress();
    }
    else {
      return (float) rounds / (float) magazineSize;
    }
  }

  public Vector2 getMuzzleOffset() {
    return muzzleOffset;
  }

  public float getAngle() {
    return angle;
  }
  
  protected boolean isIdle() {
    return currentState == null;
  }

  protected boolean isReadyToShoot() {
    return !isEmpty() && isIdle();
  }

  protected boolean isEmpty() {
    return (magazineSize != -1) && (rounds <= 0);
  }

  protected void fire(final float elapsed) {
    if (magazineSize != -1) {
      rounds -= 1;
    }

    projectiles.add(projTemplate);

    currentState = new CoolDownState(elapsed, cooldownTime);
  }

  protected void startReload(final GameTime time) {
    currentState = new ReloadingState(time.getElapsed(), reloadTime);
    rounds = magazineSize;
  }
}
