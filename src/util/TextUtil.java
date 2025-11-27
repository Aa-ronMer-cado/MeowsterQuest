package util;

public class TextUtil {

    public static final int DEFAULT_WIDTH = 157;

    public static void printCentered(String text) {
        printCentered(text, DEFAULT_WIDTH);
    }

    public static void printCentered(String text, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        System.out.println(" ".repeat(padding) + text);
    }

    public static void typewriterPrintCentered(String text, int delayMs) {
        typewriterPrintCentered(text, delayMs, DEFAULT_WIDTH);
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

    public static void printTitle(String title) {
        printTitle(title, DEFAULT_WIDTH);
    }

    public static void printTitle(String title, int width) {
        int spacing = Math.max(0, (width - title.length()) / 2);
        String space = " ".repeat(spacing);

        System.out.println("=".repeat(width));
        System.out.println(space + title);
        System.out.println("=".repeat(width));
    }


    public static void printMiddle(String title, int width) {
        int spacing = Math.max(0, (width - title.length()) / 2);
        String space = " ".repeat(spacing);

        System.out.println(space + title);
    }

    //TO DELAY TEXT
    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

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
        typewriterPrint(text, 40);
    }
}