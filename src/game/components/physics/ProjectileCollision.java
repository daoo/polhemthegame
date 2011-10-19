package game.components.physics;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.misc.Damage;
import game.entities.IEntity;
import math.CollisionHelper;
import math.Rectangle;
import math.time.GameTime;

public class ProjectileCollision implements ILogicComponent {
  private static final Damage DAMAGE_ONE = new Damage(1);

  private IEntity owner;
  private Damage damage;
  private Movement movement;

  private boolean enableCollisions;

  public ProjectileCollision() {
    enableCollisions = true;
  }

  @Override
  public void update(final GameTime time) {
    if (enableCollisions) {
      // Check for collisions with units
      final Rectangle a = owner.getBody();
      for (final IEntity e2 : owner.getWorld().getUnits()) {
        if (CollisionHelper.sweepCollisionTest(
              a, movement.getVelocity(), e2.getBody(), time.getFrameLength())) {
          // FIXME: If the projectile can hit multiple targets and is sufficently slow,
          //        it might hit the same target multiple times.
          e2.sendMessage(ComponentMessage.DAMAGE, damage);
          owner.sendMessage(ComponentMessage.DAMAGE, DAMAGE_ONE);
        }
      }
    }
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    if (message == ComponentMessage.KILL) {
      enableCollisions = false;
    }
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.COLLISION;
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner    = owner;
    this.damage   = (Damage) owner.getComponent(ComponentType.DAMAGE);
    this.movement = (Movement) owner.getComponent(ComponentType.MOVEMENT);
  }
}
