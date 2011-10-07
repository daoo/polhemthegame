package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface IGameState {
  void render(final Graphics g) throws SlickException;
  void update(final GameStateManager stateGame, int delta);
}
