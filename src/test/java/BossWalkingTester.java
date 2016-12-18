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
  private static final int WIDTH          = 1920;
  private static final int HEIGHT         = 1080;

  private final Random rnd;

  private final Rectangle rect;
  private final Vector2 circlePosition;

  private final int rx1, ry1, rx2, ry2, rw, rh;
  private final int cx, cy;
  private final int cr, crs;

  private int correct, incorrect;

  private Image points;
  private Graphics graphicsPoints;

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

    rnd = new Random();

    rx1 = 100; ry1 = 100;
    rx2 = 500; ry2 = 980;
    rw = 400; rh = 880;

    cx = 300; cy = 300;
    cr = 150;
    crs = cr * cr;

    rect = new Rectangle(rx1, ry1, rx2 - rx1, ry2 - ry1);
    circlePosition = new Vector2(cx, cy);

    correct = 0;
    incorrect = 0;
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    g.drawRect(rx1, ry1, rw, rh);
    g.draw(new Circle(cx, cy, cr));

    g.drawImage(points, 0, 0);

    g.drawString("Correct:   " + correct, 100, 10);
    g.drawString("Incorrect: " + incorrect, 100, 30);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    points = new Image(WIDTH, HEIGHT);
    graphicsPoints = points.getGraphics();

    container.setClearEachFrame(true);
    container.setTargetFrameRate(60);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    for (int i = 0; i < 100; ++i) {
      Vector2 p = Walking.newRandomTarget(rnd, rx1, ry1, rx2, ry2, cx, cy, crs);

      if (!Rectangle.contains(rect, p) || Vector2.distance(circlePosition, p) < cr) {
        graphicsPoints.setColor(Color.red);
        graphicsPoints.fillRect(p.x - 5, p.y - 5, 10, 10);
        ++incorrect;
      } else {
        graphicsPoints.setColor(Color.white);
        graphicsPoints.fillRect(p.x, p.y, 1, 1);
        ++correct;
      }
    }

    graphicsPoints.flush();
  }
}
