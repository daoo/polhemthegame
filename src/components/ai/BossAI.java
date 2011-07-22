/*
 * Copyright (c) 2009-2011 Daniel Oom, see licence.txt for more info.
 */

package components.ai;

import java.util.Stack;

import other.GameTime;
import basics.ExMath;
import basics.Rectangle;
import basics.Vector2;

import components.ICompUpdate;
import components.basic.Walker;
import components.holdables.Hand;
import components.physics.AABB;

public class BossAI implements ICompUpdate {
  private final AABB           body;
  private final Walker         walker;
  private final Hand           hand;
  private final Rectangle      arenaRect;

  private float                shootingStartTime;
  private float                shootingTargetTime;
  private boolean              isShooting;

  private final float          speed;
  private final Stack<Vector2> targets;

  public BossAI(final Walker walker, final Hand hand,
                final Rectangle consts, final AABB body, final float speed) {
    arenaRect = consts;
    this.hand = hand;
    this.body = body;
    this.speed = speed;
    this.walker = walker;

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
    target.x = ExMath.random(body.getX1() - 10, body.getX1() + 10);
    if (body.getY1() <= (arenaRect.getHeight() / 2)) {
      target.y = ExMath.random((int) body.getY1() + 50,
                               arenaRect.getMax().y - body.getHeight());
    } else {
      target.y = ExMath.random(arenaRect.getMin().y,
                               (int) body.getY1() - 50);
    }

    return target;
  }

  @Override
  public void update(final GameTime time) {
    if (isShooting) {
      if ((time.getElapsed() - shootingStartTime) >= shootingTargetTime) {
        // We're done here, stop shooting and start walking
        isShooting = false;

        walker.startWalking(time);
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
          walker.stopWalking();
          hand.toggleUse();
          isShooting = true;
          shootingStartTime = time.getElapsed();
          shootingTargetTime = ExMath.random(1.0f, 5.0f);
        } else {
          headFor(targets.peek());
        }
      }
    }
  }

  public void kill() {
    isShooting = false;
    hand.stopUse();
    walker.stopWalking();
    targets.clear();
  }

  public void addTarget(final Vector2 target) {
    targets.push(target);
  }
}
