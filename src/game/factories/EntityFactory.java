/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.graphics.RSheet;
import game.components.holdables.Hand;
import game.components.holdables.weapons.Weapon;
import game.components.misc.EffectsOnDeath;
import game.components.misc.Inventory;
import game.components.misc.Life;
import game.components.misc.PlayerControl;
import game.components.physics.Movement;
import game.components.physics.MovementConstraint;
import game.entities.Entity;
import game.entities.EntityType;
import game.misc.Shop;
import game.triggers.effects.RemoveEntity;
import game.triggers.effects.SpawnAnimationEffect;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.PlayersData;
import loader.data.json.PlayersData.PlayerData;
import loader.data.json.ShopData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ui.hud.PlayerUI;
import ui.hud.infobar.Bar;
import ui.hud.infobar.InfoBar;

public class EntityFactory {
  private static final int BAR_HEIGHT   = 2;
  private static final int BAR_OFFSET_X = 0;
  private static final int BAR_OFFSET_Y = -6;

  private final Rectangle rect;
  private final Graphics statics;

  private final WeaponFactory weaponFactory;
  private final ShopData shopData;
  private final PlayersData playersData;

  public EntityFactory(Rectangle rect, Graphics statics)
      throws ParserException, IOException {
    this.rect = rect;
    this.statics = statics;

    weaponFactory = new WeaponFactory();
    shopData      = CacheTool.getShop(Locator.getCache());
    playersData   = CacheTool.getPlayers(Locator.getCache());
  }

  public Entity makeCreep(float x, float y, float ang, CreepData data)
      throws ParserException, DataException, IOException {
    RSheet walk  = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    Entity e = new Entity(
      x, y,
      data.hitbox.width,
      data.hitbox.height,
      EntityType.CREEP
    );

    Life life = new Life(e, data.hitpoints);

    e.addLogicComponent(new Movement(e, (float) Math.cos(ang) * data.speed,
                                        (float) Math.sin(ang) * data.speed));
    e.addLogicComponent(life);
    e.addRenderComponent(walk);

    InfoBar infoBar = new InfoBar(e,
      data.hitbox.width, BAR_HEIGHT, BAR_OFFSET_X, BAR_OFFSET_Y);
    infoBar.add(new Bar(life, Color.green, Color.red));
    Locator.getUI().addDynamic(infoBar);

    EffectsOnDeath effectsOnDeath = new EffectsOnDeath(e);
    effectsOnDeath.add(new SpawnAnimationEffect(e, death, statics));
    effectsOnDeath.add(new RemoveEntity(e));
    e.addLogicComponent(effectsOnDeath);

    return e;
  }

  private static Color TRANSPARENT = new Color(0, 0, 0, 0);

  public Entity makePlayer(int index)
      throws ParserException, DataException, IOException {
    PlayerData data = playersData.players.get(index);

    RSheet walk  = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    Entity e = new Entity(
      0, 0,
      data.hitbox.width, data.hitbox.height,
      EntityType.PLAYER
    );

    // Create componenets
    Movement mov               = new Movement(e, 0, 0);
    MovementConstraint movCons = new MovementConstraint(e, rect);
    Life life                  = new Life(e, data.hitpoints);
    Hand hand                  = new Hand(e, data.handOffset.x, data.handOffset.y);
    Shop shop                  = new Shop(shopData, weaponFactory);

    Inventory inv = new Inventory(data.startMoney);
    Weapon weapon = weaponFactory.makeWeapon(data.startWeapon);
    inv.addWeapon(weapon);
    hand.grab(weapon);

    PlayerControl control = new PlayerControl(e, mov, inv, shop, hand, data.speed);

    // Add components
    e.addLogicComponent(mov);
    e.addLogicComponent(movCons);
    e.addLogicComponent(life);
    e.addLogicComponent(inv);
    e.addLogicComponent(new EffectsOnDeath(e, new SpawnAnimationEffect(e, death, statics)));

    e.addRenderComponent(hand);
    e.addRenderComponent(walk);

    e.addLogicComponent(control);

    // UI stuff
    InfoBar infoBar = new InfoBar(e,
      data.hitbox.width, BAR_HEIGHT, BAR_OFFSET_X, BAR_OFFSET_Y);
    infoBar.add(new Bar(life, Color.green, Color.red));
    infoBar.add(new Bar(hand, Color.blue, TRANSPARENT));
    Locator.getUI().addDynamic(infoBar);

    Locator.getUI().addStatic(new PlayerUI(0, 0, shop, inv));

    return e;
  }
}
