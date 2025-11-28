package util;

import core.ConsoleIO;

public final class TextUtil {
    public static final int DEFAULT_WIDTH = 157;

    private TextUtil() {}

    public static void printCentered(ConsoleIO io, String text) {
        printCentered(io, text, DEFAULT_WIDTH);
    }

    public static void printCentered(ConsoleIO io, String text, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        io.println(" ".repeat(padding) + text);
    }

    public static void typewriterPrintCentered(ConsoleIO io, String text, int delayMs) {
        typewriterPrintCentered(io, text, delayMs, DEFAULT_WIDTH);
    }

    public static void typewriterPrintCentered(ConsoleIO io, String text, int delayMs, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        io.print(" ".repeat(padding));
        for (char c : text.toCharArray()) {
            io.print(String.valueOf(c));
            io.sleepMillis(delayMs);
        }
        io.println("");
    }

    public static void printTitle(ConsoleIO io, String title) {
        printTitle(io, title, DEFAULT_WIDTH);
    }

    public static void printTitle(ConsoleIO io, String title, int width) {
        int spacing = Math.max(0, (width - title.length()) / 2);
        String space = " ".repeat(spacing);

        io.println("=".repeat(width));
        io.println(space + title);
        io.println("=".repeat(width));
    }

    public static void printMiddle(ConsoleIO io, String title, int width) {
        int spacing = Math.max(0, (width - title.length()) / 2);
        String space = " ".repeat(spacing);

        io.println(space + title);
    }

    public static void pause(ConsoleIO io, int milliseconds) {
        io.sleepMillis(milliseconds);
    }

    public static void typewriterPrint(ConsoleIO io, String text, int delayMs) {
        for (char c : text.toCharArray()) {
            io.print(String.valueOf(c));
            io.sleepMillis(delayMs);
        }
        io.println("");
    }

    public static void typewriterPrint(ConsoleIO io, String text) {
        typewriterPrint(io, text, 40);
    }
}
