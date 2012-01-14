/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.graphics.DummyAnimation;
import game.components.graphics.RSheet;
import game.components.graphics.TexturedQuad;
import game.components.graphics.animations.Continuous;
import game.components.interfaces.IAnimatedComponent;
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
import game.triggers.IEffect;
import game.triggers.effects.AOEDamageEffect;
import game.triggers.effects.RemoveEntityEffect;
import game.triggers.effects.spawn.SpawnAnimationEffect;

import java.io.IOException;
import java.util.LinkedList;

import loader.data.json.types.ProjectileData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class ProjectileFactory {
  private final Rectangle rect;
  private final ProjectileData data;
  private final Image img;
  private final SpriteSheet sprite, explosion;

  /**
   * Construct a new projectile factory for a given projectile.
   * @param bounds boundary rectangle, the projectiles should die when leaving
   *               this area
   * @param data the projectile data
   * @throws IOException when fetching images from the drive fails
   * @throws ParserException when fetching images from the drive fails
   */
  public ProjectileFactory(Rectangle bounds, ProjectileData data)
      throws IOException, ParserException {
    assert data != null;

    this.rect = bounds;
    this.data = data;

    if (data.texture != null) {
      img = CacheTool.getImage(Locator.getCache(), data.texture);
      sprite = null;
    } else if (data.sprite != null) {
      img = null;
      sprite = CacheTool.getSpriteSheet(Locator.getCache(), data.sprite);
    } else {
      img = null;
      sprite = null;
    }

    if (data.aoe != null) {
      explosion = CacheTool.getSpriteSheet(Locator.getCache(),
                                           data.aoe.explosionSprite);
    } else {
      explosion = null;
    }
  }

  public IEntity makeProjectile(IEntity source, float x, float y, float rot) {
    IAnimatedComponent anim = makeAnimation();

    Entity e = new Entity(x, y, data.hitbox.width, data.hitbox.height);

    Life life               = new Life(e, data.targets);
    RangeLimiter range      = new RangeLimiter(e, data.duration, data.range);
    ProjectileDamage damage = new ProjectileDamage(e, source, data.damage);
    OutOfBounds bounds      = new OutOfBounds(e, rect);

    e.addLogicComponent(life);
    e.addLogicComponent(range);

    e.addLogicComponent(damage);
    e.addLogicComponent(bounds);

    // If these conditions are met, the projectile is moving
    if (data.speed != 0 || data.gravity) {
      Movement mov = new Movement(e, (float) Math.cos(rot) * data.speed,
                                     (float) Math.sin(rot) * data.speed);
      e.addLogicComponent(mov);

      if (data.gravity) {
        e.addLogicComponent(new Gravity(mov));
      }

      if (data.collides) {
        e.addLogicComponent(new MovingProjectileCollision(e, mov));
      }
    } else {
      // Not moving, static collisions
      if (data.collides) {
        e.addLogicComponent(new StaticCollision(e));
      }
    }
    e.addRenderComponent(anim);

    LinkedList<IEffect> effectsOnDeath = new LinkedList<>();
    effectsOnDeath.add(new RemoveEntityEffect(e));

    if (data.aoe != null) {
      RSheet explosionAnim = new RSheet(data.aoe.explosionSprite.framerate,
                                        data.aoe.explosionSprite.offset.x,
                                        data.aoe.explosionSprite.offset.y,
                                        explosion);

      effectsOnDeath.add(new AOEDamageEffect(
        source, e.getBody(), data.aoe.radius, data.aoe.damage));
      effectsOnDeath.add(new SpawnAnimationEffect(e, explosionAnim, null));
    }

    e.addLogicComponent(new EffectsOnDeath(e, effectsOnDeath));

    return e;
  }

  private IAnimatedComponent makeAnimation() {
    if (data.texture != null) {
      return new TexturedQuad(img);
    } else if (data.sprite != null) {
      RSheet sheet = new RSheet(
        data.sprite.framerate,
        data.sprite.offset.x,
        data.sprite.offset.y,
        sprite
      );
      sheet.setAnimator(new Continuous(sheet.getTileCount()));
      return sheet;
    } else {
      return new DummyAnimation();
    }
  }
}
