package components.triggers.actions;

import main.World;
import entities.Creep;

public class SpawnCreep implements IAction {
  private final Creep creep;
  
  public SpawnCreep(final Creep creep) {
    assert (creep != null);
    
    this.creep = creep;
  }
  
  @Override
  public void execute(World world) {
    creep.start();
    world.add(creep);
  }
}
