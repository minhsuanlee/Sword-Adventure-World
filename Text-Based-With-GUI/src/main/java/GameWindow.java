import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow {
  public static final Move[] MOVES = new Move[] {new Move(0), new Move(1), new Move(2)};
  public static JFrame window;
  public static JPanel mainTitlePanel,
      startButtonPanel,
      narrativePanel,
      initPlayerPanel,
      initPlayerSubPanel,
      choiceButtonGroupPanel,
      playerPanel;
  public static JTextField playerNameTextField;
  public static JLabel mainTitleText,
      playerNameText,
      hpTitleText,
      hpValueText,
      goldTitleText,
      goldValueText,
      weaponTitleText,
      weaponValueText;
  public static JButton startButton, submitButton, choiceButton1, choiceButton2, choiceButton3, choiceButton4;
  public static JTextArea narrativeTextArea;
  public static Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);
  public StartButtonOnClickHandler startButtonOnClickHandler = new StartButtonOnClickHandler();
  public ChoiceButtonOnClickHandler choiceButtonOnClickHandler = new ChoiceButtonOnClickHandler();
  public SubmitButtonOnClickHandler submitButtonOnClickHandler = new SubmitButtonOnClickHandler();
  private Game game;
  private Monster creature;

  public GameWindow(Game game) {
    this.game = game;

    window = new JFrame();
    window.setSize(800, 600);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setBackground(Color.BLACK);
    window.setLayout(null);

    mainTitlePanel = new JPanel();
    mainTitlePanel.setBounds(50, 150, 700, 100);
    mainTitlePanel.setBackground(Color.BLACK);
    window.add(mainTitlePanel);

    mainTitleText = new JLabel("Welcome to Sword Adventure World");
    mainTitleText.setForeground(Color.WHITE);
    mainTitleText.setFont(new Font("Times New Roman", Font.PLAIN, 44));
    mainTitlePanel.add(mainTitleText);

    startButtonPanel = new JPanel();
    startButtonPanel.setBounds(300, 350, 200, 80);
    startButtonPanel.setBackground(Color.BLACK);
    window.add(startButtonPanel);

    startButton = createButton("START");
    startButton.addActionListener(startButtonOnClickHandler);
    startButtonPanel.add(startButton);

    window.setVisible(true);
  }

  public void createPlayerScreen() {
    // Hides main screen
    window.remove(mainTitlePanel);
    window.remove(startButtonPanel);
    window.revalidate();
    window.repaint();

    narrativePanel = new JPanel();
    narrativePanel.setBounds(100, 120, 600, 225);
    narrativePanel.setBackground(Color.BLACK);
    window.add(narrativePanel);

    narrativeTextArea = new JTextArea("Narrative Text Area");
    narrativeTextArea.setBounds(125, 120, 550, 250);
    narrativeTextArea.setBackground(Color.BLACK);
    narrativeTextArea.setForeground(Color.WHITE);
    narrativeTextArea.setFont(normalFont);
    narrativeTextArea.setLineWrap(true);
    narrativeTextArea.setEditable(false);
    narrativeTextArea.setWrapStyleWord(true);
    narrativePanel.add(narrativeTextArea);

    initPlayerPanel = new JPanel();
    initPlayerPanel.setBounds(200, 370, 400, 100);
    initPlayerPanel.setBackground(Color.BLACK);
    initPlayerPanel.setLayout(new GridLayout(2, 1));
    window.add(initPlayerPanel);

    initPlayerSubPanel = new JPanel();
    initPlayerSubPanel.setBackground(Color.BLACK);
    initPlayerSubPanel.setLayout(new GridLayout(1, 2));
    initPlayerPanel.add(initPlayerSubPanel);

    playerNameText = new JLabel("My name is ");
    playerNameText.setFont(normalFont);
    playerNameText.setForeground(Color.WHITE);
    initPlayerSubPanel.add(playerNameText);

    playerNameTextField = new JTextField();
    playerNameTextField.setFont(normalFont);
    initPlayerSubPanel.add(playerNameTextField);

    submitButton = createButton("Confirm");
    submitButton.addActionListener(submitButtonOnClickHandler);
    submitButton.setActionCommand("Submit");
    initPlayerPanel.add(submitButton);

    initPlayer();
  }

  public void initPlayer() {
    narrativeTextArea.setText("The Guard:\n\nHey, I've never seen you before.\n\nWhat is your name?");
  }

  public void createGameScreen() {
    window.remove(initPlayerPanel);
    window.revalidate();
    window.repaint();

    playerPanel = new JPanel();
    playerPanel.setBounds(50, 50, 700, 50);
    playerPanel.setBackground(Color.BLACK);
    playerPanel.setLayout(new GridLayout(1, 6));
    window.add(playerPanel);

    hpTitleText = new JLabel("HP:");
    hpTitleText.setFont(normalFont);
    hpTitleText.setForeground(Color.WHITE);
    playerPanel.add(hpTitleText);

    hpValueText = new JLabel();
    hpValueText.setFont(normalFont);
    hpValueText.setForeground(Color.WHITE);
    playerPanel.add(hpValueText);

    goldTitleText = new JLabel("Gold #:");
    goldTitleText.setFont(normalFont);
    goldTitleText.setForeground(Color.WHITE);
    playerPanel.add(goldTitleText);

    goldValueText = new JLabel();
    goldValueText.setFont(normalFont);
    goldValueText.setForeground(Color.WHITE);
    playerPanel.add(goldValueText);

    weaponTitleText = new JLabel("Weapon:");
    weaponTitleText.setFont(normalFont);
    weaponTitleText.setForeground(Color.WHITE);
    playerPanel.add(weaponTitleText);

    weaponValueText = new JLabel();
    weaponValueText.setFont(normalFont);
    weaponValueText.setForeground(Color.WHITE);
    playerPanel.add(weaponValueText);

    choiceButtonGroupPanel = new JPanel();
    choiceButtonGroupPanel.setBounds(250, 375, 300, 150);
    choiceButtonGroupPanel.setBackground(Color.BLACK);
    choiceButtonGroupPanel.setLayout(new GridLayout(4, 1));
    window.add(choiceButtonGroupPanel);

    choiceButton1 = createButton("Choice 1");
    choiceButton1.addActionListener(choiceButtonOnClickHandler);
    choiceButton1.setActionCommand("C1");

    choiceButton2 = createButton("Choice 2");
    choiceButton2.addActionListener(choiceButtonOnClickHandler);
    choiceButton2.setActionCommand("C2");

    choiceButton3 = createButton("Choice 3");
    choiceButton3.addActionListener(choiceButtonOnClickHandler);
    choiceButton3.setActionCommand("C3");

    choiceButton4 = createButton("Choice 4");
    choiceButton4.addActionListener(choiceButtonOnClickHandler);
    choiceButton4.setActionCommand("C4");

    choiceButtonGroupPanel.add(choiceButton1);
    choiceButtonGroupPanel.add(choiceButton2);
    choiceButtonGroupPanel.add(choiceButton3);
    choiceButtonGroupPanel.add(choiceButton4);

    playerSetup();
  }

  public void playerSetup() {
    hpValueText.setText("" + game.getHP());
    goldValueText.setText("" + game.getGoldCount());
    weaponValueText.setText(game.getWeapon());

    townSetup();
  }

  public void townSetup() {
    game.changeLocation(0);
    game.setLocationChoice(0);

    narrativeTextArea.setText(
        "You are standing in front of the gate of the Town.\n\n"
            + "There is a Guard standing in front of you.\n\n"
            + "What would you like to do?");

    choiceButton1.setText("Try to enter the Town");
    choiceButton2.setText("Buy info from the Guard");
    choiceButton3.setText("Attack the Guard");
    choiceButton4.setText("Walk away");
    showChoiceBtn2To4();
  }

  public void tryEnterTown() {
    if (game.isDevilDefeated()) {
      game.setLocationChoice(5);
      narrativeTextArea.setText(
          "The Guard:\n\n"
              + game.getPlayerName()
              + ", you are the Hero!\n\nPlease come in!\n\nYou are more than welcome here in the Town!");
      game.setIsGameOver();
      choiceButton1.setText("Enter the Town");
    } else {
      game.setLocationChoice(1);
      narrativeTextArea.setText(
          "The Guard:\n\n"
              + "Wow wow wow... slow down there \""
              + game.getPlayerName()
              + "\".\n\n"
              + "No strangers are allowed into the Town!");
      choiceButton1.setText("CONTINUE    >");
    }
    hideChoiceBtn2To4();
  }

  public void tryBuyInfo() {
    game.setLocationChoice(2);
    if (game.getGoldCount() > 0) {
      if (game.getKnownCount() == game.getTotalInfoCount()) {
        narrativeTextArea.setText(
            "The Guard:\n\n"
                + "I've already given you all the information that I had.\n\n"
                + "Thanks for the gold though. Ha ha ha ha!");
      } else {
        narrativeTextArea.setText("The Guard:\n\n" + game.unlockInfo());
      }
      goldValueText.setText("" + game.buyInformation());
    } else {
      narrativeTextArea.setText(
          "The Guard:\n\n"
              + "I don't just give away information like that.\n\n"
              + "Try coming back when you have some gold on you.");
    }
    choiceButton1.setText("CONTINUE    >");
    hideChoiceBtn2To4();
  }

  public void attackGuard() {
    game.setLocationChoice(3);
    narrativeTextArea.setText("The Guard:\n\nWhat are you thinking!?\n\nHow dare you attacked me!");
    takeDamage(1);
    choiceButton1.setText("CONTINUE    >");
    hideChoiceBtn2To4();
  }

  public void walkAway() {
    game.changeLocation(1);
    game.setLocationChoice(0);
    game.setDemonDefeated(0);

    narrativeTextArea.setText("You are at the Cross Road.\n\nWhere would you like to go?");
    choiceButton1.setText("The Town");
    choiceButton2.setText("The House");
    choiceButton3.setText("The Devil's Castle");
    choiceButton4.setText("The Dungeon");
    showChoiceBtn2To4();
  }

  public void houseSetup(boolean isFainted) {
    String newLocation = game.changeLocation(2);
    game.setLocationChoice(0);
    String narrative =
        isFainted
            ? "You fainted. Being sent back to " + newLocation + ".."
            : "You are back to " + game.getLocation();
    narrative += ".\n\nAfter a good night sleep, your HP is restored back to full health.";
    narrativeTextArea.setText(narrative);
    game.restoreFullHP();
    hpValueText.setText("" + game.getHP());
    choiceButton1.setText("CONTINUE    >");
    hideChoiceBtn2To4();
  }

  public void devilsCastleSetup() {
    game.changeLocation(3);
    game.setLocationChoice(0);

    String narrative = "You sneaked into the Devil's Castle.\n\n";

    if (game.isDevilDefeated()) {
      narrative += "The Devil has been defeated";
      choiceButton1.setText("CONTINUE    >");
      hideChoiceBtn2To4();
    } else if (game.getDemonDefeated() < 2) {
      creature = new Demon();

      narrative +=
          "Demon " + (game.getDemonDefeated() + 1) + " appears.\n\nWhat would you like to do?";

      choiceButton1.setText("Fight the Demon");
      choiceButton2.setText("Run Away");

      choiceButton2.setVisible(true);
      choiceButton3.setVisible(false);
      choiceButton4.setVisible(false);
    } else if (!game.getWeapon().equals("Excalibur")) {
      narrative += "The Devil appears\n\nThe Devil:\n\nYou are TOO weak.";
      takeDamage(Game.FULL_HP);
    } else {
      creature = new Devil();
      narrative += "The Devil appears.\n\nWhat would you like to do?";

      choiceButton1.setText("Fight the Devil");
      choiceButton2.setText("Run Away");

      choiceButton2.setVisible(true);
      choiceButton3.setVisible(false);
      choiceButton4.setVisible(false);
    }

    narrativeTextArea.setText(narrative);
  }

  public void receiveGoddessBlessing() {
    game.restoreFullHP();
    hpValueText.setText("" + Game.FULL_HP);

    narrativeTextArea.setText(
        "The Goddess:\n\nThank you, "
            + game.getPlayerName()
            + ", for travelling this far.\nI will now restore all your HP and good luck.\n\nThe Goddess healed you.");

    game.setLocationChoice(3);

    choiceButton1.setText("CONTINUE    >");
    hideChoiceBtn2To4();
  }

  public void dungeonSetup() {
    game.changeLocation(4);
    game.setLocationChoice(0);

    creature = new Monster();

    narrativeTextArea.setText(
        "You entered the Dungeon.\n\nA Monster appears.\n\nWhat would you like to do?");
    choiceButton1.setText("Fight the Monster");
    choiceButton2.setText("Run Away");

    choiceButton2.setVisible(true);
    choiceButton3.setVisible(false);
    choiceButton4.setVisible(false);
  }

  public void fightSetup() {
    game.setLocationChoice(1);

    if (!creature.isDefeated()) {
      narrativeTextArea.setText(
          creature
              + "'s HP: "
              + creature.getHP()
              + "\n\nWhat action would you like to take to fight the "
              + creature
              + "?");

      choiceButton1.setText("Rock");
      choiceButton2.setText("Paper");
      choiceButton3.setText("Scissors");

      choiceButton2.setVisible(true);
      choiceButton3.setVisible(true);
    } else {
      String narrative = "";
      if (creature instanceof Demon) {
        game.setDemonDefeated(game.getDemonDefeated() + 1);
        narrative = "You defeated Demon " + game.getDemonDefeated() + ".";
        if (game.getDemonDefeated() == 2) {
          game.setLocationChoice(5);
        } else {
          game.setLocationChoice(3);
        }
      } else if (creature instanceof Devil) {
        game.setDevilDefeated();
        narrative = "You defeated the Devil!";
        game.setLocationChoice(4);
      } else { // Monster
        int goldDrop = creature.goldDrop();
        narrative = "You defeated the Monster.\n\nThe Monster drops " + goldDrop + " gold.\n\n";
        goldValueText.setText("" + game.earnGold(goldDrop));
        if (game.getKnownCount() == game.getTotalInfoCount()
            && !game.getWeapon().equals("Excalibur")) {
          narrative +=
              "*** You have found the Sword Excalibur ***\n\n"
                  + "Sword Excalibur equipped. Damage: "
                  + Game.DAGGER_DAMAGE
                  + " => "
                  + Game.EXCALIBUR_DAMAGE;
          game.setWeapon("Excalibur");
          weaponValueText.setText(game.getWeapon());
        }
        //        creature = new Monster();
        game.setLocationChoice(3);
      }
      narrativeTextArea.setText(narrative);

      choiceButton1.setText("CONTINUE    >");
      hideChoiceBtn2To4();
    }
  }

  public void roundResult(Move playerMove) {
    game.setLocationChoice(2);

    Move creatureMove = creature.makeMove();
    if (playerMove.compareTo(creatureMove) > 0) {
      int mHP = creature.takeDamage(game.dealDamage());

      String narrative = mHP > 0 ? creature + "'s HP: " + creature.getHP() + "\n\n" : "";
      narrative +=
          game.getPlayerName()
              + "'s "
              + playerMove.getAction()
              + " beats the "
              + creature
              + "'s "
              + creatureMove.getAction()
              + ".";

      narrativeTextArea.setText(narrative);
    } else if (playerMove.compareTo(creatureMove) < 0) {
      int damage = creature.dealDamage();
      narrativeTextArea.setText(
          "The "
              + creature
              + "'s "
              + creatureMove.getAction()
              + " beats "
              + game.getPlayerName()
              + "'s "
              + playerMove.getAction()
              + ".\n\nYou lost "
              + damage
              + " HP.");
      takeDamage(damage);
    } else {
      narrativeTextArea.setText("Even round");
    }

    choiceButton1.setText("CONTINUE    >");
    hideChoiceBtn2To4();
  }

  public void takeDamage(int damage) {
    int remainingHP = game.takeDamage(damage);
    hpValueText.setText("" + remainingHP);
    if (remainingHP <= 0) {
      houseSetup(true);
    }
  }

  public void endOfGame() {
    game.setLocationChoice(6);
    narrativeTextArea.setText("END OF GAME");
    choiceButton1.setText("Start Over");
    playerPanel.setVisible(false);
  }

  public void gameReset() {
    game.setLocationChoice(0);
    game.reset();

    window.remove(narrativePanel);
    window.remove(choiceButtonGroupPanel);
    window.remove(playerPanel);

    window.add(mainTitlePanel);
    window.add(startButtonPanel);

    window.revalidate();
    window.repaint();
  }

  public void hideChoiceBtn2To4() {
    choiceButton2.setVisible(false);
    choiceButton3.setVisible(false);
    choiceButton4.setVisible(false);
  }

  public void showChoiceBtn2To4() {
    choiceButton2.setVisible(true);
    choiceButton3.setVisible(true);
    choiceButton4.setVisible(true);
  }

  public JButton createButton(String text) {
    JButton button = new JButton(text);
    button.setBackground(Color.BLACK);
    button.setForeground(Color.WHITE);
    button.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            BorderFactory.createLineBorder(Color.BLACK, 5)));
    button.setFont(normalFont);
    button.setFocusPainted(false);

    return button;
  }

  public class StartButtonOnClickHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      createPlayerScreen();
    }
  }

  public class SubmitButtonOnClickHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();
      if (command.equals("Submit")) {
        game.setPlayerName(playerNameTextField.getText());
        playerNameTextField.setText("");
        createGameScreen();
      }
    }
  }

  public class ChoiceButtonOnClickHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String choice = e.getActionCommand();

      switch (game.getLocationIndex()) {
        case 0:
          // the Town
          switch (game.getLocationChoice()) {
            case "ENTER_TOWN":
            case "BUY_INFO":
            case "ATTACK_GUARD":
            case "WALK_AWAY":
              townSetup();
              break;
            case "END_OF_GAME":
              endOfGame();
              break;
            case "START_OVER":
              gameReset();
              break;
            default:
              switch (choice) {
                case "C1":
                  tryEnterTown();
                  break;
                case "C2":
                  tryBuyInfo();
                  break;
                case "C3":
                  attackGuard();
                  break;
                default: // C4
                  walkAway();
              }
          }
          break;
        case 1:
          // the Cross Road
          switch (choice) {
            case "C1":
              townSetup();
              break;
            case "C2":
              houseSetup(false);
              break;
            case "C3":
              devilsCastleSetup();
              break;
            default: // C4
              dungeonSetup();
          }
          break;
        case 2:
          // the House
          walkAway();
          break;
        case 3:
          // the Devil's Castle
          switch (game.getLocationChoice()) {
            case "FIGHT":
              switch (choice) {
                case "C1": // Rock
                  roundResult(MOVES[0]);
                  break;
                case "C2": // Paper
                  roundResult(MOVES[1]);
                  break;
                default: // C3, Scissor
                  roundResult(MOVES[2]);
              }
              break;
            case "HEAL":
              receiveGoddessBlessing();
              break;
            case "WON":
              devilsCastleSetup();
              break;
            case "DEVIL_DEFEATED":
              walkAway();
              break;
            default:
              if (choice.equals("C1")) {
                fightSetup();
              } else { // C2, Run Away
                walkAway();
              }
          }
          break;
        default:
          // the Dungeon
          switch (game.getLocationChoice()) {
            case "FIGHT":
              switch (choice) {
                case "C1": // Rock
                  roundResult(MOVES[0]);
                  break;
                case "C2": // Paper
                  roundResult(MOVES[1]);
                  break;
                default: // C3, Scissor
                  roundResult(MOVES[2]);
              }
              break;
            case "WON":
              dungeonSetup();
              break;
            case "ROUND":
              fightSetup();
              break;
            default: // DUNGEON
              if (choice.equals("C1")) {
                fightSetup();
              } else { // C2, Run Away
                walkAway();
              }
          }
      }
    }
  }
}
