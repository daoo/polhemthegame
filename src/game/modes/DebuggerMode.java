/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.modes;

import main.Key;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;

import states.StateManager;
import ui.hud.graph.DebugGraph;

public class DebuggerMode implements IMode {
  private final GameMode game;

  private final float framelength;

  private boolean drawDebugInfo;
  private DebugGraph debugGraph;

  private boolean paused;

  private final Key keyF1, keyF5;

  public DebuggerMode(GameMode game) {
    keyF1 = new Key(Keyboard.KEY_F1);
    keyF5 = new Key(Keyboard.KEY_F5);

    this.game = game;

    framelength = 0.1f;
    paused = false;

    drawDebugInfo = true;
    debugGraph = new DebugGraph(500, 100);
  }

  @Override
  public void update(StateManager stateManager, float dt) {
    if (paused) {
      keyF5.update();
      if (keyF5.wasPressed()) {
        debugGraph.startUpdateMeasure();
        game.update(stateManager, framelength);
        debugGraph.stopUpdateMeasure();
      }
    } else {
      debugGraph.startUpdateMeasure();
      game.update(stateManager, dt);
      debugGraph.stopUpdateMeasure();
    }

    keyF1.update();
    if (keyF1.wasPressed()) {
      drawDebugInfo = !drawDebugInfo;
    }
  }

  @Override
  public void render(Graphics g) {
    debugGraph.startRenderMeasure();
    game.render(g);
    debugGraph.stopRenderMeasure();

    if (drawDebugInfo) {
      debugGraph.render(g, 0, 80);
    }
  }

  @Override
  public void start(StateManager stateManager) {
    game.start(stateManager);
  }
}
