/*
 * Copyright (c) 2010-2011 Daniel Oom, see license.txt for more info.
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
import game.components.misc.PlayerControl;
import game.components.physics.Movement;
import game.entities.Entity;
import game.entities.EntityType;
import game.entities.IEntity;
import game.triggers.effects.SpawnAnimationEffect;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.PlayersData.PlayerData;
import loader.parser.ParserException;
import main.Locator;

import org.newdawn.slick.Color;

import ui.hud.infobar.Bar;
import ui.hud.infobar.InfoBar;

public class EntityFactory {
  public static IEntity makeCreep(float x, float y, float ang, CreepData data)
      throws ParserException, DataException, IOException {
    RSheet walk  = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    Entity e  = new Entity(
      x, y,
      data.hitbox.width,
      data.hitbox.height,
      EntityType.CREEP
    );

    Life life = new Life(data.hitpoints);

    e.addLogicComponent(new Movement((float) Math.cos(ang) * data.speed,
                                     (float) Math.sin(ang) * data.speed));
    e.addLogicComponent(life);
    e.addRenderComponent(walk);

    InfoBar infoBar = new InfoBar(e, data.hitbox.width, 2, 0, -6); // FIXME: Magic Numbers
    infoBar.add(new Bar(life, Color.green, Color.red));
    Locator.getUI().addDynamic(infoBar);

    EffectsOnDeath effectsOnDeath = new EffectsOnDeath();
    effectsOnDeath.add(new SpawnAnimationEffect(e, death));
    e.addLogicComponent(effectsOnDeath);

    return e;
  }

  private static Color TRANSPARENT = new Color(0, 0, 0, 0);

  public static IEntity makePlayer(PlayerData data)
      throws ParserException, DataException, IOException {
    RSheet walk  = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    Entity e = new Entity(
      0, 0,
      data.hitbox.width, data.hitbox.height,
      EntityType.PLAYER
    );

    e.addLogicComponent(new Movement(0, 0));
    e.addLogicComponent(new PlayerControl(data.speed));

    Life life = new Life(data.hitpoints);
    e.addLogicComponent(life);

    Inventory inv = new Inventory(data.startMoney);
    Weapon weapon = CacheTool.getWeapon(Locator.getCache(), data.startWeapon);
    inv.addWeapon(weapon);
    e.addLogicComponent(inv);

    Hand hand     = new Hand(data.handOffset.x, data.handOffset.y);
    hand.grab(weapon);
    e.addRenderComponent(hand);

    e.addLogicComponent(new EffectsOnDeath(new SpawnAnimationEffect(e, death)));

    e.addRenderComponent(walk);

    InfoBar infoBar = new InfoBar(e, data.hitbox.width, 2, 0, -6); // FIXME: Magic Numbers
    infoBar.add(new Bar(life, Color.green, Color.red));
    infoBar.add(new Bar(hand, Color.blue, TRANSPARENT));
    Locator.getUI().addDynamic(infoBar);

    e.addRenderComponent(new Outliner(true, true));

    return e;
  }
}
