package components.interfaces;

public interface IDamagable {
  public void damage(final float damage);

  public void kill();
  public void killSilently();
  public boolean isAlive();
}
