/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.factories;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.io.IOException;
import java.util.Arrays;

import game.components.ai.BossAI;
import game.components.graphics.AnimatedSheet;
import game.components.holdables.Hand;
import game.components.holdables.weapons.Weapon;
import game.components.misc.EffectsOnDeath;
import game.components.misc.Inventory;
import game.components.misc.Life;
import game.components.misc.PlayerControl;
import game.components.physics.Movement;
import game.components.physics.MovementConstraint;
import game.config.Binds;
import game.entities.EntityImpl;
import game.entities.Player;
import game.entities.Unit;
import game.misc.CacheTool;
import game.misc.Locator;
import game.misc.Shop;
import game.triggers.effects.RemoveEntityEffect;
import game.triggers.effects.spawn.SpawnAnimationEffect;
import game.types.Orientation;
import game.ui.hud.infobar.Bar;
import game.ui.hud.infobar.InfoBar;
import loader.data.json.PlayersData;
import loader.data.json.ShopData;
import loader.data.json.types.BossData;
import loader.data.json.types.CreepData;
import loader.data.json.types.PlayerData;
import loader.data.json.types.UnitData;
import loader.parser.ParserException;
import math.Aabb;
import math.Rectangle;
import math.Vector2;


public class EntityFactory {
  private static final Orientation PLAYER_ORIENTATION = Orientation.RIGHT;
  private static final Orientation BOSS_ORIENTATION = Orientation.LEFT;
  private static final Orientation CREEP_ORIENTATION = Orientation.LEFT;

  private static final Color TRANSPARENT = new Color(0, 0, 0, 0);
  private static final int BAR_HEIGHT = 2;
  private static final int BAR_OFFSET_X = 0;
  private static final int BAR_OFFSET_Y = -6;

  private final Aabb mWorldBoundary;
  private final Graphics mStatics;

  private final WeaponFactory mWeaponFactory;
  private final ShopData mShopData;
  private final PlayersData mPlayersData;

  public EntityFactory(Aabb boundary, Graphics statics) throws ParserException, IOException {
    mWorldBoundary = boundary;
    mStatics = statics;

    Aabb projectileBox = new Aabb(new Vector2(-boundary.getSize().x, -boundary.getSize().y),
        new Rectangle(3 * boundary.getSize().x, 3 * boundary.getSize().y));

    mWeaponFactory = new WeaponFactory(projectileBox, statics);
    mShopData = CacheTool.getShop(Locator.getCache());
    mPlayersData = CacheTool.getPlayers(Locator.getCache());
  }

  private Unit makeUnit(
      float x, float y, float dx, float dy, Orientation orientation, UnitData data) throws
      ParserException, IOException {
    AnimatedSheet walk = CacheTool
        .getAnimatedSheet(Locator.getCache(), orientation, 0, data.sprites.get("walk"));
    AnimatedSheet death = CacheTool
        .getAnimatedSheet(Locator.getCache(), orientation, 0, data.sprites.get("death"));

    EntityImpl entity = new EntityImpl(x, y, data.hitbox.width, data.hitbox.height);
    Movement mov = new Movement(entity, dx, dy);
    Life life = new Life(entity, data.hitpoints);

    entity.addLogicComponent(mov);
    entity.addLogicComponent(life);
    entity.addRenderComponent(walk);

    entity.addLogicComponent(new EffectsOnDeath(entity, Arrays
        .asList(new SpawnAnimationEffect(entity, death, mStatics), new RemoveEntityEffect(entity))));

    InfoBar infoBar = new InfoBar(entity, data.hitbox.width, BAR_HEIGHT, BAR_OFFSET_X,
        BAR_OFFSET_Y);
    infoBar.add(new Bar(life, Color.green, Color.red));

    return new Unit(entity, mov, life, infoBar);
  }

  public Unit makeCreep(float x, float y, CreepData data) throws ParserException, IOException {
    return makeUnit(x, y, -data.unit.speed, 0, CREEP_ORIENTATION, data.unit);
  }

  public Player makePlayer(String playerName, Binds binds) throws ParserException, IOException {
    PlayerData data = mPlayersData.getPlayer(playerName);

    Unit unit = makeUnit(0, 0, 0, 0, PLAYER_ORIENTATION, data.unit);

    Hand hand = new Hand(unit.entity, PLAYER_ORIENTATION,
        new Vector2(data.handOffset.x, data.handOffset.y));
    Shop shop = new Shop(mShopData, PLAYER_ORIENTATION, mWeaponFactory);

    Inventory inv = new Inventory(data.startMoney);
    Weapon weapon = mWeaponFactory.makeWeapon(data.startWeapon, PLAYER_ORIENTATION);
    inv.addWeapon(weapon);
    hand.grab(weapon);

    // Add components
    unit.entity.addLogicComponent(new MovementConstraint(unit.entity, mWorldBoundary));
    unit.entity.addLogicComponent(inv);
    unit.entity.addRenderComponent(hand);
    unit.entity.addLogicComponent(
        new PlayerControl(unit.entity, unit.movement, inv, shop, hand, data.unit.speed, binds));

    // UI stuff
    unit.infoBar.add(new Bar(hand, Color.blue, TRANSPARENT));

    return new Player(unit.entity, shop, inv, unit.infoBar);
  }

  public Unit makeBoss(BossData data) throws ParserException, IOException {
    int middleY = (int) (mWorldBoundary.getCenter().y - data.unit.hitbox.height / 2);

    Vector2 initialTarget = new Vector2(
        mWorldBoundary.getMax().x + data.unit.hitbox.width - data.locationX,
        middleY);

    Unit unit = makeUnit(mWorldBoundary.getMax().x, middleY, 0, 0, BOSS_ORIENTATION, data.unit);

    Hand hand = new Hand(unit.entity, BOSS_ORIENTATION,
        new Vector2(data.handOffset.x, data.handOffset.y));
    Weapon weapon = mWeaponFactory.makeWeapon(data.weapon, BOSS_ORIENTATION);
    BossAI ai = new BossAI(unit.entity, unit.movement, hand, mWorldBoundary, data.locationX,
        data.unit.speed, initialTarget);

    unit.entity.addLogicComponent(ai);
    unit.entity.addRenderComponent(hand);

    hand.grab(weapon);

    return unit;
  }
}
