package loader.data.json.types;


public class ProjectileData {
  public class AOE {
    public float      radius;
    public boolean    keepEffect;
    public SpriteData explosionSprite;
    public float      damage;
  }

  public String     name;

  public boolean    collides, gravity;
  public Size       hitbox;

  public AOE        aoe;
  public float      damage;

  public int        targets;
  public float      duration;
  public float      range;
  public float      speed;

  public SpriteData sprite;
  public String     texture;
}