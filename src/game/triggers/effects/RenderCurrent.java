package game.triggers.effects;

import game.components.interfaces.IAnimatedComponent;
import game.pods.GameTime;
import game.triggers.IEffect;
import game.world.World;
import math.Rectangle;

import org.newdawn.slick.Graphics;

public class RenderCurrent implements IEffect {
  private final Rectangle rect;
  private final IAnimatedComponent anim;
  private final Graphics graphics;

  public RenderCurrent(Rectangle rect, IAnimatedComponent anim, Graphics graphics) {
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
