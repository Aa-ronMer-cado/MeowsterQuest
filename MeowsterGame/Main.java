import java.util.Scanner;
import java.util.Random;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    // ==================== UTILITY METHODS ====================
    public static void clearScreen() {
        // Simple clear screen simulation for console
        System.out.println("\n\n\n");
    }

    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Prints text with a typewriter effect (letter by letter)
     * @param text The text to print
     * @param delayMs Delay in milliseconds between each character
     */
    public static void typewriterPrint(String text, int delayMs) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void typewriterPrint(String text) {
        typewriterPrint(text, 50);
    }
    public static void typewriterPrintCentered(String text, int delayMs, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        System.out.print(" ".repeat(padding));
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void typewriterPrintCentered(String text, int delayMs) {
        typewriterPrintCentered(text, delayMs, 60);
    }

    public static void printCentered(String text, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        System.out.println(" ".repeat(padding) + text);
    }

    public static int getIntInput(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    public static void displayTitle() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          MEOWSTERQUEST: THE RISE OF PAWSHIRE 🐾");
        System.out.println("=".repeat(60));
    }
}