package textgame;

public class Action implements Comparable<Action> {
    private String action;
    private int actionIndex;

    public Action() {
        this((int) (Math.random() * Game.ACTIONS.size()));
    }

    public Action(int actionIndex) {
        this.action = Game.ACTIONS.get(actionIndex);
        this.actionIndex = actionIndex;
    }

    public String getAction() {
        return action;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    @Override
    public int compareTo(Action o) {
        int otherIndex = o.getActionIndex();
        if (actionIndex == otherIndex) {
            return 0;
        } else if (actionIndex == 1 && otherIndex == 0 || actionIndex == 2 && otherIndex == 1 || actionIndex == 0 && otherIndex == 2) {
            return 1;
        } else {
            return -1;
        }
    }
}
