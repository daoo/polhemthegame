package main;

import org.lwjgl.input.Keyboard;

/**
 * Helper class for querying status for a single key.
 */
public class Key {
  private final int key;

  private boolean down, pressed, released;

  /**
   * Construct a new key for a key identifier.
   * @param key the key identifier, must be in the interval [1, 255]
   */
  public Key(int key) {
    assert key > Keyboard.CHAR_NONE;
    assert key < Keyboard.KEYBOARD_SIZE;

    this.key = key;

    down = false;
    pressed = false;
    released = false;
  }

  /**
   * Poll the status of the key.
   */
  public void update() {
    if (isKeyDown()) {
      // Key is down during this call to update().
      // If the key was down during the last call the key wasn't pressed. If the
      // key was up during the last call the key have been pressed.
      pressed = !down;
      down = true;
    } else {
      // Key is up during this call to update().
      // If it was down during the previous call they key have been released, if
      // it wasn't it has not been released.
      released = down;
      down = false;
    }
  }

  /**
   * Was the key pressed during the last call to update.
   * @return true if the key was pressed, false otherwise
   */
  public boolean wasPressed() {
    return pressed;
  }

  /**
   * Was the key released during the last call to update.
   * @return true if the key was released, false otherwise
   */
  public boolean wasReleased() {
    return released;
  }

  /**
   * Was the key down during the last call to update.
   * @return true if the key was down, false otherwise
   */
  public boolean wasDown() {
    return down;
  }

  /**
   * Internal helper method that polls the keyboard.
   * @return true if the key is down during the call, false otherwise
   */
  private boolean isKeyDown() {
    return Keyboard.isKeyDown(key);
  }
}
