package entities.projectiles;

import java.util.ArrayList;
import java.util.Collection;

import loader.data.json.ProjectilesData.ProjectileData;
import other.GameTime;

import components.actions.AOEDamage;
import components.actions.IAction;
import components.actions.SpawnRunToEndAnim;
import components.interfaces.ICompAnim;

public class ExplodingProjectile extends Projectile {
  private ArrayList<IAction> actions;

  private final float        aoeRange, aoeDamage;

  private final ICompAnim    explosion;

  public ExplodingProjectile(float x, float y, float rot,
                             ProjectileData data,
                             ICompAnim renderer, ICompAnim explosion,
                             GameTime time) {
    super(x, y, rot, data, renderer, time);

    actions = new ArrayList<IAction>();

    aoeRange = data.aoe.radius;
    aoeDamage = data.aoe.damage;

    this.explosion = explosion;
  }

  @Override
  public void kill() {
    super.kill();

    actions.add(new AOEDamage(body.getCenter(), aoeRange, aoeDamage));
    actions.add(new SpawnRunToEndAnim(body.getX1(), body.getY1(),
                                      explosion.getTileWidth(),
                                      explosion.getTileHeight(),
                                      explosion));
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
