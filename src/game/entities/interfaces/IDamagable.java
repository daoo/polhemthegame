/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities.interfaces;

public interface IDamagable {
  public void damage(final float damage);

  public void kill();
  public boolean isAlive();
}
