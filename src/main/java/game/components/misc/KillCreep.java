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
  private final Entity mOwner;
  private final int mLimit;
  private final IEffect mEffect;

  public KillCreep(Entity owner, int limit, IEffect effect) {
    mOwner = owner;
    mLimit = limit;
    mEffect = effect;
  }

  @Override
  public void update(GameTime time) {
    if (mOwner.getBody().getX2() < mLimit) {
      mOwner.addEffect(mEffect);
      mOwner.remove();
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
