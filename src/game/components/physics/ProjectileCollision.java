package game.components.physics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.misc.Damage;
import game.entities.IEntity;
import math.CollisionHelper;
import math.time.GameTime;

public class ProjectileCollision implements ILogicComponent {
  private static final Damage DAMAGE_ONE = new Damage(1);

  private IEntity owner;

  public ProjectileCollision() {
    // Do nothing
  }

  @Override
  public void update(final GameTime time) {
    // Check for collisions with units
    final AABB a = owner.getBody();
    for (final IEntity e2 : owner.getWorld().getUnits()) {
      if (CollisionHelper.SweepCollisionTest(a, e2.getBody(), time.getFrameLength())) {
        // FIXME: If the projectile can hit multiple targets and is sufficently slow,
        //        it might hit the same target multiple times.
        e2.sendMessage(ComponentMessage.DAMAGE,
                       owner.getComponent(ComponentType.DAMAGE));
        owner.sendMessage(ComponentMessage.DAMAGE, DAMAGE_ONE);
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
