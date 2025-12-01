package system;

import core.Main;
import util.TextUtil;
import util.MusicUtil;

public class Menu {

    public MusicUtil music = new MusicUtil();
    
    public int showMainMenu() {
        Main.clearScreen();
        //music.playBGM("src/resource/BGMforWholeGame.wav");
        TextUtil.printTitle("MEOWSTERQUEST: THE RISE OF PAWSHIRE");

        System.out.println();
        System.out.println("MAIN MENU"); 
        System.out.println("1. Play");
        System.out.println("2. Exit");
        System.out.print("\nChoose an option: ");

        return Main.getIntInput(1, 2);
    }

    public void displayIntroduction() {
        TextUtil.clearScreen();
        TextUtil.printTitle("INTRODUCTION");

        String[] intro = {
            "Long, long ago, Pawshire purred in harmony...",
            "Until darkness crept in from Asonia.",
            "The Tower of Tails now imprisons our kin.",
            "A hero must rise to save them all."
        };

        for (String line : intro) {
            TextUtil.typewriterPrintCentered(line, 100, 160, music);
            TextUtil.pause(1000);
        }
        System.out.println();

        TextUtil.printMiddle("""
                                                |>>>
                                                |
                                            _  _|_  _        
                                           |;|_|;|_|;|
                                           \\.    .  /
                                            \\:  .  /
                                             ||:   |
                                             ||:.  |
                                             ||:  .|
                                             ||:   |       \\,//
                                             ||: , |            //`\\
                                             ||:   |
                                             ||: . |
              __                            _||_   |
     ____--`~    '--~~__            __ ----~    ~`---,              ___
-~--~                   ~---__ ,--~'                  ~~----_____-~'   `~----~~
            """, 100);
    }

    public void showVictorySequence(String playerName) {
        Main.clearScreen();
        music.playSFX("src/resource/VictorySound.wav");
        TextUtil.printTitle("VICTORY!");

        TextUtil.printCentered("Pawshire is restored! Calm returns to the land...");
        System.out.println();
        TextUtil.pause(1500);

        TextUtil.printCentered("The citizens cheer for " + playerName + "!");
        TextUtil.pause(1000);

        System.out.println();

        TextUtil.typewriterPrint("[Prisoner]: \"Because of you, our kin are free at last!\"", music);
        TextUtil.pause(1000);

        TextUtil.typewriterPrint("[Prisoner]: \"Pawshire will never forget your courage and sacrifice.\"", music);
        TextUtil.pause(700);

        System.out.println();
        TextUtil.printCentered("Prisoners reunite!");
        TextUtil.printCentered("Rowma, Necko, and Cleo are safe!");
        TextUtil.pause(2000);
    }

    public void showEndScreen() { 
        Main.clearScreen();
        TextUtil.printCentered("----- GAME COMPLETE ------");

        System.out.print("Rate your experience (1-5): ");
        Main.getIntInput(1, 5);

        System.out.println();
        TextUtil.printTitle("THANK YOU FOR PLAYING!");

        TextUtil.printCentered("Your courage has guided Pawshire to freedom.");
        TextUtil.printCentered("May your journey always be filled with");
        TextUtil.printCentered("bravery, wonder, and kindness.");

        TextUtil.typewriterPrint("\n=== CREDITS ===", music);
        TextUtil.typewriterPrint("MEO\\V3X", music);
        TextUtil.typewriterPrint("Programming: Java OOP PROGRAMMING", music);
        TextUtil.typewriterPrint("Thanks for playing!", music);
        TextUtil.typewriterPrint("\nPress Enter to return to main menu...", music);
        Main.scanner.nextLine();
    }
}
