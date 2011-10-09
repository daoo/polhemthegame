/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.basic.Inventory;
import game.components.basic.SimpleControl;
import game.components.holdables.Hand;
import game.components.holdables.IArmed;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ICompAnim;
import game.components.triggers.actions.SpawnProjectile;
import game.entities.groups.EntityType;
import game.entities.projectiles.Projectile;
import game.entities.projectiles.ProjectileTemplate;
import game.world.World;
import math.Vector2;
import math.time.GameTime;

import org.newdawn.slick.Color;

import ui.hud.infobar.Bar;



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
    super(x, y, width, height, 0, 0, EntityType.PLAYER, maxHP, walk, death);

    inv       = new Inventory(startingMoney);
    hand      = new Hand(handOffsetX, handOffsetY);
    weaponBar = new Bar(Color.blue, new Color(0, 0, 0, 0));
    control   = new SimpleControl(this, hand, speed);

    add(hand);
    addBar(weaponBar);
    add(control);
  }

  @Override
  public void update(final GameTime time, final World world) {
    super.update(time, world);

    final Weapon w = hand.getWeapon();

    weaponBar.setFraction(w.getProgress());

    // Find out if there are any projectiles that want to be spawned
    for (final ProjectileTemplate tmp : w.projectiles) {
      final Vector2 o = body.getMin().add(hand.getOffset().add(w.getMuzzleOffset()));
      final Projectile p = tmp.makeProjectile(o.x, o.y, w.getAngle(), time);
      addAction(new SpawnProjectile(p));
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
}
