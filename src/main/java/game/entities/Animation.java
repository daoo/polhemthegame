/*
 * Copyright (c) 2009-2012 Daniel Oom, see license.txt for more info.
 */

package game.entities;

import org.newdawn.slick.Graphics;

import game.components.graphics.AnimatedSheet;
import game.types.GameTime;
import game.types.Message;
import math.Vector2;
import util.Node;

/**
 * Specialized entity for just showing an animation.
 */
public class Animation implements Entity {
  private final AnimatedSheet mSheet;
  private final Graphics mGraphics;

  private boolean mActive;
  private Vector2 mPosition;

  public Animation(int x, int y, AnimatedSheet anim, Graphics graphics) {
    mPosition = new Vector2(x, y);
    mSheet = anim;
    mGraphics = graphics;

    mActive = true;
  }

  @Override
  public boolean isActive() {
    return mActive;
  }

  @Override
  public void remove() {
    mActive = false;
  }

  @Override
  public void render(Graphics g) {
    g.pushTransform();
    g.translate(mPosition.x, mPosition.y);

    mSheet.render(g);

    g.popTransform();
  }

  @Override
  public void sendMessage(Message message, Object args) {
    mSheet.reciveMessage(message, args);
  }

  public void setPosition(Vector2 position) {
    mPosition = position;
  }

  @Override
  public void update(GameTime time) {
    mSheet.update(time);

    if (mSheet.getCurrentTile().isEqual(mSheet.getLastTile())) {
      mActive = false;

      mGraphics.pushTransform();
      mGraphics.translate(mPosition.x, mPosition.y);
      mSheet.render(mGraphics);
      mGraphics.popTransform();
      mGraphics.flush();
    }
  }

  @Override
  public String debugString() {
    return "Animation";
  }

  @Override
  public Node<String> debugTree() {
    Node<String> parent = new Node<>(debugString());

    parent.nodes.add(new Node<>("Animation = " + mSheet));
    parent.nodes.add(new Node<>("Active = " + Boolean.toString(mActive)));

    return parent;
  }
}
