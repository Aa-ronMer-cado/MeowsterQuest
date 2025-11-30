package core;

import entity.player.Player;
import system.Characters;
import system.Menu;
import system.Tower;
import util.TextUtil;

public class Game {
    private Player player;
    private final Menu menuManager = new Menu();
    private final Characters characterManager = new Characters();
    private final Tower towerManager = new Tower();

    public void start() {
        mainMenu();
    }

    private void mainMenu() {
        int choice = menuManager.showMainMenu();

        if (choice == 1) {
            TextUtil.pause(1000);
            startGame();
        } else {
            System.out.println("\nThank you for visiting Pawshire!");
        }
    }

    private void startGame() {
        TextUtil.clearScreen();
        menuManager.displayIntroduction();
        player = characterManager.createCharacter();

        characterManager.showNPCEncounter(player.getName());

        boolean victory = towerManager.playTowerLevels(player);

        if (victory) victorySequence();
    }

    private void victorySequence() {
        menuManager.showVictorySequence(player.getName());
        endScreen();
    }

    private void endScreen() {
        menuManager.showEndScreen();
        mainMenu();
    }
}
