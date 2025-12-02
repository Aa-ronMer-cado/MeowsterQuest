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

        TextUtil.typewriterPrintCentered("Pawshire is restored! Calm returns to the land...", music);
        System.out.println();
        TextUtil.pause(1500);

        TextUtil.typewriterPrintCentered("The citizens cheer for " + playerName + "!", music);
        TextUtil.pause(1000);

        System.out.println();

        TextUtil.typewriterBlip("[Prisoner]: \"Because of you, our kin are free at last!\"", 100, music);
        TextUtil.pause(1000);

        TextUtil.typewriterBlip("[Prisoner]: \"Pawshire will never forget your courage and sacrifice.\"", 100, music);
        TextUtil.pause(700);

        System.out.println();
        TextUtil.typewriterPrintCentered("Prisoners reunite!", music);
        TextUtil.typewriterPrintCentered("Rowma, Necko, and Cleo are safe!", music);
        TextUtil.pause(2000);
    }

    public void showEndScreen() { 
        Main.clearScreen();
        TextUtil.typewriterPrintCentered("----- GAME COMPLETE ------", music);

        System.out.print("Rate your experience (1-5): ");
        Main.getIntInput(1, 5);

        System.out.println();
        TextUtil.printTitle("THANK YOU FOR PLAYING!");

        TextUtil.typewriterPrintCentered("Your courage has guided Pawshire to freedom.", music);
        TextUtil.typewriterPrintCentered("May your journey always be filled with", music);
        TextUtil.typewriterPrintCentered("bravery, wonder, and kindness.", music);

        TextUtil.typewriterPrint("\n=== CREDITS ===", music);
        TextUtil.typewriterPrint("MEO\\V3X", music);
        TextUtil.typewriterPrint("Programming: Java OOP PROGRAMMING", music);
        TextUtil.typewriterPrint("Thanks for playing!", music);
        TextUtil.typewriterPrint("\nPress Enter to return to main menu...", music);
        Main.scanner.nextLine();
    }
}
