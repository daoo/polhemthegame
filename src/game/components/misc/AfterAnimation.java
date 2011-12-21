package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.graphics.animations.Tile;
import game.components.interfaces.IAnimatedComponent;
import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.pods.GameTime;
import game.triggers.IEffect;
/**
 * Execute an effect
 */
public class AfterAnimation implements ILogicComponent {
  private final Entity owner;
  private final IAnimatedComponent anim;
  private final Tile target;
  private final IEffect effect;

  public AfterAnimation(Entity owner, IAnimatedComponent anim, Tile target, IEffect effect) {
    this.owner = owner;
    this.anim = anim;
    this.target = target;
    this.effect = effect;
  }

  @Override
  public void update(GameTime time) {
    if (anim.getCurrentTile().equals(target)) {
      owner.addEffect(effect);
      owner.remove();
    }
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.EFFECT_ON_ANIMATION_FRAME;
  }
}