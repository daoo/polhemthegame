/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.graphics.DummyAnimation;
import game.components.graphics.RSheet;
import game.components.graphics.TexturedQuad;
import game.components.graphics.animations.Idle;
import game.components.interfaces.IAnimatedComponent;
import game.components.life.EffectsOnDeath;
import game.components.life.Life;
import game.components.misc.ProjectileDamage;
import game.components.misc.RangeLimiter;
import game.components.physics.Gravity;
import game.components.physics.Movement;
import game.components.physics.ProjectileCollision;
import game.entities.Entity;
import game.entities.EntityType;
import game.entities.IEntity;
import game.triggers.effects.AOEDamage;
import game.triggers.effects.RemoveEntity;
import game.triggers.effects.SpawnAnimationEffect;

import java.io.IOException;

import loader.data.json.ProjectilesData.ProjectileData;
import loader.parser.ParserException;
import main.Locator;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class ProjectileTemplate {
  private final ProjectileData data;
  private final Image          img;
  private final SpriteSheet    explosion;

  public ProjectileTemplate(ProjectileData data)
    throws IOException, ParserException {
    this.data = data;

    if (data.texture != null) {
      img = CacheTool.getImage(Locator.getCache(), data.texture);
    } else if (data.sprite != null) {
      img = CacheTool.getSpriteSheet(Locator.getCache(), data.sprite);
    } else {
      img = null;
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

    Entity e = new Entity(x, y, data.hitbox.width, data.hitbox.height,
                          EntityType.PROJECTILE);

    e.addLogicComponent(new Life(data.targets));
    e.addLogicComponent(new RangeLimiter(data.duration, data.range));
    e.addLogicComponent(new Movement((float) Math.cos(rot) * data.speed,
                                     (float) Math.sin(rot) * data.speed));
    if (data.gravity) {
      e.addLogicComponent(new Gravity());
    }

    e.addLogicComponent(new ProjectileDamage(source, data.damage));
    e.addLogicComponent(new ProjectileCollision());

    EffectsOnDeath effects = new EffectsOnDeath();
    effects.add(new RemoveEntity(e));
    e.addLogicComponent(effects);

    e.addRenderComponent(anim);

    if (data.aoe != null) {
      RSheet explosionAnim = new RSheet(data.aoe.explosionSprite.framerate,
                                        data.aoe.explosionSprite.offset.x,
                                        data.aoe.explosionSprite.offset.y,
                                        explosion, new Idle());

      effects.add(new AOEDamage(source, e.getBody(), data.aoe.radius, data.aoe.damage));
      effects.add(new SpawnAnimationEffect(e, explosionAnim));
    }

    return e;
  }

  private IAnimatedComponent makeAnimation() {
    if (data.texture != null) {
      return new TexturedQuad(img);
    } else if (data.sprite != null) {
      return new RSheet(data.sprite.framerate,
                        data.sprite.offset.x,
                        data.sprite.offset.y,
                        (SpriteSheet) img,
                        new Idle());
    }

    return new DummyAnimation();
  }
}
