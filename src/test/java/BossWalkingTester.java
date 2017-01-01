/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import game.components.ai.Walking;
import math.Aabb;
import math.Rectangle;
import math.Vector2;
import util.Random;

public class BossWalkingTester extends BasicGame {
  private static final boolean FULLSCREEN = false;
  private static final int WIDTH = 1920;
  private static final int HEIGHT = 1080;
  private static final int FPS = 60;

  private final Random mRandom = new Random();

  private final Aabb mBox;
  private final math.Circle mCircle;

  private int mCorrect;
  private int mIncorrect;

  private Image mPoints;
  private Graphics mGraphicsPoints;

  public static void main(String[] args) {
    try {
      AppGameContainer app = new AppGameContainer(new BossWalkingTester());

      app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
      app.start();
    } catch (SlickException ex) {
      ex.printStackTrace();
    }
  }

  public BossWalkingTester() {
    super("Boss Random Walking Tester");

    Vector2 min = new Vector2(100, 100);
    Vector2 max = new Vector2(500, 980);
    mBox = new Aabb(min, Rectangle.fromExtents(min, max));
    mCircle = new math.Circle(new Vector2(300, 300), 150);

    mCorrect = 0;
    mIncorrect = 0;
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    g.drawRect(mBox.getMin().x, mBox.getMin().y, mBox.getSize().x, mBox.getSize().y);
    g.draw(new Circle(mCircle.center.x, mCircle.center.y, mCircle.radius));

    g.drawImage(mPoints, 0, 0);

    g.drawString("Correct:   " + mCorrect, 100, 10);
    g.drawString("Incorrect: " + mIncorrect, 100, 30);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    mPoints = new Image(WIDTH, HEIGHT);
    mGraphicsPoints = mPoints.getGraphics();

    container.setClearEachFrame(true);
    container.setTargetFrameRate(FPS);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    for (int i = 0; i < 100; ++i) {
      Vector2 p = Walking.newRandomTarget(mRandom, mBox, mCircle);

      if (!mBox.contains(p) || Vector2.distance(mCircle.center, p) < 150) {
        mGraphicsPoints.setColor(Color.red);
        mGraphicsPoints.fillRect(p.x - 5, p.y - 5, 10, 10);
        ++mIncorrect;
      } else {
        mGraphicsPoints.setColor(Color.white);
        mGraphicsPoints.fillRect(p.x, p.y, 1, 1);
        ++mCorrect;
      }
    }

    mGraphicsPoints.flush();
  }
}
