package textgame;

import java.util.Scanner;

public class GameManager {
    public static Scanner scanner = new Scanner(System.in);
    public static Game game = new Game();

    public static void main(String[] args) {
        System.out.println("Welcome to Sword Adventure World");
        printGameStoryDivider();
        System.out.println("The Guard: Hey, I've never seen you before. What is your name?");
        System.out.print("You: My name is ");
        String playerName = scanner.nextLine();
        game.setPlayerName(playerName);
        System.out.println("The Guard: " + game.getPlayerName() + ". Hmmm.");

        while (!game.getIsGameOver()) {
            switch (game.getLocationIndex()) {
                case 0:
                    interactWithTheGuard();
                    break;
                case 1:
                    goBackToTheHouse();
                    break;
                case 2:
                    sneakIntoDevilsCastle();
                    break;
                default:  // 3
                    enterIntoTheDungeon();
            }
        }
    }

    /**********
     ** Town **
     **********/
    public static void printGuardMenu() {
        System.out.println("1. Try to enter the gate of the Town");
        System.out.println("2. Try to get information from the Guard");
        System.out.println("3. Attack the Guard");
        System.out.println("4. Walk away");
        System.out.println();
    }

    public static void interactWithTheGuard() {
        printGameStoryDivider();
        printGameNarrative("You are standing in front of the gate of the Town");
        printGameNarrative("What would you like to do? (Enter Action #)");
        System.out.println();

        printGuardMenu();
        System.out.print(game.getPlayerName() + ": ");
        int action = Integer.parseInt(scanner.nextLine());
        printGameStoryDivider();

        switch (action) {
            case 1:
                if (game.getDevilDefeated()) {
                    System.out.println("The Guard: " + game.getPlayerName() + ", you are the Hero!");
                    System.out.println("           Please come in. You are more than welcome here in the Town!");
                    game.setIsGameOver();
                    printGameStoryDivider();
                    printGameNarrative("END OF GAME");
                } else {
                    System.out.println("The Guard: Wow wow wow... slow down there \"" + game.getPlayerName() + "\".");
                    System.out.println("           We don't allow stranger just walk into the Town like that.");
                }
                break;
            case 2:
                if (game.getGoldCount() > 0) {
                    int remainigGoldCount = game.buyInformation();
                    if (game.getKnownCount() == game.getTotalInfoCount()) {
                        System.out.println("The Guard: I've already given you all the information that I had.");
                        System.out.println("           Thanks for the gold though. Ha ha ha ha!");
                    } else {
                        System.out.println("The Guard: " + game.unlockInfo());
                    }
                    printGameNarrative("You now have " + remainigGoldCount + " gold left");
                } else {
                    System.out.println("The Guard: I don't just give away information like that.");
                    System.out.println("           Try coming back when you have some gold on you.");
                }
                break;
            case 3:
                System.out.println("The Guard: What are you thinking!? How dare you attacked me!");
                takeDamage(1);
                break;
            default:
                changeLocation();
        }
    }

    /***********
     ** House **
     ***********/
    public static void goBackToTheHouse() {
        printGameStoryDivider();
        printGameNarrative("You are back to " + game.getLocation());
        printGameNarrative("HP: " + game.restoreFullHP());
        printGameStoryDivider();
        changeLocation();
    }

    /********************
     ** Devil's Castle **
     ********************/
    public static void sneakIntoDevilsCastle() {
        printGameStoryDivider();
        printGameNarrative("You sneaked into " + game.getLocation());

        if (game.getDevilDefeated()) {
            printGameNarrative("The Devil has been defeated");
            printGameStoryDivider();
            changeLocation();
        } else {
            Demon[] demons = new Demon[]{new Demon(), new Demon()};
            int choice = 1;
            for (int i = 0; i < demons.length; i++) {
                Demon demon = demons[i];
                printGameNarrative("Demon " + (i + 1) + " appears. What would you like to do? (Enter Action #)");
                System.out.println();
                printFightMenu(demon);

                System.out.print(game.getPlayerName() + ": ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    while (!demon.isDefeated() && game.getHP() > 0) {
                        fight(demon);
                    }

                    if (demon.isDefeated()) {
                        printGameNarrative("You defeated the Demon");
                        printGameStoryDivider();
                    }

                    if (game.getHP() <= 0) break;
                } else {
                    break;
                }
            }

            if (choice != 1) {
                printGameStoryDivider();
                changeLocation();
            }

            if (game.getHP() > 0 && choice == 1) {
                if (!game.getWeapon().equals("Excalibur")) {
                    printGameNarrative("The Devil appears");
                    System.out.println("The Devil: You are TOO weak.");
                    takeDamage(Game.FULL_HP);
                } else {
                    System.out.println("The Goddness: Thank you, " + game.getPlayerName() + ", for travelling this far.");
                    System.out.println("              I will now restore all your HP and good luck.");
                    System.out.println();

                    game.restoreFullHP();
                    printGameNarrative("The Goddness healed you");
                    printGameNarrative("HP: " + game.restoreFullHP());
                    printGameStoryDivider();

                    Monster devil = new Devil();
                    printGameNarrative("The Devil appears");
                    System.out.println();
                    printFightMenu(devil);

                    System.out.print(game.getPlayerName() + ": ");
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 1) {
                        while (!devil.isDefeated() && game.getHP() > 0) {
                            fight(devil);
                        }

                        if (devil.isDefeated()) {
                            game.setDevilDefeated();
                            printGameNarrative("You defeated the Devil");
                        }
                    }
                }
            }
        }
    }

    /*************
     ** Dungeon **
     *************/
    public static void enterIntoTheDungeon() {
        printGameStoryDivider();
        printGameNarrative("You entered " + game.getLocation());

        int choice = 1;
        while (choice == 1) {
            Monster monster = new Monster();
            printGameNarrative("A Monster appears. What would you like to do? (Enter Action #)");
            System.out.println();
            printFightMenu(monster);

            System.out.print(game.getPlayerName() + ": ");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice != 1) break;
            while (!monster.isDefeated() && game.getHP() > 0) {
                fight(monster);
            }

            if (monster.isDefeated()) {
                int goldDrop = monster.goldDrop();
                printGameNarrative("You defeated the Monster. The Monster drops " + goldDrop + " gold");
                printGameNarrative("You now have total of " + game.earnGold(goldDrop) + " gold");
                if (game.getKnownCount() == game.getTotalInfoCount() && !game.getWeapon().equals("Excalibur")) {
                    System.out.println();
                    printGameNarrative("*** You have found the Sword Excalibur ***");
                    printGameNarrative("Sword Excalibur equipped. Damage: " + Game.DAGGER_DAMAGE + " -> " + Game.EXCALIBUR_DAMAGE);
                    game.setWeapon("Excalibur");
                }
                printGameStoryDivider();
            }

            if (game.getHP() <= 0) break;
        }

        if (game.getHP() > 0) {
            printGameStoryDivider();
            changeLocation();
        }
    }

    /************
     ** Common **
     ************/
    public static void printLocationMenu() {
        System.out.println("1. The Town");
        System.out.println("2. The House");
        System.out.println("3. The Devil's Castle");
        System.out.println("4. The Dungeon");
        System.out.println();
    }

    public static void changeLocation() {
        printGameNarrative("Where would you like to go? (Enter Location #)");
        System.out.println();

        printLocationMenu();

        System.out.print(game.getPlayerName() + ": ");
        int loc = Integer.parseInt(scanner.nextLine());
        game.changeLocation(loc - 1);
    }

    public static void printActions() {
        int index = 1;
        for (String action : Game.ACTIONS) {
            System.out.println(index + ". " + action);
            index++;
        }
        System.out.println();
    }

    public static void printFightMenu(Monster creature) {
        System.out.println("1. Fight the " + creature);
        System.out.println("2. Flee");
        System.out.println();
    }

    public static void fight(Monster creature) {
        printGameStoryDivider();
        printGameNarrative("What action would you like to take? (Enter Action #)");
        System.out.println();
        printActions();

        System.out.print(game.getPlayerName() + ": ");
        int actionIndex = Integer.parseInt(scanner.nextLine());
        System.out.println();

        Action action = new Action(actionIndex - 1);
        Action mAction = creature.takeAction();
        if (action.compareTo(mAction) > 0) {
            printGameNarrative(game.getPlayerName() + "'s " + action.getAction() + " beats the " + creature + "'s " + mAction.getAction());
            int mHP = creature.takeDamage(game.dealDamage());
            if (mHP > 0)
                printGameNarrative("The " + creature + " has " + mHP + " HP left");
        } else if (action.compareTo(mAction) < 0) {
            printGameNarrative("The " + creature + "'s " + mAction.getAction() + " beats " + game.getPlayerName() + "'s " + action.getAction());
            takeDamage(creature.dealDamage());
        } else {
            printGameNarrative("Even round");
        }
    }

    public static void takeDamage(int damage) {
        int currentHP = game.getHP();
        int remainingHP = game.takeDamage(damage);
        printGameNarrative("HP: " + currentHP + " -> " + remainingHP);
        if (remainingHP <= 0) {
            String newLocation = game.changeLocation(1);
            printGameNarrative("You fainted. Being sent back to " + newLocation + "...");
        }
    }

    public static void printGameNarrative(String message) {
        System.out.println("[ " + message + " ]");
    }

    public static void printGameStoryDivider() {
        System.out.println("--------------------------------");
    }
}
