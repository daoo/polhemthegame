package components.basic;

public class Stats {
  private int kills;
  
  public Stats() {
    kills = 0;
  }
  
  public void addKill() {
    ++kills;
  }
  
  public int getKills() {
    return kills;
  }
}
