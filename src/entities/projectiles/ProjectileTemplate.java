/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package entities.projectiles;

import java.io.IOException;

import loader.data.json.ProjectilesData.ProjectileData;
import loader.parser.ParserException;
import main.Launcher;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import other.CacheTool;
import other.GameTime;

import components.graphics.Invisible;
import components.graphics.RSheet;
import components.graphics.TexturedQuad;
import components.graphics.animations.Idle;
import components.interfaces.ICompAnim;

public class ProjectileTemplate {
  private final ProjectileData data;
  private final Image          img;
  private final SpriteSheet    explosion;

  public ProjectileTemplate(final ProjectileData data)
    throws IOException, ParserException {
    this.data = data;

    if (data.texture != null) {
      img = CacheTool.getImage(Launcher.cache, data.texture);
    } else if (data.sprite != null) {
      img = CacheTool.getSpriteSheet(Launcher.cache, data.sprite);
    } else {
      img = null;
    }

    if (data.aoe != null) {
      explosion = CacheTool.getSpriteSheet(Launcher.cache, data.aoe.explosionSprite);
    } else {
      explosion = null;
    }
  }

  public Projectile makeProjectile(final float x, final float y, final float rot,
                                   final GameTime time) {
    ICompAnim anim = null;
    if (img == null) {
      anim = new Invisible();
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
