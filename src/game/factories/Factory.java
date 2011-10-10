/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import game.CacheTool;
import game.components.ComponentType;
import game.components.basic.Inventory;
import game.components.basic.Life;
import game.components.basic.SimpleControl;
import game.components.graphics.RSheet;
import game.components.holdables.Hand;
import game.components.holdables.weapons.Weapon;
import game.components.interfaces.ICompAnim;
import game.components.misc.SpawnOnDeath;
import game.entities.Entity;
import game.entities.InvisibleRectangle;
import game.entities.Players;
import game.entities.groups.EntityType;
import game.entities.interfaces.IEntity;
import game.events.impl.DamagePlayerEvent;
import game.events.impl.KillEvent;
import game.world.World;

import java.io.IOException;

import loader.data.DataException;
import loader.data.json.CreepsData.CreepData;
import loader.data.json.PlayersData.PlayerData;
import loader.parser.ParserException;
import main.Locator;
import math.Rectangle;

import org.newdawn.slick.Color;

import ui.hud.infobar.Bar;
import ui.hud.infobar.InfoBar;


public class Factory {

  public static World makeWorld(final Rectangle rectWorld, final Players players) {
    /**
     * The layout of rectangles:
     * |-----------------------------|
     * | bigRect                     |
     * |                             |
     * |                             |
     * |---------|---------|         |
     * | cKiller |smallRect|         |
     * |---------|---------|         |
     * |                             |
     * |                             |
     * |                             |
     * |-----------------------------|
     */

    final World w = new World();

    final InvisibleRectangle rectBig =
      new InvisibleRectangle(-rectWorld.getWidth(), -rectWorld.getHeight(),
                             3 * rectWorld.getWidth(), 3 * rectWorld.getHeight());
    rectBig.onNotContainsEvent.add(new KillEvent());
    w.add(rectBig);

    // TODO: Restrict players

    final InvisibleRectangle rectCreepKiller =
      new InvisibleRectangle(-rectWorld.getWidth(), 0,
                             rectWorld.getWidth(), rectWorld.getHeight());
    rectCreepKiller.onContainsEvent.add(new KillEvent());
    rectCreepKiller.onContainsEvent.add(new DamagePlayerEvent());
    w.add(rectCreepKiller);

    for (final IEntity p : players) {
      w.add(p);
    }

    return w;
  }

  public static IEntity makeCreep(final float x, final float y, final float ang,
                                  final CreepData data)
    throws ParserException, DataException, IOException {
    final RSheet walk = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    final RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    return Factory.makeUnit(x, y, data.hitbox.width, data.hitbox.height,
                            (float) Math.cos(ang) * data.speed,
                            (float) Math.sin(ang) * data.speed,
                            EntityType.CREEP, data.hitpoints,
                            walk, death);
  }

  public static IEntity makePlayer(final float x, final float y, final PlayerData data)
    throws ParserException, DataException, IOException {
    final RSheet walk = CacheTool.getRSheet(Locator.getCache(), data.getSheet("walk"));
    final RSheet death = CacheTool.getRSheet(Locator.getCache(), data.getSheet("death"));

    final IEntity e = Factory.makeUnit(x, y, data.hitbox.width, data.hitbox.height,
                                       0, 0, EntityType.PLAYER, data.hitpoints,
                                       walk, death);

    final Inventory inv       = new Inventory(data.startMoney);
    final Hand hand      = new Hand(data.handOffset.x, data.handOffset.y);
    final Bar weaponBar = new Bar(hand, Color.blue, new Color(0, 0, 0, 0));

    e.addRenderComponent(hand);
    ((InfoBar)e.getComponent(ComponentType.INFO_BAR)).add(weaponBar);
    e.addLogicComponent(new SimpleControl(data.speed));

    final Weapon weapon = CacheTool.getWeapon(Locator.getCache(), data.startWeapon);
    inv.add(weapon);
    hand.grab(weapon);

    return e;
  }
  
  private static IEntity makeUnit(final float x, final float y,
                                  final float width, final float height,
                                  final float dx, final float dy,
                                  final EntityType type, final float maxHP,
                                  final ICompAnim walk, final ICompAnim death) {
    final Entity e = new Entity(x, y, width, height, dx, dy, type);

    final Life life = new Life(maxHP);
    
    e.addLogicComponent(life);
    e.addRenderComponent(walk);

    final InfoBar infoBar = new InfoBar(width, 2, 0, -6);
    final Bar hpBar = new Bar(life, Color.green, Color.red);
    infoBar.add(hpBar);

    e.addRenderComponent(infoBar);
    
    e.addLogicComponent(new SpawnOnDeath(death));
    
    return e;
  }
}
