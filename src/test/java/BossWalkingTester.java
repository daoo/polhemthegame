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
import math.Rectangle;
import math.Vector2;
import util.Random;

public class BossWalkingTester extends BasicGame {
  private static final boolean FULLSCREEN = false;
  private static final int WIDTH = 1920;
  private static final int HEIGHT = 1080;

  private final Random mRnd;

  private final Rectangle mRect;
  private final Vector2 mCirclePosition;

  private final int mRx1;
  private final int mRy1;
  private final int mRx2;
  private final int mRy2;
  private final int mRw;
  private final int mRh;
  private final int mCx;
  private final int mCy;
  private final int mCr;
  private final int mCrs;

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

    mRnd = new Random();

    mRx1 = 100;
    mRy1 = 100;
    mRx2 = 500;
    mRy2 = 980;
    mRw = 400;
    mRh = 880;

    mCx = 300;
    mCy = 300;
    mCr = 150;
    mCrs = mCr * mCr;

    mRect = new Rectangle(mRx1, mRy1, mRx2 - mRx1, mRy2 - mRy1);
    mCirclePosition = new Vector2(mCx, mCy);

    mCorrect = 0;
    mIncorrect = 0;
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    g.drawRect(mRx1, mRy1, mRw, mRh);
    g.draw(new Circle(mCx, mCy, mCr));

    g.drawImage(mPoints, 0, 0);

    g.drawString("Correct:   " + mCorrect, 100, 10);
    g.drawString("Incorrect: " + mIncorrect, 100, 30);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    mPoints = new Image(WIDTH, HEIGHT);
    mGraphicsPoints = mPoints.getGraphics();

    container.setClearEachFrame(true);
    container.setTargetFrameRate(60);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    for (int i = 0; i < 100; ++i) {
      Vector2 p = Walking.newRandomTarget(mRnd, mRx1, mRy1, mRx2, mRy2, mCx, mCy, mCrs);

      if (!Rectangle.contains(mRect, p) || Vector2.distance(mCirclePosition, p) < mCr) {
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
