public class Move implements Comparable<Move> {
  private final String action;
  private final int actionIndex;

  public Move() {
    this((int) (Math.random() * Game.MOVES.size()));
  }

  public Move(int actionIndex) {
    this.action = Game.MOVES.get(actionIndex);
    this.actionIndex = actionIndex;
  }

  public String getAction() {
    return action;
  }

  public int getActionIndex() {
    return actionIndex;
  }

  @Override
  public int compareTo(Move o) {
    int otherIndex = o.getActionIndex();
    if (actionIndex == otherIndex) {
      return 0;
    } else if (actionIndex == 1 && otherIndex == 0
        || actionIndex == 2 && otherIndex == 1
        || actionIndex == 0 && otherIndex == 2) {
      return 1;
    } else {
      return -1;
    }
  }
}
