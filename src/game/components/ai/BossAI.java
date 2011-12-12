/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.ComponentMessage;
import game.components.ComponentType;
import game.components.holdables.Hand;
import game.components.interfaces.ILogicComponent;
import game.entities.IEntity;
import game.time.GameTime;

import java.util.Stack;

import main.Locator;
import math.ExMath;
import math.Rectangle;
import math.Vector2;

public class BossAI implements ILogicComponent {
  private final Rectangle      body;
  private final IEntity        unit;
  private final Hand           hand;
  private final Rectangle      arenaRect;

  private float                shootingStartTime;
  private float                shootingTargetTime;
  private boolean              isShooting;

  private final float          speed;
  private final Stack<Vector2> targets;

  public BossAI(IEntity unit, Hand hand,
                Rectangle consts, Rectangle body, float speed) {
    arenaRect = consts;
    this.hand = hand;
    this.body = body;
    this.speed = speed;
    this.unit = unit;

    isShooting = false;

    targets = new Stack<>();
  }

  private void headFor(Vector2 target) {
    Vector2 delta = target.subtract(body.getMin());
    float dx = target.x - body.getX1();
    float dy = target.y - body.getY1();

    float timeX = Math.abs(dx / speed);
    float timeY = Math.abs(dy / speed);

    // FIXME: body.setVelocity(Vector2.ZERO);
    if ((timeX > 0) || (timeY > 0)) {
      float max = Math.max(timeX, timeY);
      // FIXME: body.setVelocity(delta.divide(max));
    }
  }

  private Vector2 newTarget() {
    Vector2 target = new Vector2();
    target.x = Locator.getRandom().nextFloat(
      body.getX1() - 10,
      body.getX1() + 10);
    if (body.getY1() <= (arenaRect.getHeight() / 2)) {
      target.y = Locator.getRandom().nextFloat(
        body.getY1() + 50,
        arenaRect.getMax().y - body.getHeight());
    } else {
      target.y = Locator.getRandom().nextFloat(
        arenaRect.getMin().y,
        body.getY1() - 50);
    }

    return target;
  }

  @Override
  public void update(GameTime time) {
    if (isShooting) {
      if ((time.elapsed - shootingStartTime) >= shootingTargetTime) {
        // We're done here, stop shooting and start walking
        isShooting = false;

        unit.sendMessage(ComponentMessage.START_ANIMATION, null);
        targets.push(newTarget());
        headFor(targets.peek());
      }
    } else {
      if (targets.empty()) {
        // Add more targets
        targets.push(newTarget());
        headFor(targets.peek());
      } else if (ExMath.inRange(targets.peek().distance(body.getMin()), -10, 10)) {
        // Target reached
        targets.pop();
        if (targets.empty()) {
          // No more targets, start shooting
          unit.sendMessage(ComponentMessage.STOP_ANIMATION, null);
          hand.stopUse();
          isShooting = true;
          shootingStartTime = time.elapsed;
          shootingTargetTime = Locator.getRandom().nextFloat(1.0f, 5.0f);
        } else {
          headFor(targets.peek());
        }
      }
    }
  }

  public void addTarget(Vector2 target) {
    targets.push(target);
  }

  @Override
  public void reciveMessage(ComponentMessage message, Object args) {
    // Do nothing
  }

  @Override
  public ComponentType getComponentType() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
