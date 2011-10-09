/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.ComponentMessage;
import game.components.basic.Life;
import game.components.interfaces.ICompAnim;
import game.components.triggers.actions.SpawnRunToEndAnim;
import game.entities.groups.EntityType;

import org.newdawn.slick.Color;

import ui.hud.infobar.Bar;
import ui.hud.infobar.InfoBar;

public class Unit extends Entity {
  private final InfoBar infoBar;
  private final Bar hpBar;

  private final ICompAnim death;

  public Unit(final float x, final float y,
              final float width, final float height,
              final float dx, final float dy,
              final EntityType type, final int maxHP,
              final ICompAnim walk, final ICompAnim death) {
    super(x, y, width, height, dx, dy, type);

    this.death = death;

    addLogicComponent(new Life(maxHP));
    addRenderComponent(walk);

    infoBar = new InfoBar(width, 2, 0, -6);
    hpBar = new Bar(Color.green, Color.red);
    infoBar.add(hpBar);

    addRenderComponent(infoBar);
  }

  protected void addBar(final Bar bar) {
    infoBar.add(bar);
  }

  @Override
  public void sendMessage(final ComponentMessage message, final Object args) {
    if (message == ComponentMessage.KILL) {
      clearComponents();
      addAction(new SpawnRunToEndAnim(
        body.getX1(), body.getY1(),
        death.getTileWidth(), death.getTileHeight(),
        death));
    }

    super.sendMessage(message, args);
  }
}
