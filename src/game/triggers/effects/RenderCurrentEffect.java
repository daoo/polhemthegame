/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers.effects;

import game.components.interfaces.IAnimatedComponent;
import game.pods.GameTime;
import game.triggers.IEffect;
import game.world.World;
import math.Rectangle;

import org.newdawn.slick.Graphics;

public class RenderCurrentEffect implements IEffect {
  private final Rectangle rect;
  private final IAnimatedComponent anim;
  private final Graphics graphics;

  public RenderCurrentEffect(Rectangle rect, IAnimatedComponent anim,
                             Graphics graphics) {
    assert rect != null && anim != null && graphics != null;

    this.rect     = rect;
    this.anim     = anim;
    this.graphics = graphics;
  }

  @Override
  public void execute(GameTime time, World world) {
    graphics.pushTransform();
    graphics.translate(rect.getX1(), rect.getY1());
    anim.render(graphics);
    graphics.popTransform();
    graphics.flush();
  }
}