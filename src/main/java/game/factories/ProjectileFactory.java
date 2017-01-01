/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.components.IRenderComponent;
import game.components.graphics.AnimatedSheet;
import game.components.graphics.TexturedQuad;
import game.components.graphics.animations.ContinuousAnimator;
import game.components.graphics.animations.RandomAnimator;
import game.components.misc.EffectsOnDeath;
import game.components.misc.Life;
import game.components.misc.OutOfBounds;
import game.components.misc.ProjectileDamage;
import game.components.misc.RangeLimiter;
import game.components.physics.Gravity;
import game.components.physics.Movement;
import game.components.physics.MovingProjectileCollision;
import game.components.physics.StaticCollision;
import game.entities.Entity;
import game.entities.IEntity;
import game.misc.CacheTool;
import game.misc.Locator;
import game.triggers.IEffect;
import game.triggers.effects.AOEDamageEffect;
import game.triggers.effects.RemoveEntityEffect;
import game.triggers.effects.spawn.SpawnAnimationEffect;
import game.types.Orientation;
import loader.data.json.types.ProjectileData;
import loader.parser.ParserException;
import math.Aabb;
import math.ExtraMath;
import util.SpriteSheet;

public class ProjectileFactory {
  private final Aabb mBoundary;
  private final Orientation mOrientation;
  private final int mLaunchAngle;
  private final int mSpread;
  private final ProjectileData mData;
  private final Image mImg;
  private final SpriteSheet mSprite;
  private final SpriteSheet mExplosion;
  private final Graphics mStatics;

  /**
   * Construct a new projectile factory for a given projectile.
   *
   * @param boundary boundary rectangle, the projectiles should die when leaving
   * this area
   * @param data the projectile data
   * @param launchAngle the angle of launching in degrees, zero means horizontal
   * @param spread spread in degrees, [-spread, spread]
   * @param orientation the orientation of the weapon firing this projectile
   * @param statics graphics context for static rendering
   * @throws IOException when fetching images from the drive fails
   * @throws ParserException when fetching images from the drive fails
   */
  public ProjectileFactory(
      Aabb boundary, int launchAngle, int spread, Orientation orientation, ProjectileData data,
      Graphics statics) throws IOException, ParserException {
    assert data != null;

    mBoundary = boundary;
    mLaunchAngle = launchAngle;
    mSpread = spread;
    mOrientation = orientation;
    mData = data;
    mStatics = statics;

    if (data.texture != null) {
      mImg = CacheTool.getImage(Locator.getCache(), data.texture);
      mSprite = null;
    } else if (data.sprite != null) {
      mImg = null;
      mSprite = CacheTool.getSpriteSheet(Locator.getCache(), data.sprite);
    } else {
      mImg = null;
      mSprite = null;
    }

    mExplosion = data.aoe == null
        ? null
        : CacheTool.getSpriteSheet(Locator.getCache(), data.aoe.explosionSprite);
  }

  public int getWidth() {
    return mData.hitbox.width;
  }

  /**
   * Make a new projectile.
   *
   * @param source the source of the projectile (who fired it), can be null
   * @param x the x coordinate of where it starts
   * @param y the y coordinate of where it starts
   * @return a new projectile
   */
  public Entity makeProjectile(IEntity source, float x, float y) {
    Entity p = new Entity(x, y, mData.hitbox.width, mData.hitbox.height);

    p.addLogicComponent(new Life(p, mData.targets));
    p.addLogicComponent(new RangeLimiter(p, mData.duration, mData.range));

    p.addLogicComponent(new ProjectileDamage(p, source, mData.damage));
    p.addLogicComponent(new OutOfBounds(p, mBoundary));

    int angle = getRotation();

    // If these conditions are met, the projectile is moving
    if (mData.speed != 0 || mData.gravity) {
      Movement mov = getMovement(p, angle);
      p.addLogicComponent(mov);

      if (mData.gravity) {
        p.addLogicComponent(new Gravity(mov));
      }

      if (mData.collides) {
        p.addLogicComponent(new MovingProjectileCollision(p, mov));
      }
    } else {
      if (mData.collides) {
        p.addLogicComponent(new StaticCollision(p));
      }
    }

    IRenderComponent render = getRender(angle);
    if (render != null) {
      p.addRenderComponent(render);
    }

    ArrayList<IEffect> effectsOnDeath = new ArrayList<>();
    effectsOnDeath.add(new RemoveEntityEffect(p));

    if (mData.aoe != null) {
      setupExplosion(source, p, effectsOnDeath);
    }

    p.addLogicComponent(new EffectsOnDeath(p, effectsOnDeath));

    return p;
  }

  private Movement getMovement(Entity p, int angle) {
    float rad = ExtraMath.degToRad(angle);

    float dx = (float) Math.cos(rad) * mData.speed;
    float dy = (float) Math.sin(rad) * mData.speed;

    if (mOrientation == Orientation.LEFT) {
      dx = -dx;
    }

    return new Movement(p, dx, dy);
  }

  private int getRotation() {
    if (mSpread == 0) {
      return mLaunchAngle;
    }

    return mLaunchAngle + Locator.getRandom().nextInt(-mSpread, mSpread) - mSpread / 2;
  }

  private void setupExplosion(IEntity source, Entity p, List<IEffect> effectsOnDeath) {
    AnimatedSheet explosionAnim = new AnimatedSheet(mData.aoe.explosionSprite.framerate,
        mData.aoe.explosionSprite.offset.x, mData.aoe.explosionSprite.offset.y, Orientation.RIGHT,
        0, mExplosion);

    effectsOnDeath
        .add(new AOEDamageEffect(source, p.getBody(), mData.aoe.radius, mData.aoe.damage));
    effectsOnDeath.add(new SpawnAnimationEffect(p, explosionAnim, mStatics));
  }

  private IRenderComponent getRender(int angle) {
    if (mData.texture != null) {
      return new TexturedQuad(mImg, mOrientation, angle);
    } else if (mData.sprite != null) {
      AnimatedSheet sheet = new AnimatedSheet(mData.sprite.framerate, mData.sprite.offset.x,
          mData.sprite.offset.y, mOrientation, angle, mSprite);

      if (mData.sprite.random) {
        sheet.setAnimator(new RandomAnimator(sheet.getTileCount()));
      } else {
        sheet.setAnimator(new ContinuousAnimator(sheet.getTileCount()));
      }

      return sheet;
    } else {
      return null;
    }
  }
}
