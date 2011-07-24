package entities;

import components.interfaces.ICompAnim;

public class Animated extends Entity {
  public Animated(final float x, final float y,
                  final float w, final float h,
                  ICompAnim anim) {
    super(x, y, w, h, 0, 0);
    
    add(anim);
  }
}
