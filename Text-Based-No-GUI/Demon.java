package textgame;

public class Demon extends Monster {
    public Demon() {
        super();
        hp = 8;
    }

    // 1 or 2 or 3
    public int dealDamage() {
        return (int) (Math.random() * 3) + 1;
    }

    public String toString() {
        return "Demon";
    }
}
