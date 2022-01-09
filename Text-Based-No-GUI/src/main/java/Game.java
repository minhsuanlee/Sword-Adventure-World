import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Game {
  public static final int FULL_HP = 10;
  public static final List<String> LOCATIONS =
      Arrays.asList("the Town", "the House", "the Devil's Castle", "the Dungeon");
  public static final List<String> ACTIONS =
      Arrays.asList("ROCK", "PAPER", "SCISSORS");
  public static final int DAGGER_DAMAGE = 1;
  public static final int EXCALIBUR_DAMAGE = 5;

  private String playerName;
  private int hp;
  private boolean isGameOver;
  private int goldCount;
  private int location;
  private String sword;
  private final List<String> unknownInfo;
  private final List<String> knownInfo;
  private int totalInfoCount;
  private boolean isDevilDefeated;

  public Game() {
    hp = FULL_HP;
    isGameOver = false;
    goldCount = 0;
    location = 0;
    sword = "Dagger";
    unknownInfo = new ArrayList<>();
    knownInfo = new ArrayList<>();
    isDevilDefeated = false;
    populateUnknownInfo();
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public boolean getIsGameOver() {
    return isGameOver;
  }

  public void setIsGameOver() {
    isGameOver = true;
  }

  public int getGoldCount() {
    return goldCount;
  }

  public int earnGold(int amount) {
    goldCount += amount;
    return goldCount;
  }

  public int buyInformation() {
    return --goldCount;
  }

  public int getHP() {
    return hp;
  }

  public int dealDamage() {
    return sword.equals("Dagger") ? DAGGER_DAMAGE : EXCALIBUR_DAMAGE;
  }

  public int takeDamage(int damage) {
    hp -= damage;
    return hp;
  }

  public int restoreFullHP() {
    hp = FULL_HP;
    return hp;
  }

  public int getLocationIndex() {
    return location;
  }

  public String getLocation() {
    return LOCATIONS.get(location);
  }

  public String changeLocation(int locIndex) {
    location = locIndex;
    return LOCATIONS.get(location);
  }

  public String getWeapon() {
    return sword;
  }

  public void setWeapon(String weapon) {
    sword = weapon;
  }

  public int getTotalInfoCount() {
    return totalInfoCount;
  }

  public int getKnownCount() {
    return knownInfo.size();
  }

  public boolean getDevilDefeated() {
    return isDevilDefeated;
  }

  public void setDevilDefeated() {
    isDevilDefeated = true;
  }

  public String unlockInfo() {
    int pick = (int) (Math.random() * unknownInfo.size());
    String info = unknownInfo.remove(pick);
    knownInfo.add(info);
    return info;
  }

  private void populateUnknownInfo() {
    try (InputStream inputStream = this.getClass().getResourceAsStream("/information.txt")) {
      if (inputStream == null) {
        throw new IOException();
      }

      try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
           BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
           Stream<String> lines = bufferedReader.lines()) {
        lines.forEach(unknownInfo::add);
        totalInfoCount = unknownInfo.size();
      }
    } catch (IOException e) {
      System.out.println("Unable to find \"information.txt\" in resources/");
      setIsGameOver();
    }
  }
}
