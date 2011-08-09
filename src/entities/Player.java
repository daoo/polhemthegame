/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package entities;

import org.newdawn.slick.Color;

import other.GameTime;
import ui.infobar.Bar;
import basics.Vector2;

import components.basic.Inventory;
import components.basic.SimpleControl;
import components.basic.Stats;
import components.holdables.Hand;
import components.holdables.weapons.Weapon;
import components.interfaces.IArmed;
import components.interfaces.ICompAnim;
import components.triggers.actions.SpawnProjectile;

import entities.projectiles.Projectile;
import entities.projectiles.ProjectileTemplate;

public class Player extends Unit implements IArmed {
  private final Inventory     inv;
  private final Hand          hand;
  private final Bar           weaponBar;
  private final SimpleControl control;

  public Player(final float x, final float y,
                final float width, final float height,
                final float handOffsetX, final float handOffsetY,
                final float speed, final int maxHP, final int startingMoney,
                final ICompAnim walk, final ICompAnim death) {
    super(x, y, width, height, 0, 0, maxHP, walk, death);

    inv = new Inventory(startingMoney);
    hand = new Hand(handOffsetX, handOffsetY);
    weaponBar = new Bar(Color.blue, null);
    control = new SimpleControl(this, speed);

    add(hand);
    addBar(weaponBar);
    add(control);
  }

  @Override
  public void update(final GameTime time) {
    super.update(time);

    final Weapon w = hand.getWeapon();

    weaponBar.setFraction(w.getProgress());

    // Find out if there are any projectiles that want to be spawned
    for (final ProjectileTemplate tmp : w.projectiles) {
      final Vector2 o = body.getMin().add(hand.getOffset().add(w.getMuzzleOffset()));
      final Projectile p = tmp.makeProjectile(o.x, o.y, w.getAngle(), time);
      actions.add(new SpawnProjectile(p));
    }
    w.projectiles.clear();
  }

  @Override
  public void giveWeapon(final Weapon w) {
    if (!inv.contains(w)) {
      inv.add(w);
    }
    hand.grab(w);
  }

  public void previousWeapon() {
    hand.grab(inv.previousWeapon());
  }

  public void nextWeapon() {
    hand.grab(inv.nextWeapon());
  }

  public Hand getHand() {
    return hand;
  }

  public Inventory getInventory() {
    return inv;
  }

  public Stats getStats() {
    throw new UnsupportedOperationException("Not implemented");
  }

}
