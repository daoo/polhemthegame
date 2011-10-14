package game.components.physics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.entities.projectiles.Projectile;
import game.world.World;
import math.CollisionHelper;
import math.time.GameTime;

public class ProjectileCollision implements ILogicComponent {
  private IEntity owner;
  private World world;

  @Override
  public void update(final GameTime time) {
    // Check for collisions with units
    final AABB a = owner.getBody();
    for (final IEntity e2 : world.getUnits()) {
      if (CollisionHelper.SweepCollisionTest(a, e2.getBody(), time.getFrameLength())) {
        // FIXME: If the projectile can hit multiple targets and is sufficently slow,
        //        it might hit the same target multiple times.
        owner.sendMessage(ComponentMessage.DAMAGE, 1);
        e2.sendMessage(ComponentMessage.DAMAGE, owner.getDamage());
      }
    }
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.COLLISION;
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner = owner;
  }

}
