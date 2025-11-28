package core;

import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public static void clearScreen() {
        System.out.println("\n");
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

    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
