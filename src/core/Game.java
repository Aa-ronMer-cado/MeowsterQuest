package core;

import entity.player.Player;
import system.Characters;
import system.Menu;
import system.Tower;
import util.MusicUtil;
import util.TextUtil;

public class Game {
    private Player player;
    private Menu menuManager;
    private Characters characterManager;
    private Tower towerManager;
    public MusicUtil music = new MusicUtil();

    public Game() {
        this.menuManager = new Menu();
        this.characterManager = new Characters();
        this.towerManager = new Tower();
    }

    public void start() {
        Main.clearScreen();
        mainMenu();
    }

    private void mainMenu() {
        music.playIntroBGM("src/resource/BGMforWholeGame.wav");
        TextUtil.typewriterBlipCentered("LOADING GAME.......", 300, 160, music);
        TextUtil.pause(700);

        int choice = menuManager.showMainMenu();

        if (choice == 1) {
            TextUtil.pause(1000);
            startGame();
        } else {
            System.out.println("\nThank you for visiting Pawshire!");
        }
    }

    private void startGame() {
        Main.clearScreen();
        //menuManager.displayIntroduction();
        player = characterManager.createCharacter();
        //characterManager.showNPCEncounter(player.getName());
        music.stopBGM();
        boolean victory = towerManager.playTowerLevels(player);
        music.stopBGM();

        if (victory) {
            TextUtil.pause(1000);
            music.playIntroBGM("src/resource/BGMforWholeGame.wav");
            victorySequence();
        }
    }

    private void victorySequence() {
        menuManager.showVictorySequence(player.getName());
        endScreen();
    }

    private void endScreen() {
        menuManager.showEndScreen();
        music.stopBGM();
        mainMenu();
    }
}