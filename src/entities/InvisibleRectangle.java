package com.daoo.entities;

import java.util.Collection;
import java.util.Collections;

import org.newdawn.slick.Graphics;

import com.daoo.components.physics.AABB;
import com.daoo.components.triggers.actions.IAction;
import com.daoo.entities.interfaces.IEntity;
import com.daoo.entities.interfaces.IObject;
import com.daoo.events.EventHandler;
import com.daoo.events.ObjectEventArgs;
import com.daoo.game.World;
import com.daoo.math.Vector2;
import com.daoo.math.time.GameTime;

public class InvisibleRectangle implements IEntity {
  private final AABB body;

  public final EventHandler onNotContainsEvent;

  public InvisibleRectangle(float x, float y, float w, float h) {
    body = new AABB(x, y, w, h, 0, 0);

    onNotContainsEvent = new EventHandler();
  }

  @Override
  public void update(final GameTime time, final World world) {
    for (final IObject o : world.getUnits()) {
      final Unit u = (Unit) o;
      if (!body.isContaining(u.getBody())) {
        onNotContainsEvent.execute(this, new ObjectEventArgs(world, u));
      }
    }
  }

  @Override
  public void render(Graphics g) {
    // Do nothing
  }

  @Override
  public void setPosition(Vector2 v) {
    body.setPosition(v);
  }

  @Override
  public void setVelocity(Vector2 v) {
    body.addVelocity(v);
  }

  @Override
  public AABB getBody() {
    return body;
  }

  @Override
  public boolean isAlive() {
    return true;
  }

  @Override
  public boolean hasActions() {
    return false;
  }

  @Override
  public Collection<IAction> getActions() {
    return Collections.emptyList();
  }

  @Override
  public void clearActions() {
    // Do nothing
  }
}
