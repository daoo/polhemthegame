package components.basic;

public interface IDamagable {
  public void damage(final float damage);
  
  public void kill();
  public boolean isAlive();
}
