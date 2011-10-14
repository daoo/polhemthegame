/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities.projectiles;

import game.CacheTool;
import game.components.graphics.DummyAnimation;
import game.components.graphics.RSheet;
import game.components.graphics.TexturedQuad;
import game.components.graphics.animations.Idle;
import game.components.interfaces.ICompAnim;
import game.entities.IEntity;

import java.io.IOException;

import loader.data.json.ProjectilesData.ProjectileData;
import loader.parser.ParserException;
import main.Locator;
import math.time.GameTime;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;



public class ProjectileTemplate {
  private final ProjectileData data;
  private final Image          img;
  private final SpriteSheet    explosion;

  public ProjectileTemplate(final ProjectileData data)
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
      explosion = CacheTool.getSpriteSheet(Locator.getCache(), data.aoe.explosionSprite);
    } else {
      explosion = null;
    }
  }

  public IEntity makeProjectile(final float x, final float y, final float rot,
                                   final GameTime time) {
    ICompAnim anim = null;
    if (img == null) {
      anim = new DummyAnimation();
    } else if (data.texture != null) {
      anim = new TexturedQuad(img);
    } else if (data.sprite != null) {
      anim = new RSheet(data.sprite.framerate,
                        data.sprite.offset.x,
                        data.sprite.offset.y,
                        (SpriteSheet) img,
                        new Idle());
    }

    if (data.aoe == null) {
      return new Projectile(x, y, rot, data, anim, time);
    }
    else {
      final RSheet explosionAnim = new RSheet(data.aoe.explosionSprite.framerate,
                                              data.aoe.explosionSprite.offset.x,
                                              data.aoe.explosionSprite.offset.y,
                                              explosion, new Idle());
      return new ExplodingProjectile(x, y, rot, data, anim, explosionAnim, time);
    }
  }
}
