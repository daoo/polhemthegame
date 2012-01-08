/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package tests;

import game.components.graphics.debug.Outliner;
import game.components.graphics.debug.SolidQuad;
import game.components.physics.Movement;
import game.entities.Entity;
import game.pods.GameTime;
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

  private static final int RECTANGLE_X = 1420;
  private static final int RECTANGLE_Y = 340;
  private static final int RECTANGLE_W = 400;
  private static final int RECTANGLE_H = 400;

  private static final int PROJECTILE_X  = 100;
  private static final int PROJECTILE_Y  = 440;
  private static final int PROJECTILE_W  = 200;
  private static final int PROJECTILE_H  = 200;
  private static final int PROJECTILE_DX = 400;
  private static final int PROJECTILE_DY = 0;

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
  private final Entity obj1, obj2;
  private final Movement mov2;

  public CollisionTester() {
    super("Collision Tester");

    elapsed = 0;
    world = new World();

    obj1 = new Entity(RECTANGLE_X, RECTANGLE_Y, RECTANGLE_W, RECTANGLE_H);
    obj1.addRenderComponent(new SolidQuad(Color.white, RECTANGLE_W, RECTANGLE_H));

    world.addUnit(obj1);

    // Make projectile
    obj2 = new Entity(PROJECTILE_X, PROJECTILE_Y, PROJECTILE_W, PROJECTILE_H);
    mov2 = new Movement(obj2, PROJECTILE_DX, PROJECTILE_DY);

    obj2.addLogicComponent(mov2);
    obj2.addRenderComponent(new SolidQuad(Color.white, PROJECTILE_W, PROJECTILE_H));
    obj2.addRenderComponent(new Outliner(obj2, mov2, true, true));

    world.addProjectile(obj2);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setTargetFrameRate(60);
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    world.render(g);

    if (CollisionHelper.sweepCollisionTest(obj2.getBody(), mov2.getVelocity(),
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
        mov2.addVelocity(0, -s);
      else if (Keyboard.isKeyDown(Keyboard.KEY_S))
        mov2.addVelocity(0, s);
      else if (Keyboard.isKeyDown(Keyboard.KEY_A))
        mov2.addVelocity(-s, 0);
      else if (Keyboard.isKeyDown(Keyboard.KEY_D))
        mov2.addVelocity(s, 0);
    }

    float frameLength = delta / 1000.0f;
    elapsed += frameLength;
    world.update(new GameTime(frameLength, elapsed));
  }
}
