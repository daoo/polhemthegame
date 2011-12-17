/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.graphics.RSheet;
import game.components.graphics.debug.Outliner;
import game.components.holdables.Hand;
import game.components.holdables.weapons.Weapon;
import game.components.life.EffectsOnDeath;
import game.components.life.Life;
import game.components.misc.Inventory;
import game.components.misc.MovementConstraint;
import game.components.misc.PlayerControl;
import game.components.physics.Movement;
import game.entities.Entity;
import game.entities.EntityType;
import game.entities.IEntity;
import game.misc.Shop;
import game.triggers.effects.SpawnAnimationEffect;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.PlayersData.PlayerData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Color;

import ui.hud.PlayerUI;
import ui.hud.infobar.Bar;
import ui.hud.infobar.InfoBar;

public class EntityFactory {
  public static IEntity makeCreep(float x, float y, float ang, CreepData data)
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

    InfoBar infoBar = new InfoBar(e, data.hitbox.width, 2, 0, -6); // FIXME: Magic Numbers
    infoBar.add(new Bar(life, Color.green, Color.red));
    Locator.getUI().addDynamic(infoBar);

    EffectsOnDeath effectsOnDeath = new EffectsOnDeath(e);
    effectsOnDeath.add(new SpawnAnimationEffect(e, death));
    e.addLogicComponent(effectsOnDeath);

    return e;
  }

  private static Color TRANSPARENT = new Color(0, 0, 0, 0);

  public static IEntity makePlayer(PlayerData data, Rectangle rect)
      throws ParserException, DataException, IOException {
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
    Shop shop                  = new Shop(CacheTool.getShop(Locator.getCache()));

    Inventory inv = new Inventory(data.startMoney);
    Weapon weapon = MiscFactory.makeWeapon(
      CacheTool.getWeaponData(Locator.getCache(), data.startWeapon));
    inv.addWeapon(weapon);
    hand.grab(weapon);

    PlayerControl control = new PlayerControl(e, mov, inv, shop, hand, data.speed);

    // Add components
    e.addLogicComponent(mov);
    e.addLogicComponent(movCons);
    e.addLogicComponent(life);
    e.addLogicComponent(inv);
    e.addLogicComponent(new EffectsOnDeath(e, new SpawnAnimationEffect(e, death)));

    e.addRenderComponent(hand);
    e.addRenderComponent(walk);

    e.addLogicComponent(control);

    // UI stuff
    InfoBar infoBar = new InfoBar(e, data.hitbox.width, 2, 0, -6); // FIXME: Magic Numbers
    infoBar.add(new Bar(life, Color.green, Color.red));
    infoBar.add(new Bar(hand, Color.blue, TRANSPARENT));
    Locator.getUI().addDynamic(infoBar);

    Locator.getUI().addStatic(new PlayerUI(0, 0, shop, inv));

    return e;
  }
}
