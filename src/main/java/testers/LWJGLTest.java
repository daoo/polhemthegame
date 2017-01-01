/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package testers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

public class LWJGLTest {
  private static final int WIDTH = 1024;
  private static final int HEIGHT = 768;

  public static void main(String[] args) {
    try {
      Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
      Display.create();
      Display.setVSyncEnabled(true);

      GL11.glEnable(GL11.GL_TEXTURE_2D);

      GL11.glClearColor(0, 0, 0, 0);

      GL11.glEnable(GL11.GL_BLEND);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

      GL11.glMatrixMode(GL11.GL_PROJECTION);
      GL11.glLoadIdentity();
      GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
      GL11.glMatrixMode(GL11.GL_MODELVIEW);

      Texture texture = TextureLoader.getTexture("PNG",
          ResourceLoader.getResourceAsStream("data/textures/bosses/wheelchairguy-walk.png"));

      while (!Display.isCloseRequested()) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        Color.white.bind();
        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(0, 0);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(texture.getImageWidth(), 0);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(texture.getImageWidth(), texture.getImageHeight());
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(0, texture.getImageHeight());
        GL11.glEnd();

        Display.update();
        Display.sync(60);
      }

      Display.destroy();
    } catch (LWJGLException | IOException e) {
      Display.destroy();
      e.printStackTrace();
    }
  }
}
