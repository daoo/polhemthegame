/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package components.triggers;

import game.World;

import java.util.ArrayList;

import math.time.GameTime;

import components.triggers.actions.IAction;
import components.triggers.conditions.ICondition;

public class Trigger {
  private final ArrayList<ICondition> conditions;
  private final ArrayList<IAction> actions;

  public Trigger() {
    conditions = new ArrayList<ICondition>();
    actions = new ArrayList<IAction>();
  }
  
  public void add(final ICondition c) {
    conditions.add(c);
  }

  public void addAction(final IAction a) {
    actions.add(a);
  }

  public void update(final GameTime time, final World world) {
    if (evaluate(time, world)) {
      for (final IAction a : actions) {
        a.execute(time, world);
      }
    }
  }

  private boolean evaluate(final GameTime time, final World world) {
    for (final ICondition c : conditions) {
      if (!c.evaluate(time, world)) {
        return false;
      }
    }

    return true;
  }
}
