package game.components.physics;

import java.util.LinkedList;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.interfaces.ILogicComponent;
import game.components.misc.Damage;
import game.entities.IEntity;
import math.CollisionHelper;
import math.Rectangle;
import math.Vector2;
import math.time.GameTime;

public class ProjectileCollision implements ILogicComponent {
  private static final Damage DAMAGE_ONE = new Damage(1);

  private boolean enableCollisions;
  private final LinkedList<IEntity> collidedWith;

  private IEntity owner;
  private Damage damage;
  private Movement movement;

  public ProjectileCollision() {
    enableCollisions = true;
    collidedWith = new LinkedList<IEntity>();
  }

  private void collisionCheck(Rectangle a, Vector2 m, IEntity b, float dt) {
    if (!collidedWith.contains(b)) {
      if (CollisionHelper.sweepCollisionTest(a, m, b.getBody(), dt)) {
        b.sendMessage(ComponentMessage.DAMAGE, damage);
        owner.sendMessage(ComponentMessage.DAMAGE, DAMAGE_ONE);

        collidedWith.add(b);
      }
    }
  }

  @Override
  public void update(GameTime time) {
    if (enableCollisions) {
      for (IEntity e : owner.getWorld().getUnits()) {
        collisionCheck(owner.getBody(), movement.getVelocity(), e, time.frame);
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
  public void setOwner(IEntity owner) {
    this.owner    = owner;
    this.damage   = (Damage) owner.getComponent(ComponentType.DAMAGE);
    this.movement = (Movement) owner.getComponent(ComponentType.MOVEMENT);
  }
}
