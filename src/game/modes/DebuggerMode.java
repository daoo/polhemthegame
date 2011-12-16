/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package game.modes;

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
  private boolean F5_down;

  public DebuggerMode(GameMode game) {
    this.game = game;

    framelength = 0.1f;
    paused = false;

    drawDebugInfo = true;
    debugGraph = new DebugGraph(500, 100);

    F5_down = false;
  }

  @Override
  public void update(StateManager stateManager, float dt) {
    if (paused) {
      if (Keyboard.isKeyDown(Keyboard.KEY_F5)) {
        if (!F5_down) {
          debugGraph.startUpdateMeasure();
          game.update(stateManager, framelength);
          debugGraph.stopUpdateMeasure();
        }

        F5_down = true;
      } else {
        F5_down = false;
      }
    } else {
      debugGraph.startUpdateMeasure();
      game.update(stateManager, dt);
      debugGraph.stopUpdateMeasure();
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
