/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import java.io.IOException;
import java.util.HashMap;

import game.course.LevelManager;
import game.misc.Locator;
import game.types.GameTime;
import game.ui.hud.BlackBox;
import game.ui.hud.PlayerUI;
import game.ui.hud.UI;
import loader.data.json.CampaignData;
import loader.parser.ParserException;
import util.FontHelper;
import util.Key;
import util.Node;

public class PlayingState implements GameState {
  private static final String[] PAUSE_TEXT = {
      "big:Game Paused", "Press Escape to unpause or Backspace to go to the Main Menu"};
  private static final int PAUSE_TEXT_SPACING = 10;

  private final UI mUi;
  private final LevelManager mLevels;
  private final Image mImgPauseText;

  private final Key mKeyBackspace;
  private final Key mKeyEscape;

  private long mElapsed;
  private boolean mPaused;

  private final int mArenaX;
  private final int mArenaY;
  private final int mWindowWidth;
  private final int mWindowHeight;

  public PlayingState(
      StateManager stateManager, CampaignData data, boolean twoPlayer, int windowWidth,
      int windowHeight) throws ParserException, IOException, SlickException {
    mWindowWidth = windowWidth;
    mWindowHeight = windowHeight;

    mKeyBackspace = new Key(Keyboard.KEY_BACK);
    mKeyEscape = new Key(Keyboard.KEY_ESCAPE);

    mArenaX = 0;
    mArenaY = PlayerUI.HEIGHT;

    mUi = new UI(windowWidth, windowHeight);
    Locator.registerUI(mUi);
    mUi.addStatic(new BlackBox(0, 0, windowWidth, PlayerUI.HEIGHT));
    mUi.addStatic(new BlackBox(0, windowHeight - PlayerUI.HEIGHT, windowWidth, PlayerUI.HEIGHT));

    int arenaWidth = windowWidth;
    int arenaHeight = windowHeight - PlayerUI.HEIGHT * 2;
    mLevels = new LevelManager(stateManager, twoPlayer, data, windowWidth, arenaWidth, arenaHeight);

    HashMap<String, UnicodeFont> fonts = new HashMap<>();
    fonts.put("big", FontHelper.getFont("Verdana", 30));
    fonts.put("default", FontHelper.getFont("Verdana", 20));
    mImgPauseText = FontHelper.renderString(fonts, PAUSE_TEXT_SPACING, PAUSE_TEXT);

    mElapsed = 0;
    mPaused = false;
  }

  @Override
  public void start(StateManager stateManager) {
    mLevels.nextLevel();
  }

  @Override
  public void end(StateManager stateManager) {
    // Do nothing
  }

  /**
   * The update iteration of the game loop.
   * Updates entities, switches levels, etc.
   *
   * @param dt Approximate length of the current frame in milliseconds
   */
  @Override
  public void update(StateManager stateManager, int dt) {
    if (mPaused) {
      mKeyBackspace.update();
      mKeyEscape.update();
      if (mKeyEscape.wasPressed()) {
        mPaused = false;
      } else if (mKeyBackspace.wasPressed()) {
        stateManager.enterMainMenu();
      }
    } else {
      mKeyEscape.update();
      if (mKeyEscape.wasPressed()) {
        mPaused = true;
      } else {
        mElapsed += dt;
        mLevels.update(new GameTime(dt / 1000.0f, dt, mElapsed));
        mUi.update();

        if (mLevels.isFinished()) {
          if (mLevels.isCredits()) {
            stateManager.enterCredits();
          } else {
            stateManager.enterMainMenu();
          }
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(mArenaX, mArenaY);

    mLevels.render(g);
    mUi.renderDynamics(g);

    g.popTransform();

    mUi.renderStatics(g);

    if (mPaused) {
      g.setColor(new Color(0, 0, 0, 100));
      g.fillRect(0, 0, mWindowWidth, mWindowHeight);
      g.drawImage(mImgPauseText, mWindowWidth / 2.0f - mImgPauseText.getWidth() / 2.0f,
          mWindowHeight / 2.0f - mImgPauseText.getHeight() / 2.0f);
    }
  }

  @Override
  public String debugString() {
    return "PlayingState, time " + mElapsed;
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());
    parent.nodes.add(mLevels.debugTree());

    return parent;
  }
}
