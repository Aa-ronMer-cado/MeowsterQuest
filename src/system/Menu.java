package system;

import core.Main;
import util.TextUtil;

public class Menu {

    public int showMainMenu() {
        Main.clearScreen();
        TextUtil.printTitle("MEOWSTERQUEST: THE RISE OF PAWSHIRE");

        System.out.println();
        System.out.println("MAIN MENU"); //EMOJI
        System.out.println("1. Play");
        System.out.println("2. Exit");
        System.out.print("\nChoose an option: ");

        return Main.getIntInput(1, 2);
    }

    public void displayIntroduction() {
        Main.clearScreen();
        TextUtil.printTitle("INTRODUCTION");

        String[] intro = {
            "Long, long ago, Pawshire purred in harmony...",
            "Until darkness crept in from Asonia.",
            "The Tower of Tails now imprisons our kin.",
            "A hero must rise to save them all."
        };

        for (String line : intro) {
            TextUtil.typewriterPrintCentered(line, 40);
            Main.pause(10);
        }
        System.out.println();
    }

    public void showVictorySequence(String playerName) {
        Main.clearScreen();
        TextUtil.printTitle("VICTORY!");

        TextUtil.typewriterPrintCentered("Pawshire is restored! Calm returns to the land...", 40);
        System.out.println();
        Main.pause(1500);

        TextUtil.typewriterPrintCentered("The citizens cheer for " + playerName + "!", 35);
        Main.pause(1000);

        System.out.println();
        System.out.println("[Prisoner]: \"Because of you, our kin are free at last!\""); //PUT DELAY
        Main.pause(1000);

        System.out.println("[Prisoner]: \"Pawshire will never forget your courage and sacrifice.\""); //PUT DELAY
        Main.pause(1500);

        System.out.println();
        TextUtil.printCentered("🎊 Prisoners reunite! 🎊");
        TextUtil.typewriterPrintCentered("Rowma, Necko, and Cleo are safe!", 35);
        Main.pause(2000);
    }

    public void showEndScreen() { //FIX TEXTUTIL PRINT
        Main.clearScreen();
        TextUtil.printTitle("GAME COMPLETE", 60);

        System.out.print("Rate your experience (1-5): ");
        Main.getIntInput(1, 5);

        System.out.println();
        System.out.println("THANK YOU FOR PLAYING!");

        TextUtil.typewriterPrintCentered("Your courage has guided Pawshire to freedom.", 40, 157);
        TextUtil.typewriterPrintCentered("May your journey always be filled with", 40,157);
        TextUtil.typewriterPrintCentered("bravery, wonder, and kindness.", 40, 157);

        System.out.println("\n=== CREDITS ==="); //FIX CREDITS
        System.out.println("Game Design: GROUP 1");
        System.out.println("Programming: Java OOP PROGRAMMING");
        System.out.println("Thanks for playing!");
        System.out.println("\nPress Enter to return to main menu...");
        Main.scanner.nextLine();
    }
}
