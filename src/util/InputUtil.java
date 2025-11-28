package util;

import core.ConsoleIO;
public final class InputUtil {
    private InputUtil() {}

    public static int getIntInput(ConsoleIO io, int min, int max) {
        while (true) {
            try {
                String input = io.readLine().trim();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    io.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                io.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
