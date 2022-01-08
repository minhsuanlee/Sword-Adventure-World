package textgame;

public class Devil extends Monster {
    public Devil() {
        super();
        hp = 15;
    }

    // 4 or 5
    public int dealDamage() {
        return (int) (Math.random() * 2) + 4;
    }

    public String toString() {
        return "Devil";
    }
}
