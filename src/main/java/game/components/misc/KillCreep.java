/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.components.misc;

import game.components.LogicComponent;
import game.entities.EntityImpl;
import game.triggers.Effect;
import game.types.GameTime;
import game.types.Message;

public class KillCreep implements LogicComponent {
  private final EntityImpl mOwner;
  private final float mLimit;
  private final Effect mEffect;

  public KillCreep(EntityImpl owner, float limit, Effect effect) {
    mOwner = owner;
    mLimit = limit;
    mEffect = effect;
  }

  @Override
  public void update(GameTime time) {
    if (mOwner.getBody().getMax().x < mLimit) {
      mOwner.addEffect(mEffect);
      mOwner.remove();
    }
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
