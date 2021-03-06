/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import game.components.misc.Inventory;
import game.misc.Shop;
import game.ui.hud.infobar.InfoBar;

@SuppressWarnings("InstanceVariableNamingConvention")
public class Player {
  public final EntityImpl entity;
  public final Shop shopUI;
  public final InfoBar infoBar;
  public final Inventory inventory;

  public Player(EntityImpl entity, Shop shopUI, Inventory inventory, InfoBar bar) {
    this.entity = entity;
    this.shopUI = shopUI;
    this.inventory = inventory;
    this.infoBar = bar;
  }
}
