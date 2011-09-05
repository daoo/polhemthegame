package events;

import entities.interfaces.IObject;
import game.World;

public class ObjectEventArgs extends EventArgs {
  private final IObject object;

  public ObjectEventArgs(final World world, final IObject object) {
    super(world);
    this.object = object;
  }

  public IObject getObject() {
    return object;
  }
}
