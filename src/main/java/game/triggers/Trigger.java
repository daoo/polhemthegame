/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.triggers;

import java.util.ArrayList;
import java.util.Collection;

import debug.DebugHelper;
import game.course.World;
import game.types.GameTime;
import util.Node;

/**
 * A trigger that can be looped. More specifically a set of effects that can be
 * executed when some conditions are met.
 */
public class Trigger {
  private final ArrayList<Condition> mConditions;
  private final ArrayList<Effect> mEffects;

  private boolean mRunAgain;

  private World mWorld;

  /**
   * Create a new trigger with no conditions or effects.
   */
  public Trigger() {
    mRunAgain = true;

    mConditions = new ArrayList<>();
    mEffects = new ArrayList<>();
  }

  /**
   * Sets the world in which the conditions should be evaluated and the effects
   * executed.
   *
   * @param world the world to use, can not be null
   */
  public void setWorld(World world) {
    assert world != null;
    mWorld = world;
  }

  /**
   * Tells if this trigger will run it's effects again.
   *
   * @return true if this is a looping trigger, if it isn't it will return true
   * or false depending on if the trigger have been executed or not.
   */
  public boolean runAgain() {
    return mRunAgain;
  }

  /**
   * Run the effects if the conditions are true. Also takes looping into
   * account.
   *
   * @param time the game time to use for execution and evaluation
   */
  public void update(GameTime time) {
    if (mRunAgain) {
      if (checkConditions(time)) {
        execute(time);

        mRunAgain = false;
      }
    }
  }

  /**
   * Checks if the conditions are met.
   * Conditions are met if the list of conditions is non-empty and ALL
   * conditions evaluate to true.
   *
   * @param time the game time to use when evaluating conditions
   * @return true if all conditions are met or false otherwise
   */
  private boolean checkConditions(GameTime time) {
    if (mConditions.isEmpty()) {
      return false;
    }

    for (Condition condition : mConditions) {
      if (!condition.evaluate(time, mWorld)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Execute all effects with a specific game time.
   *
   * @param time the time to use
   */
  private void execute(GameTime time) {
    for (Effect effect : mEffects) {
      effect.execute(time, mWorld);
    }
  }

  /**
   * Adds a condition to the trigger.
   *
   * @param condition the condition to add, can not be null
   */
  public void addCondition(Condition condition) {
    assert condition != null;

    mConditions.add(condition);
  }

  /**
   * Adds an effect to the trigger.
   *
   * @param effect the effect to add, can not be null
   */
  public void addEffect(Effect effect) {
    assert effect != null;

    mEffects.add(effect);
  }

  public void addAllEffects(Collection<? extends Effect> collection) {
    assert collection != null;

    mEffects.addAll(collection);
  }

  public String debugString() {
    return "Trigger, runAgain " + Boolean.toString(mRunAgain);
  }

  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());

    parent.nodes.add(DebugHelper.listToNode("Conditions", mConditions));
    parent.nodes.add(DebugHelper.listToNode("Effects", mEffects));

    return parent;
  }
}
