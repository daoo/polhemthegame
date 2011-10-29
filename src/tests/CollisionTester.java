/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package tests;

import game.components.ComponentType;
import game.components.graphics.debug.Outliner;
import game.components.graphics.debug.SolidQuad;
import game.components.physics.Movement;
import game.entities.Entity;
import game.entities.EntityType;
import game.entities.IEntity;
import game.time.GameTime;
import game.world.World;
import math.CollisionHelper;
import math.Rectangle;
import math.Vector2;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class CollisionTester extends BasicGame {
  private static final boolean FULLSCREEN = false;
  private static final int WIDTH          = 1920;
  private static final int HEIGHT         = 1080;

  public static void main(String[] args) {
    try {
      AppGameContainer app = new AppGameContainer(new CollisionTester());

      app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
      app.start();
    } catch (SlickException ex) {
      ex.printStackTrace();
    }
  }

  private float elapsed;

  private final World world;
  private final IEntity obj1, obj2;

  private IEntity makeStaticRectangle(int x, int y, int w, int h) {
    IEntity obj = new Entity(x, y, w, h, EntityType.CREEP);
    obj.addRenderComponent(new SolidQuad(Color.white, w, h));

    return obj;
  }

  private IEntity makeProjectile(int x, int y, int w, int h, int dx, int dy) {
    IEntity obj = new Entity(x, y, w, w, EntityType.PROJECTILE);

    obj.addLogicComponent(new Movement(dx, dy));

    obj.addRenderComponent(new SolidQuad(Color.white, w, h));
    obj.addRenderComponent(new Outliner(true, true));

    return obj;
  }

  public CollisionTester() {
    super("Collision Tester");

    elapsed = 0;
    world = new World();

    obj1 = makeStaticRectangle(1420, 340, 400, 400);
    world.addLast(obj1);

    obj2 = makeProjectile(100, 440, 200, 200, 400, 0);
    world.addLast(obj2);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setTargetFrameRate(60);
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    world.render(g);

    if (CollisionHelper.sweepCollisionTest(obj2.getBody(),
         ((Movement)obj2.getComponent(ComponentType.MOVEMENT)).getVelocity(),
         obj1.getBody(), 1)) {
      g.drawString("COLLISION!", 100, 10);
    }
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      container.exit();
    } else {
      Rectangle body = obj2.getBody();
      Movement mov = (Movement) obj2.getComponent(ComponentType.MOVEMENT);
      float s = 100.0f / delta;

      if (Keyboard.isKeyDown(Keyboard.KEY_UP))
        body.addPosition(new Vector2(0, -s));
      else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        body.addPosition(new Vector2(0, s));
      else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        body.addPosition(new Vector2(-s, 0));
      else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        body.addPosition(new Vector2(s, 0));

      else if (Keyboard.isKeyDown(Keyboard.KEY_W))
        mov.getVelocity().addSelf(0, -s);
      else if (Keyboard.isKeyDown(Keyboard.KEY_S))
        mov.getVelocity().addSelf(0, s);
      else if (Keyboard.isKeyDown(Keyboard.KEY_A))
        mov.getVelocity().addSelf(-s, 0);
      else if (Keyboard.isKeyDown(Keyboard.KEY_D))
        mov.getVelocity().addSelf(s, 0);
    }

    float frameLength = delta / 1000.0f;
    elapsed += frameLength;
    world.update(new GameTime(frameLength, elapsed));
  }
}
