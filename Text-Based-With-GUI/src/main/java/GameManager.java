public class GameManager {
  public static Game game;
  public static GameWindow gameWindow;

  public static void main(String[] args) {
    game = new Game();
    gameWindow = new GameWindow(game);
  }
}
