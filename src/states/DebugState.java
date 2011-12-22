/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package states;

import main.Key;
import main.Launcher;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ui.hud.graph.DebugGraph;
import util.Node;
import util.Tree;

/**
 * Wrapper state which renders some extra debugging information.
 */
public class DebugState implements IState {
  private final IState game;

  private static final float FRAME_LENGTH = 0.1f;

  private boolean drawDebugInfo;
  private DebugGraph debugGraph;

  private boolean paused;

  private final Key keyF1, keyF2, keyF5, keyF6;

  public DebugState(IState game) {
    keyF1 = new Key(Keyboard.KEY_F1);
    keyF2 = new Key(Keyboard.KEY_F2);
    keyF5 = new Key(Keyboard.KEY_F5);
    keyF6 = new Key(Keyboard.KEY_F6);

    this.game = game;

    paused = false;

    drawDebugInfo = true;
    debugGraph = new DebugGraph(Launcher.WIDTH, 100);
  }

  @Override
  public void start(StateManager stateManager) {
    game.start(stateManager);
  }

  @Override
  public void end(StateManager stateManager) {
    game.end(stateManager);
  }

  @Override
  public void update(StateManager stateManager, float dt) {
    if (paused) {
      keyF5.update();
      if (keyF5.wasPressed()) {
        paused = false;
      } else {
        keyF6.update();
        if (keyF6.wasPressed()) {
          debugGraph.startUpdateMeasure();
          game.update(stateManager, FRAME_LENGTH);
          debugGraph.stopUpdateMeasure();
        }
      }
    } else {
      keyF5.update();
      if (keyF5.wasPressed()) {
        paused = true;
      } else {
        debugGraph.startUpdateMeasure();
        game.update(stateManager, dt);
        debugGraph.stopUpdateMeasure();
      }
    }

    keyF1.update();
    if (keyF1.wasPressed()) {
      drawDebugInfo = !drawDebugInfo;
    }

    keyF2.update();
    if (keyF2.wasPressed()) {
      Tree<Object> tree = new Tree<>(game.debugInfo());
      System.out.println(tree.toString());
    }
  }

  @Override
  public void render(Graphics g) throws SlickException {
    debugGraph.startRenderMeasure();
    game.render(g);
    debugGraph.stopRenderMeasure();

    if (drawDebugInfo) {
      debugGraph.render(g, 0, 80);
    }
  }

  @Override
  public Node<Object> debugInfo() {
    Node<Object> parent = new Node<Object>("DebugState");
    parent.nodes.add(game.debugInfo());

    return parent;
  }
}
