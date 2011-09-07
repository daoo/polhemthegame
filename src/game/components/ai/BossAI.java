/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import java.util.Stack;

import main.Locator;
import math.ExMath;
import math.Rectangle;
import math.Vector2;
import math.time.GameTime;


import game.components.ComponentMessages;
import game.components.holdables.Hand;
import game.components.interfaces.ICompUpdate;
import game.components.physics.AABB;
import game.entities.Unit;

public class BossAI implements ICompUpdate {
  private final AABB           body;
  private final Unit          unit;
  private final Hand           hand;
  private final Rectangle      arenaRect;

  private float                shootingStartTime;
  private float                shootingTargetTime;
  private boolean              isShooting;

  private final float          speed;
  private final Stack<Vector2> targets;

  public BossAI(final Unit unit, final Hand hand,
                final Rectangle consts, final AABB body, final float speed) {
    arenaRect = consts;
    this.hand = hand;
    this.body = body;
    this.speed = speed;
    this.unit = unit;

    isShooting = false;

    targets = new Stack<Vector2>();
  }

  private void headFor(final Vector2 target) {
    final Vector2 delta = target.subtract(body.getMin());
    final float dx = target.x - body.getX1();
    final float dy = target.y - body.getY1();

    final float timeX = Math.abs(dx / speed);
    final float timeY = Math.abs(dy / speed);

    body.setVelocity(Vector2.ZERO);
    if ((timeX > 0) || (timeY > 0)) {
      final float max = Math.max(timeX, timeY);
      body.setVelocity(delta.divide(max));
    }
  }

  private Vector2 newTarget() {
    final Vector2 target = new Vector2();
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
  public void update(final GameTime time) {
    if (isShooting) {
      if ((time.getElapsed() - shootingStartTime) >= shootingTargetTime) {
        // We're done here, stop shooting and start walking
        isShooting = false;

        unit.start();
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
          unit.stop();
          hand.stopUse();
          isShooting = true;
          shootingStartTime = time.getElapsed();
          shootingTargetTime = Locator.getRandom().nextFloat(1.0f, 5.0f);
        } else {
          headFor(targets.peek());
        }
      }
    }
  }

  private void kill() {
    isShooting = false;
    hand.stopUse();
    unit.stop();
    targets.clear();
  }

  public void addTarget(final Vector2 target) {
    targets.push(target);
  }

  @Override
  public void reciveMessage(ComponentMessages message) {
    if (message == ComponentMessages.KILLED) {
      kill();
    }
  }
}