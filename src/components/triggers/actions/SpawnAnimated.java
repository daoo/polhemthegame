package components.triggers.actions;

import main.World;

import components.interfaces.ICompAnim;

import entities.Animated;

public class SpawnAnimated implements IAction {
  private final Animated animated;

  public SpawnAnimated(final float x, final float y,
                       final float width, final float height,
                       final ICompAnim anim) {
    animated = new Animated(x, y, width, height, anim);
  }

  @Override
  public void execute(final World world) {
    animated.start();
    world.add(animated);
  }
}
