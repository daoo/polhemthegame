/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.types;

import game.components.misc.Inventory;
import game.entities.Entity;
import game.misc.Shop;
import ui.hud.infobar.InfoBar;

public class Player {
  public final Entity entity;
  public final Shop shopUI;
  public final InfoBar infoBar;
  public final Inventory inventory;

  public Player(Entity entity, Shop shopUI, Inventory inventory, InfoBar bar) {
    this.entity = entity;
    this.shopUI = shopUI;
    this.inventory = inventory;
    this.infoBar = bar;
  }
}
