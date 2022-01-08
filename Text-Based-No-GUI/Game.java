package textgame;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Game {
    public static final int FULL_HP = 10;
    public static final List<String> LOCATIONS = Arrays.asList(new String[]{"the Town", "the House", "the Devil's Castle", "the Dungeon"});
    public static final List<String> ACTIONS = Arrays.asList(new String[]{"ROCK", "PAPER", "SCISSORS"});
    public static final int DAGGER_DAMAGE = 1;
    public static final int EXCALIBUR_DAMAGE = 5;

    private String playerName;
    private int hp;
    private boolean isGameOver;
    private int goldCount;
    private int location;
    private String sword;
    private List<String> unknownInfo;
    private List<String> knownInfo;
    private int totalInfoCount;
    private boolean isDevilDefeated;

    public Game() {
        hp = FULL_HP;
        isGameOver = false;
        goldCount = 5;
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
        try {
            File file = new File("./information.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                unknownInfo.add(fileScanner.nextLine());
                totalInfoCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        try (
//                InputStream inputStream = this.getClass().getResourceAsStream("/information.txt");
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                Stream<String> lines = bufferedReader.lines();
//        ) {
//            lines.forEach(a -> unknownInfo.add(a));
//            totalInfoCount = unknownInfo.size();
//        } catch (IOException e) {
//            System.out.println(e);
//        }
    }
}
