package main;

import org.lwjgl.input.Keyboard;

public class Key {
  private final int key;

  private boolean down, pressed, released;

  public Key(int key) {
    down = false;
    pressed = false;
    released = false;

    this.key = key;
  }

  public void update() {
    if (isKeyDown()) {
      pressed = !down;
      down = true;
    } else {
      released = down;
      down = false;
    }
  }

  public boolean wasPressed() {
    return pressed;
  }

  public boolean wasReleased() {
    return released;
  }

  public boolean isDown() {
    return down;
  }

  private boolean isKeyDown() {
    return Keyboard.isKeyDown(key);
  }
}
