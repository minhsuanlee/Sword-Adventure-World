package textgame;

public class Monster {
    protected int hp;
    private boolean isDefeated;

    public Monster() {
        isDefeated = false;
        hp = 2;
    }

    public Action takeAction() {
        return new Action();
    }

    // 1 or 2
    public int dealDamage() {
        return (int) (Math.random() * 2) + 1;
    }

    public int takeDamage(int damage) {
        hp -= damage;
        isDefeated = hp <= 0;
        return hp;
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public int goldDrop() {
        return (int) (Math.random() * 3) + 1;
    }

    public String toString() {
        return "Monster";
    }
}
