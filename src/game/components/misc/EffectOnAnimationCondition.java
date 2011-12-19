package game.components.misc;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.graphics.animations.Tile;
import game.components.interfaces.IAnimatedComponent;
import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.pods.GameTime;
import game.triggers.IEffect;

public class EffectOnAnimationCondition implements ILogicComponent {
  private final Entity owner;
  private final IAnimatedComponent anim;
  private final Tile target;
  private final IEffect effect;

  /**
   * Since components can't be "deactivated" and we don't wan't the effect to be
   * spawned multiple times.
   */
  private boolean activated;

  public EffectOnAnimationCondition(Entity owner, IAnimatedComponent anim, Tile target, IEffect effect) {
    this.owner = owner;
    this.anim = anim;
    this.target = target;
    this.effect = effect;

    activated = false;
  }

  @Override
  public void update(GameTime time) {
    if (!activated && anim.getCurrentTile().equals(target)) {
      activated = true;
      owner.addEffect(effect);
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
