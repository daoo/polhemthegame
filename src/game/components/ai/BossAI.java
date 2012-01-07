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
import math.Rectangle;
import math.Vector2;

public class BossAI implements ILogicComponent {
  private final IEntity entity;
  private final Rectangle body;
  private final Movement movement;
  private final Hand hand;
  private final Rectangle arenaRect;

  private final float speed;

  private IBossState state;

  public BossAI(IEntity entity, Movement movement, Hand hand,
                Rectangle arenaRect, float speed, Vector2 initialTarget) {
    this.entity    = entity;
    this.hand      = hand;
    this.movement  = movement;
    this.body      = entity.getBody();
    this.arenaRect = arenaRect;
    this.speed     = speed;

    LinkedList<Vector2> targets = new LinkedList<>();
    targets.add(initialTarget);
    // FIXME: Magic numbers
    for (int i = 0; i < 2; ++i) {
      targets.add(newTarget());
    }
    state = new Walking(entity, hand, speed, movement, targets);
  }

  private Vector2 newTarget() {
    // FIXME: Magic numbers
    float targetX = Locator.getRandom().nextFloat(
      body.getX1() - 10, body.getX1() + 10);
    float targetY = 0;
    if (body.getY1() <= (arenaRect.getCenter().y)) {
      targetY = Locator.getRandom().nextFloat(
        body.getY1() + 50,
        arenaRect.getMax().y - body.getHeight());
    } else {
      targetY = Locator.getRandom().nextFloat(
        arenaRect.getMin().y,
        body.getY1() - 50);
    }

    return new Vector2(targetX, targetY);
  }

  @Override
  public void update(GameTime time) {
    if (state.isFinished()) {
      // Go to next state

      if (state.getNextState() == BossState.WALKING) {
        LinkedList<Vector2> targets = new LinkedList<>();
        // FIXME: Magic numbers
        for (int i = 0; i < 2; ++i) {
          targets.add(newTarget());
        }

        state = new Walking(entity, hand, speed, movement, targets);
      } else if (state.getNextState() == BossState.SHOOTING) {
        state = new Shooting(entity, hand,
          Locator.getRandom().nextFloat(1.0f, 5.0f));
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
