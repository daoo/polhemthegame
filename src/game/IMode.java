package game;

import org.newdawn.slick.Graphics;

public interface IMode {
  public void update(final float dt);
  public void render(final Graphics g);
  
  public boolean isFinished();
}
