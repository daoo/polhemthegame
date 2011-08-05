package entities;

import components.interfaces.ICompAnim;
import components.interfaces.IWalking;

public class Animated extends Entity implements IWalking {
  private final ICompAnim anim;

  public Animated(final float x, final float y,
                  final float w, final float h,
                  final ICompAnim anim) {
    super(x, y, w, h, 0, 0);

    this.anim = anim;
    add(anim);
  }

  @Override
  public void start() {
    anim.start();
  }

  @Override
  public void stop() {
    anim.stop();
  }
}
