/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.holdables;

import game.actions.SpawnProjectile;
import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.IRenderComponent;
import game.entities.IEntity;
import game.entities.projectiles.ProjectileTemplate;
import math.Vector2;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

import ui.hud.infobar.IProgress;


public class Hand implements IRenderComponent, IProgress {
  private final Vector2 offset;

  private IEntity owner;
  private Weapon weapon;

  public Hand(final float handOffsetX, final float handOffsetY) {
    offset = new Vector2(handOffsetX, handOffsetY);

    weapon = null;
  }

  public void startUse() {
    if (weapon != null) {
      weapon.toggleOn();
    }
  }

  public void stopUse() {
    if (weapon != null) {
      weapon.toggleOff();
    }
  }

  public void grab(final Weapon newItem) {
    // To grab a new holdable we need to stop using the current one
    stopUse();

    weapon = newItem;
  }

  @Override
  public void update(final GameTime time) {
    weapon.update(time);

    // Find out if there are any projectiles that want to be spawned
    for (final ProjectileTemplate projTemplate : weapon.projectiles) {
      final Vector2 o = owner.getBody().getMin().add(offset.add(weapon.getMuzzleOffset()));
      final IEntity p = projTemplate.makeProjectile(o.x, o.y, weapon.getAngle(), time);
      owner.addAction(new SpawnProjectile(p));
    }
    weapon.projectiles.clear();
  }

  @Override
  public void render(final Graphics g) {
    g.pushTransform();
    g.translate(offset.x, offset.y);

    weapon.render(g);

    g.popTransform();
  }

  @Override
  public void reciveMessage(final ComponentMessage message, final Object args) {
    if (message == ComponentMessage.START_HOLDABLE) {
      startUse();
    } else if (message == ComponentMessage.STOP_HOLDABLE) {
      stopUse();
    }
  }

  @Override
  public float getProgress() {
    return weapon.getProgress();
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentType.HAND;
  }

  @Override
  public void setOwner(final IEntity owner) {
    this.owner = owner;
  }
}
