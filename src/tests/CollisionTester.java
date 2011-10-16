package tests;

import game.components.graphics.OutlineNext;
import game.components.graphics.SolidQuad;
import game.entities.Entity;
import game.entities.IEntity;
import game.entities.groups.EntityType;
import game.world.World;
import math.time.GameTime;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class CollisionTester extends BasicGame {
  private static final boolean FULLSCREEN = false;
  private static final int WIDTH = 1920;
  private static final int HEIGHT = 1080;

  public static void main(final String[] args) {
    try {
      final AppGameContainer app = new AppGameContainer(new CollisionTester());

      app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
      app.start();
    } catch (final SlickException ex) {
      ex.printStackTrace();
    }
  }

  private float elapsed;

  private final World world;

  public CollisionTester() {
    super("Collision Tester");

    elapsed = 0;
    world = new World();

    final IEntity obj1 = new Entity(1420, 340, 400, 400, EntityType.CREEP);
    obj1.addRenderComponent(new SolidQuad(Color.white, 400, 400));
    world.add(obj1);

    final IEntity obj2 = new Entity(100, 440, 200, 200, EntityType.PROJECTILE);
    obj2.addRenderComponent(new SolidQuad(Color.white, 200, 200));
    obj2.addRenderComponent(new OutlineNext());
    world.add(obj2);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setTargetFrameRate(60);
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    world.render(g);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    final float frameLength = delta / 1000.0f;
    elapsed += frameLength;
    world.update(new GameTime(frameLength, elapsed));
  }
}
