/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.actions;

import game.components.ComponentMessage;
import game.components.interfaces.ICompAnim;
import game.entities.Entity;
import game.entities.IEntity;
import game.entities.groups.EntityType;
import game.world.World;
import math.Rectangle;
import math.time.GameTime;

public class SpawnDeathAnim implements IAction {
  private final Rectangle body;
  private final float width, height;
  private final ICompAnim anim;

  public SpawnDeathAnim(Rectangle body, float width, float height, ICompAnim anim) {
    this.body   = body;
    this.width  = width;
    this.height = height;
    this.anim   = anim;
  }

  @Override
  public void execute(GameTime time, World world) {
    IEntity e = new Entity(body.getX1(), body.getY1(), width, height,
                                 EntityType.ANIMATED);
    e.addRenderComponent(anim);
    e.sendMessage(ComponentMessage.END_ANIMATION, null);
    world.add(e);
  }
}
