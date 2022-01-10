public class Devil extends Monster {
  public Devil() {
    super();
    hp = 15;
  }

  // 3 or 4 or 5
  public int dealDamage() {
    return (int) (Math.random() * 3) + 3;
  }

  public String toString() {
    return "Devil";
  }
}
