package loader.data.json.types;


public class WeaponData {
  public String     name;

  public boolean    automatic;
  public int        clipSize;
  public float      rpm;
  public float      reloadTime;
  public float      launchAngle;

  public SpriteData sprite;
  public String     texture;

  public String     projectile;
  public Offset     muzzleOffset;
}