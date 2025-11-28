package system;

import core.ConsoleIO;
import util.InputUtil;
import util.TextUtil;

public class Menu {
    private final ConsoleIO io;

    public Menu(ConsoleIO io) {
        this.io = io;
    }

    public int showMainMenu() {
        io.clearScreen();
        TextUtil.printTitle(io, "MEOWSTERQUEST: THE RISE OF PAWSHIRE");

        io.println("");
        io.println("MAIN MENU");
        io.println("1. Play");
        io.println("2. Exit");
        io.print("\nChoose an option: ");

        return InputUtil.getIntInput(io, 1, 2);
    }

    public void displayIntroduction() {
        io.clearScreen();
        TextUtil.printTitle(io, "INTRODUCTION");

        String[] intro = {
            "Long, long ago, Pawshire purred in harmony...",
            "Until darkness crept in from Asonia.",
            "The Tower of Tails now imprisons our kin.",
            "A hero must rise to save them all."
        };

        for (String line : intro) {
            TextUtil.typewriterPrintCentered(io, line, 40);
            io.sleepMillis(10);
        }
        io.println("");
    }

    public void showVictorySequence(String playerName) {
        io.clearScreen();
        TextUtil.printTitle(io, "VICTORY!");

        TextUtil.typewriterPrintCentered(io, "Pawshire is restored! Calm returns to the land...", 40);
        io.println("");
        io.sleepMillis(1500);

        TextUtil.typewriterPrintCentered(io, "The citizens cheer for " + playerName + "!", 35);
        io.sleepMillis(1000);

        io.println("");
        TextUtil.typewriterPrint(io, "[Prisoner]: \"Because of you, our kin are free at last!\"", 40);
        io.sleepMillis(1000);

        TextUtil.typewriterPrint(io, "[Prisoner]: \"Pawshire will never forget your courage and sacrifice.\"", 40);
        io.sleepMillis(1500);

        io.println("");
        TextUtil.typewriterPrintCentered(io, " Prisoners reunite! ", 40);
        TextUtil.typewriterPrintCentered(io, "Rowma, Necko, and Cleo are safe!", 35);
        io.sleepMillis(2000);
    }

    public void showEndScreen() {
        io.clearScreen();
        TextUtil.printMiddle(io, "----- GAME COMPLETE ------", 157);

        io.print("Rate your experience (1-5): ");
        InputUtil.getIntInput(io, 1, 5);

        io.println("");
        TextUtil.printTitle(io, "THANK YOU FOR PLAYING!");

        TextUtil.typewriterPrintCentered(io, "Your courage has guided Pawshire to freedom.", 40, 157);
        TextUtil.typewriterPrintCentered(io, "May your journey always be filled with", 40,157);
        TextUtil.typewriterPrintCentered(io, "bravery, wonder, and kindness.", 40, 157);

        TextUtil.typewriterPrint(io, "\n=== CREDITS ===", 100);
        TextUtil.typewriterPrint(io, "Game Design: GROUP 1", 100);
        TextUtil.typewriterPrint(io, "Programming: Java OOP PROGRAMMING", 100);
        TextUtil.typewriterPrint(io, "Thanks for playing!", 100);
        TextUtil.typewriterPrint(io, "\nPress Enter to return to main menu...", 100);
        io.readLine();
    }
}
