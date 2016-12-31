/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package util;

import org.lwjgl.input.Keyboard;

/**
 * Helper class for querying status for a single key.
 */
public class Key {
  private final int mKey;

  private boolean mDown;
  private boolean mPressed;
  private boolean mReleased;

  /**
   * Construct a new key for a key identifier.
   *
   * @param key the key identifier, must be in the interval [1, 255]
   */
  public Key(int key) {
    assert key > Keyboard.CHAR_NONE;
    assert key < Keyboard.KEYBOARD_SIZE;

    mKey = key;

    mDown = false;
    mPressed = false;
    mReleased = false;
  }

  /**
   * Poll the status of the key.
   */
  public void update() {
    if (isKeyDown()) {
      // Key is down during this call to update().
      // If the key was down during the last call the key wasn't pressed. If the
      // key was up during the last call the key have been pressed.
      mPressed = !mDown;
      mDown = true;
    } else {
      // Key is up during this call to update().
      // If it was down during the previous call they key have been released, if
      // it wasn't it has not been released.
      mReleased = mDown;
      mDown = false;
    }
  }

  /**
   * Was the key pressed during the last call to update.
   *
   * @return true if the key was pressed, false otherwise
   */
  public boolean wasPressed() {
    return mPressed;
  }

  /**
   * Was the key released during the last call to update.
   *
   * @return true if the key was released, false otherwise
   */
  public boolean wasReleased() {
    return mReleased;
  }

  /**
   * Was the key down during the last call to update.
   *
   * @return true if the key was down, false otherwise
   */
  public boolean wasDown() {
    return mDown;
  }

  /**
   * Internal helper method that polls the keyboard.
   *
   * @return true if the key is down during the call, false otherwise
   */
  private boolean isKeyDown() {
    return Keyboard.isKeyDown(mKey);
  }
}
