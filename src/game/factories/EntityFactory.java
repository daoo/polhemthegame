/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.graphics.RSheet;
import game.components.graphics.debug.LineToOrigin;
import game.components.graphics.debug.Outliner;
import game.components.holdables.Hand;
import game.components.holdables.weapons.Weapon;
import game.components.life.EffectsOnDeath;
import game.components.life.Life;
import game.components.life.SpawnOnDeath;
import game.components.misc.Inventory;
import game.components.misc.SimpleControl;
import game.components.physics.Movement;
import game.entities.Entity;
import game.entities.EntityType;
import game.entities.IEntity;
import game.triggers.effects.RemoveEntity;

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

    Entity e  = new Entity(x, y, data.hitbox.width, data.hitbox.height, EntityType.CREEP);
    Life life = new Life(data.hitpoints);

    e.addLogicComponent(new Movement((float) Math.cos(ang) * data.speed,
                                     (float) Math.sin(ang) * data.speed));
    e.addLogicComponent(life);
    e.addLogicComponent(new SpawnOnDeath(death));
    e.addLogicComponent(new EffectsOnDeath(new RemoveEntity(e)));
    e.addRenderComponent(walk);

    InfoBar infoBar = new InfoBar(e, data.hitbox.width, 2, 0, -6); // FIXME: Magic Numbers
    infoBar.add(new Bar(life, Color.green, Color.red));
    Locator.getUI().addElement(infoBar);

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

    Life life             = new Life(data.hitpoints);
    Inventory inv         = new Inventory(data.startMoney);
    Hand hand             = new Hand(data.handOffset.x, data.handOffset.y);
    SimpleControl control = new SimpleControl(data.speed);
    Weapon weapon         = CacheTool.getWeapon(Locator.getCache(), data.startWeapon);

    e.addLogicComponent(new Movement(0, 0));
    e.addLogicComponent(life);
    e.addLogicComponent(new SpawnOnDeath(death));
    // TODO: Do I need this one?
    // e.addLogicComponent(new EffectsOnDeath(new RemoveEntity(e)));
    e.addRenderComponent(walk);

    inv.addWeapon(weapon);
    hand.grab(weapon);

    e.addLogicComponent(inv);
    e.addRenderComponent(hand);
    e.addLogicComponent(control);

    InfoBar infoBar = new InfoBar(e, data.hitbox.width, 2, 0, -6); // FIXME: Magic Numbers
    infoBar.add(new Bar(life, Color.green, Color.red));
    infoBar.add(new Bar(hand, Color.blue, TRANSPARENT));
    Locator.getUI().addElement(infoBar);

    e.addRenderComponent(new Outliner(true, true));
    e.addRenderComponent(new LineToOrigin());

    return e;
  }
}
