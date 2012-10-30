/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.ILogicComponent;
import game.entities.Entity;
import game.triggers.IEffect;
import game.types.GameTime;
import game.types.Message;

public class KillCreep implements ILogicComponent {
  private final Entity owner;
  private final int limit;
  private final IEffect effect;

  public KillCreep(Entity owner, int limit, IEffect effect) {
    this.owner  = owner;
    this.limit  = limit;
    this.effect = effect;
  }

  @Override
  public void update(GameTime time) {
    if (owner.body.getX2() < limit) {
      owner.addEffect(effect);
      owner.remove();
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
