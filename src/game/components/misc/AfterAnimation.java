/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ComponentType;
import game.components.Message;
import game.components.graphics.animations.Tile;
import game.components.interfaces.IAnimatedComponent;
import game.components.interfaces.ILogicComponent;
import game.entities.Entity;
import game.pods.GameTime;
import game.triggers.IEffect;

import java.util.LinkedList;

/**
 * Execute an effect when the animation have reached a specific tile.
 */
public class AfterAnimation implements ILogicComponent {
  private final Entity owner;
  private final IAnimatedComponent anim;
  private final Tile target;

  private final LinkedList<IEffect> effects;

  public AfterAnimation(Entity owner, IAnimatedComponent anim, Tile target) {
    this.owner  = owner;
    this.anim   = anim;
    this.target = target;

    effects = new LinkedList<>();
  }

  public void add(IEffect effect) {
    effects.add(effect);
  }

  @Override
  public void update(GameTime time) {
    if (anim.getCurrentTile().isEqual(target)) {
      owner.addEffects(effects);
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.EFFECT_ON_ANIMATION_FRAME;
  }
}
