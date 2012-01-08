/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.ai.BossAI;
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
import game.misc.Shop;
import game.triggers.effects.RemoveEntity;
import game.triggers.effects.SpawnAnimationEffect;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.BossesData.BossData;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.PlayersData;
import loader.data.json.PlayersData.PlayerData;
import loader.data.json.ShopData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;
import math.Vector2;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ui.hud.PlayerUI;
import ui.hud.infobar.Bar;
import ui.hud.infobar.InfoBar;

public class EntityFactory {
  private static final Color TRANSPARENT = new Color(0, 0, 0, 0);
  private static final int BAR_HEIGHT    = 2;
  private static final int BAR_OFFSET_X  = 0;
  private static final int BAR_OFFSET_Y  = -6;

  private final Rectangle worldRect;
  private final Graphics statics;

  private final WeaponFactory weaponFactory;
  private final ShopData shopData;
  private final PlayersData playersData;

  public EntityFactory(Rectangle worldRect, Graphics statics)
      throws ParserException, IOException {
    this.worldRect = worldRect;
    this.statics = statics;

    Rectangle bigRect = new Rectangle(
      -worldRect.getWidth(), -worldRect.getHeight(),
      3 * worldRect.getWidth(), 3 * worldRect.getHeight());

    weaponFactory = new WeaponFactory(bigRect);
    shopData      = CacheTool.getShop(Locator.getCache());
    playersData   = CacheTool.getPlayers(Locator.getCache());
  }

  public Entity makeCreep(float x, float y, float ang, CreepData data)
      throws ParserException, DataException, IOException {
    RSheet walk  = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    Entity e = new Entity(x, y, data.hitbox.width, data.hitbox.height);

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

  public Entity makePlayer(int index)
      throws ParserException, DataException, IOException {
    PlayerData data = playersData.players.get(index);

    RSheet walk  = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    Entity e = new Entity(0, 0, data.hitbox.width, data.hitbox.height);

    // Create components
    Movement mov               = new Movement(e, 0, 0);
    MovementConstraint movCons = new MovementConstraint(e, worldRect);
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
    e.addLogicComponent(new EffectsOnDeath(e,
      new SpawnAnimationEffect(e, death, statics)));

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

  public Entity makeBoss(BossData data)
      throws DataException, ParserException, IOException {
    int middleY = (int) (worldRect.getCenter().y - data.hitbox.height / 2);

    Vector2 initialTarget = new Vector2(
      worldRect.getX1() - data.hitbox.width - data.locationX,
      middleY
    );

    Entity e = new Entity(
      worldRect.getX1(), middleY,
      data.hitbox.width, data.hitbox.height
    );

    Movement mov  = new Movement(e, 0, 0);
    Life life     = new Life(e, data.hitpoints);
    Hand hand     = new Hand(e, data.handOffset.x, data.handOffset.y);
    Weapon weapon = weaponFactory.makeWeapon(data.weapon);
    BossAI ai     = new BossAI(e, mov, hand, worldRect, data.speed, initialTarget);

    e.addLogicComponent(life);
    e.addLogicComponent(mov);
    e.addLogicComponent(ai);
    e.addRenderComponent(hand);

    hand.grab(weapon);

    InfoBar infoBar = new InfoBar(e,
      data.hitbox.width, BAR_HEIGHT, BAR_OFFSET_X, BAR_OFFSET_Y);
    infoBar.add(new Bar(life, Color.green, Color.red));
    Locator.getUI().addDynamic(infoBar);

    return e;
  }
}
