package util;

public class TextUtil {

    private static final String TYPING_SFX_PATH = "src/resource/TypeWriter.wav";
    public static final int DEFAULT_WIDTH = 160;

    /* ---------------------- CENTERED PRINT ---------------------- */

    public static void printCentered(String text) {
        printCentered(text, DEFAULT_WIDTH);
    }

    public static void printCentered(String text, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        System.out.println(" ".repeat(padding) + text);
    }

    /* ---------------------- MULTILINE CENTER ---------------------- */

    public static void printMiddle(String text) {
        printMiddle(text, DEFAULT_WIDTH);
    }

    public static void printMiddle(String text, int width) {
        for (String line : text.split("\n")) {
            int padding = Math.max(0, (width - line.length()) / 2);
            System.out.println(" ".repeat(padding) + line);
        }
    }

    /* ---------------------- TYPEWRITER PRINT WITHOUT SOUND ---------------------- */

    public static void typewriterPrintCentered(String text, int delayMs) {
        typewriterPrintCentered(text, delayMs, DEFAULT_WIDTH);
    }

    public static void typewriterPrintCentered(String text, int delayMs, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        System.out.print(" ".repeat(padding));

        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayMs);
        }

        System.out.println();
    }

    public static void typewriterPrintCenteredWithBorder(String text, int delayMs) {
        typewriterPrintCenteredWithBorder(text, delayMs, DEFAULT_WIDTH);
    }

    public static void typewriterPrintCenteredWithBorder(String text, int delayMs, int width) {
        String border = "=".repeat(width);
        int padding = Math.max(0, (width - text.length()) / 2);

        System.out.println(border);
        System.out.print(" ".repeat(padding));
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayMs);
        }
        System.out.println();
        System.out.println(border);
    }

    /* ---------------------- TYPEWRITER PRINT WITH SOUND ---------------------- */

    public static void typewriterPrint(String text, MusicUtil musicUtil) {
        typewriterPrint(text, 50, musicUtil);
    }

    public static void typewriterPrint(String text, int delayMs, MusicUtil musicUtil) {
        musicUtil.startTypingSFX(TYPING_SFX_PATH);

        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayMs);
        }

        System.out.println();
        musicUtil.stopTypingSFX();
    }

    public static void typewriterPrintCentered(String text, int delayMs, int width, MusicUtil musicUtil) {
        int padding = Math.max(0, (width - text.length()) / 2);
        musicUtil.startTypingSFX(TYPING_SFX_PATH);

        System.out.print(" ".repeat(padding));
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayMs);
        }

        System.out.println();
        musicUtil.stopTypingSFX();
    }

    public static void typewriterPrintCenteredWithBorder(String text, int delayMs, int width, MusicUtil musicUtil) {
        String border = "=".repeat(width);
        int padding = Math.max(0, (width - text.length()) / 2);

        System.out.println(border);
        musicUtil.startTypingSFX(TYPING_SFX_PATH);

        System.out.print(" ".repeat(padding));
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayMs);
        }

        System.out.println();
        musicUtil.stopTypingSFX();
        System.out.println(border);
    }

    public static void typewriterPrintCenteredWithBorder(String text, MusicUtil musicUtil) {
        typewriterPrintCenteredWithBorder(text, 40, DEFAULT_WIDTH, musicUtil);
    }

    /* ---------------------- TITLES ---------------------- */

    public static void printTitle(String title) {
        printTitle(title, DEFAULT_WIDTH);
    }

    public static void printTitle(String title, int width) {
        int padding = Math.max(0, (width - title.length()) / 2);
        String spacing = " ".repeat(padding);

        System.out.println("=".repeat(width));
        System.out.println(spacing + title);
        System.out.println("=".repeat(width));
    }

    /* ---------------------- CLEAR SCREEN ---------------------- */

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /* ---------------------- UTILITY ---------------------- */

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void pause(int ms) {
        sleep(ms);
    }
}
