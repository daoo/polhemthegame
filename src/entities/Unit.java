package entities;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

import other.GameTime;
import ui.infobar.Bar;
import ui.infobar.InfoBar;
import basics.Vector2;

import components.ICompAnim;
import components.actions.IAction;
import components.basic.IUnit;
import components.basic.Walker;
import components.physics.AABB;

public class Unit extends Entity implements IUnit {
  private final float              maxHP;
  private float                    hp;
  private boolean                  alive;

  private final InfoBar            infoBar;
  private final Bar                hpBar;

  private final Walker             walker;

  protected final ArrayList<IAction> actions;

  public Unit(final float x, final float y,
              final float width, final float height,
              final float dx, final float dy,
              final int maxHP, final ICompAnim anim)
    throws SlickException {
    super(x, y, width, height, dx, dy);
    actions = new ArrayList<IAction>();

    hp = maxHP;
    this.maxHP = maxHP;
    alive = true;

    walker = new Walker(anim);

    add(anim);

    infoBar = new InfoBar(width, 2, 0, -6);
    hpBar = new Bar(Color.green, Color.red);
    infoBar.add(hpBar);

    add(infoBar);
  }
  
  protected void addBar(final Bar bar) {
    infoBar.add(bar);
  }

  @Override
  public void update(final GameTime time) {
    super.update(time);
    hpBar.setFraction(hp / maxHP);
  }

  @Override
  public boolean isAlive() {
    return alive;
  }

  @Override
  public void kill() {
    alive = false;
  }

  @Override
  public AABB getBody() {
    return body;
  }

  @Override
  public void setPosition(final Vector2 v) {
    body.setPosition(v);
  }

  @Override
  public void setVelocity(final Vector2 v) {
    body.setVelocity(v);
  }

  @Override
  public void damage(final float damage) {
    hp -= damage;

    if (hp <= 0) {
      hp = 0;
      kill();
    }
  }

  @Override
  public void start(final GameTime time) {
    walker.startWalking(time);
  }

  @Override
  public void stop() {
    walker.stopWalking();
  }

  @Override
  public boolean hasActions() {
    return !actions.isEmpty();
  }

  @Override
  public Collection<IAction> getActions() {
    return actions;
  }

  @Override
  public void clearActions() {
    actions.clear();
  }
}
