import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Game {
  public static final int FULL_HP = 10;
  public static final List<String> LOCATIONS =
      Arrays.asList("the Town", "the Cross Road", "the House", "the Devil's Castle", "the Dungeon");
  public static final Map<Integer, String[]> LOC_TO_CHOICE =
      new HashMap<>() {
        {
          put(
              0,
              new String[] {
                "TOWN",
                "ENTER_TOWN",
                "BUY_INFO",
                "ATTACK_GUARD",
                "WALK_AWAY",
                "END_OF_GAME",
                "START_OVER"
              });
          put(1, new String[] {"CROSS_ROAD"});
          put(2, new String[] {"HOUSE"});
          put(3, new String[] {"DEVILS_CASTLE", "FIGHT", "ROUND", "WON", "DEVIL_DEFEATED", "HEAL"});
          put(4, new String[] {"DUNGEON", "FIGHT", "ROUND", "WON"});
        }
      };
  public static final List<String> MOVES = Arrays.asList("ROCK", "PAPER", "SCISSORS");
  public static final int DAGGER_DAMAGE = 1;
  public static final int EXCALIBUR_DAMAGE = 5;

  private final List<String> unknownInfo;
  private final List<String> knownInfo;

  private String playerName;
  private int hp;
  private boolean isGameOver;
  private int goldCount;
  private int locIndex;
  private String locChoice;
  private String weapon;
  private int totalInfoCount;
  private boolean isDevilDefeated;
  private int demonDefeated;

  public Game() {
    playerName = "Player";
    hp = FULL_HP;
    isGameOver = false;
    goldCount = 0;
    locIndex = 0;
    locChoice = LOC_TO_CHOICE.get(locIndex)[0];
    weapon = "Dagger";
    unknownInfo = new ArrayList<>();
    knownInfo = new ArrayList<>();
    totalInfoCount = 0;
    isDevilDefeated = false;
    demonDefeated = 0;

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
    return weapon.equals("Dagger") ? DAGGER_DAMAGE : EXCALIBUR_DAMAGE;
  }

  public int takeDamage(int damage) {
    hp -= damage;
    return hp;
  }

  public void restoreFullHP() {
    hp = FULL_HP;
  }

  public int getLocationIndex() {
    return locIndex;
  }

  public String getLocation() {
    return LOCATIONS.get(locIndex);
  }

  public String changeLocation(int locIndex) {
    this.locIndex = locIndex;
    return LOCATIONS.get(locIndex);
  }

  public String getLocationChoice() {
    return locChoice;
  }

  public void setLocationChoice(int choiceIdx) {
    locChoice = LOC_TO_CHOICE.get(locIndex)[choiceIdx];
  }

  public String getWeapon() {
    return weapon;
  }

  public void setWeapon(String weapon) {
    this.weapon = weapon;
  }

  public int getTotalInfoCount() {
    return totalInfoCount;
  }

  public int getKnownCount() {
    return knownInfo.size();
  }

  public boolean isDevilDefeated() {
    return isDevilDefeated;
  }

  public void setDevilDefeated() {
    isDevilDefeated = true;
  }

  public int getDemonDefeated() {
    return demonDefeated;
  }

  public void setDemonDefeated(int times) {
    demonDefeated = times;
  }

  public String unlockInfo() {
    int pick = (int) (Math.random() * unknownInfo.size());
    String info = unknownInfo.remove(pick);
    knownInfo.add(info);
    return info;
  }

  public void reset() {
    playerName = "Player";
    hp = FULL_HP;
    isGameOver = false;
    goldCount = 0;
    locIndex = 0;
    locChoice = LOC_TO_CHOICE.get(locIndex)[0];
    weapon = "Dagger";
    totalInfoCount = 0;
    isDevilDefeated = false;
    demonDefeated = 0;

    knownInfo.clear();
    populateUnknownInfo();
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
