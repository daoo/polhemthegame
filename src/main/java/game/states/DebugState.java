/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.states;


import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import game.ui.hud.graph.DebugGraph;
import util.Key;
import util.Node;
import util.Tree;

/**
 * Wrapper state which renders some extra debugging information.
 */
public class DebugState implements IState {
  private static final int GRAPH_X = 0;
  private static final int GRAPH_Y = 80;

  /**
   * Frame step length in milliseconds.
   */
  private static final int FRAME_LENGTH = 100;

  private final DebugGraph mDebugGraph;
  private boolean mDrawDebugInfo;

  private final IState mGame;
  private boolean mPaused;

  private final Key mKeyF1;
  private final Key mKeyF2;
  private final Key mKeyF5;
  private final Key mKeyF6;

  public DebugState(int width, IState game) {
    mKeyF1 = new Key(Keyboard.KEY_F1);
    mKeyF2 = new Key(Keyboard.KEY_F2);
    mKeyF5 = new Key(Keyboard.KEY_F5);
    mKeyF6 = new Key(Keyboard.KEY_F6);

    mGame = game;

    mPaused = false;

    mDrawDebugInfo = true;
    mDebugGraph = new DebugGraph(width, 100);
  }

  @Override
  public void start(StateManager stateManager) {
    mGame.start(stateManager);
  }

  @Override
  public void end(StateManager stateManager) {
    mGame.end(stateManager);
  }

  @Override
  public void update(StateManager stateManager, int dt) {
    if (mPaused) {
      mKeyF5.update();
      if (mKeyF5.wasPressed()) {
        mPaused = false;
      } else {
        mKeyF6.update();
        if (mKeyF6.wasPressed()) {
          mDebugGraph.startUpdateMeasure();
          mGame.update(stateManager, FRAME_LENGTH);
          mDebugGraph.stopUpdateMeasure();
        }
      }
    } else {
      mKeyF5.update();
      if (mKeyF5.wasPressed()) {
        mPaused = true;
      } else {
        mDebugGraph.startUpdateMeasure();
        mGame.update(stateManager, dt);
        mDebugGraph.stopUpdateMeasure();
      }
    }

    mKeyF1.update();
    if (mKeyF1.wasPressed()) {
      mDrawDebugInfo = !mDrawDebugInfo;
    }

    mKeyF2.update();
    if (mKeyF2.wasPressed()) {
      Tree<String> tree = new Tree<>(mGame.debugTree());
      System.out.println(tree);
    }
  }

  @Override
  public void render(Graphics g) throws SlickException {
    mDebugGraph.startRenderMeasure();
    mGame.render(g);
    mDebugGraph.stopRenderMeasure();

    if (mDrawDebugInfo) {
      g.pushTransform();
      g.translate(GRAPH_X, GRAPH_Y);
      mDebugGraph.render(g);
      g.popTransform();
    }
  }

  @Override
  public String debugString() {
    return "DebugState";
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());
    parent.nodes.add(mGame.debugTree());

    return parent;
  }
}
