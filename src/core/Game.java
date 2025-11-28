package core;

import entity.player.Player;
import system.Characters;
import system.Menu;
import system.Tower;

public class Game {
    private Player player;
    private final Menu menuManager;
    private final Characters characterManager;
    private final Tower towerManager;
    private final ConsoleIO io;

    public Game(Menu menuManager, Characters characterManager, Tower towerManager, ConsoleIO io) {
        this.menuManager = menuManager;
        this.characterManager = characterManager;
        this.towerManager = towerManager;
        this.io = io;
    }

    public void start() {
        mainMenu();
    }

    private void mainMenu() {
        int choice = menuManager.showMainMenu();

        if (choice == 1) {
            io.sleepMillis(1000);
            startGame();
        } else {
            io.println("\nThank you for visiting Pawshire!");
        }
    }

    private void startGame() {
        io.clearScreen();
        menuManager.displayIntroduction();
        player = characterManager.createCharacter();
        characterManager.showNPCEncounter(player.getName());
        boolean victory = towerManager.playTowerLevels(player);

        if (victory) {
            victorySequence();
        }
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
