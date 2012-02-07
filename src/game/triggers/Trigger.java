/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.triggers;

import game.types.GameTime;
import game.world.World;

import java.util.ArrayList;
import java.util.Collection;

import util.Node;
import debug.DebugHelper;

/**
 * A trigger that can be looped. More specifically a set of effects that can be
 * executed when some conditions are met.
 */
public class Trigger implements ITrigger {
  private final ArrayList<ICondition> conditions;
  private final ArrayList<IEffect> effects;

  private boolean runAgain;

  private World world;

  /**
   * Create a new trigger with no conditions or effects.
   */
  public Trigger() {
    runAgain = true;

    conditions = new ArrayList<>();
    effects = new ArrayList<>();
  }

  /**
   * Sets the world in which the conditions should be evaluated and the effects
   * executed.
   * @param world the world to use, can not be null
   */
  @Override
  public void setWorld(World world) {
    assert world != null;

    this.world = world;
  }

  /**
   * Tells if this trigger will run it's effects again.
   * @return true if this is a looping trigger, if it isn't it will return true
   *         or false depending on if the trigger have been executed or not.
   */
  @Override
  public boolean runAgain() {
    return runAgain;
  }

  /**
   * Run the effects if the conditions are true. Also takes looping into
   * account.
   * @param time the game time to use for execution and evaluation
   */
  @Override
  public void update(GameTime time) {
    if (runAgain) {
      if (checkConditions(time)) {
        execute(time);

        runAgain = false;
      }
    }
  }

  /**
   * Checks if the conditions are met.
   * Conditions are met if the list of conditions is non-empty and ALL
   * conditions evaluate to true.
   * @param time the game time to use when evaluating conditions
   * @return true if all conditions are met or false otherwise
   */
  private boolean checkConditions(GameTime time) {
    if (conditions.isEmpty()) {
      return false;
    }

    for (ICondition condition : conditions) {
      if (!condition.evaluate(time, world)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Execute all effects with a specific game time.
   * @param time the time to use
   */
  private void execute(GameTime time) {
    for (IEffect effect : effects) {
      effect.execute(time, world);
    }
  }

  /**
   * Adds a condition to the trigger.
   * @param condition the condition to add, can not be null
   */
  @Override
  public void addCondition(ICondition condition) {
    assert condition != null;

    conditions.add(condition);
  }

  /**
   * Add a collection of conditions to the trigger.
   * @param collection the collection to add, can not be null
   */
  @Override
  public void addAllConditions(Collection<? extends ICondition> collection) {
    assert collection != null;

    this.conditions.addAll(collection);
  }

  /**
   * Adds an effect to the trigger.
   * @param effect the effect to add, can not be null
   */
  @Override
  public void addEffect(IEffect effect) {
    assert effect != null;

    effects.add(effect);
  }

  @Override
  public void addAllEffects(Collection<? extends IEffect> collection) {
    assert collection != null;

    effects.addAll(collection);
  }

  @Override
  public String debugString() {
    return "Trigger, runAgain " + Boolean.toString(runAgain);
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());

    parent.nodes.add(DebugHelper.listToNode("Conditions", conditions));
    parent.nodes.add(DebugHelper.listToNode("Effects", effects));

    return parent;
  }
}
