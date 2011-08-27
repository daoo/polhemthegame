package entities;

import java.util.Collection;
import java.util.Collections;

import math.Vector2;
import math.time.GameTime;

import org.newdawn.slick.Graphics;

import components.physics.AABB;
import components.triggers.actions.IAction;

import entities.interfaces.IEntity;
import entities.interfaces.IObject;
import events.EventHandler;
import events.ObjectEventArgs;
import game.World;

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
        onNotContainsEvent.execute(this, new ObjectEventArgs(u));
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
