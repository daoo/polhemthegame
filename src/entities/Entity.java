package entities;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import basics.Vector2;

import other.GameTime;

import components.ICompRender;
import components.ICompUpRend;
import components.ICompUpdate;
import components.basic.IEntity;
import components.physics.AABB;

public class Entity implements IEntity {
  protected final AABB                   body;
  private final ArrayList<ICompUpdate> updates;
  private final ArrayList<ICompRender>   renders;

  public Entity(final float x, final float y,
                final float w, final float h,
                final float dx, final float dy) {
    body = new AABB(x, y, w, h, dx, dy);
    updates = new ArrayList<ICompUpdate>();
    renders = new ArrayList<ICompRender>();
  }

  public void update(final GameTime time) {
    body.integrate(time.getFrameLength());
    
    for (ICompUpdate comp : updates) {
      comp.update(time);
    }
  }

  public void render(final Graphics g) {
    g.pushTransform();
    g.translate(body.getX1(), body.getY1());
    
    for (ICompRender comp : renders) {
      comp.render(g);
    }
    
    g.popTransform();
  }

  public void add(final ICompUpRend comp) {
    updates.add(comp);
    renders.add(comp);
  }
  
  public void add(final ICompRender comp) {
    renders.add(comp);
  }
  
  public void add(final ICompUpdate comp) {
    updates.add(comp);
  }

  @Override
  public void setPosition(Vector2 v) {
    body.setPosition(v);
  }

  @Override
  public void setVelocity(Vector2 v) {
    body.setVelocity(v);
  }

  @Override
  public AABB getBody() {
    return body;
  }
}
