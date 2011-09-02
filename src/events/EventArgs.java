package events;

import game.World;

public class EventArgs implements IEventArgs {
  private final World world;

  public EventArgs(final World world) {
    this.world = world;
  }

  public World getWorld() {
    return world;
  }
}
