/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.components.ai;

import game.components.Message;
import game.components.holdables.Hand;
import game.components.interfaces.ILogicComponent;
import game.components.physics.Movement;
import game.entities.IEntity;
import game.pods.GameTime;

import java.util.LinkedList;

import main.Locator;
import math.IRandom;
import math.Rectangle;
import math.Vector2;

public class BossAI implements ILogicComponent {
  private static final int INITIAL_TARGET_COUNT = 2;
  private static final int TARGET_MIN_COUNT = 0;
  private static final int TARGET_MAX_COUNT = 3;

  private static final int TARGET_X_DIFFERENCE = 10;
  private static final int TARGET_Y_DIFFERENCE = 50;

  private static final float SHOOTING_TIME_MIN = 1.0f;
  private static final float SHOOTING_TIME_MAX = 1.0f;

  private final IEntity entity;
  private final Rectangle body;
  private final Movement movement;
  private final Hand hand;
  private final Rectangle movementRect;

  private final float speed;

  private IBossState state;

  public BossAI(IEntity entity, Movement movement, Hand hand,
                Rectangle arenaRect, float speed, Vector2 initialTarget) {
    this.entity   = entity;
    this.hand     = hand;
    this.movement = movement;
    this.body     = entity.getBody();
    this.speed    = speed;

    this.movementRect = new Rectangle(arenaRect.getX1(), arenaRect.getY1(),
                                      arenaRect.getX2() - body.getWidth(),
                                      arenaRect.getY2() - body.getHeight());

    LinkedList<Vector2> targets = new LinkedList<>();
    targets.add(initialTarget);
    for (int i = 0; i < INITIAL_TARGET_COUNT; ++i) {
      targets.add(newTarget());
    }

    state = new Walking(entity, hand, speed, movement, targets);
  }

  private Vector2 newTarget() {
    IRandom rnd = Locator.getRandom();

    float targetX = rnd.nextFloat(
      body.getX1() - TARGET_X_DIFFERENCE,
      body.getX1() + TARGET_X_DIFFERENCE);
    float targetY = 0;

    if (body.getY1() <= movementRect.getCenter().y) {
      // Go to the lower part
      targetY = rnd.nextFloat(
        body.getY1() + TARGET_Y_DIFFERENCE, movementRect.getY2());
    } else {
      // Go to the upper part
      targetY = rnd.nextFloat(
        movementRect.getY1(), body.getY1() - TARGET_Y_DIFFERENCE);
    }

    return new Vector2(targetX, targetY);
  }

  @Override
  public void update(GameTime time) {
    if (state.isFinished()) {
      // Go to next state

      if (state.getNextState() == BossState.WALKING) {
        LinkedList<Vector2> targets = new LinkedList<>();

        int targetCount = Locator.getRandom().nextInt(
          TARGET_MIN_COUNT, TARGET_MAX_COUNT + 1);
        for (int i = 0; i < targetCount; ++i) {
          targets.add(newTarget());
        }

        state = new Walking(entity, hand, speed, movement, targets);
      } else if (state.getNextState() == BossState.SHOOTING) {
        float shootingTime = Locator.getRandom().nextFloat(
          SHOOTING_TIME_MIN, SHOOTING_TIME_MAX);
        state = new Shooting(entity, hand, shootingTime);
      }

      state.start(time);
    }

    state.update(time);
  }

  @Override
  public void reciveMessage(Message message, Object args) {
    // Do nothing
  }
}
