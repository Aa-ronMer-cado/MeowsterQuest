// TextUtil.java
package util;

public class TextUtil {

    private static final String TYPING_SFX_PATH = "audio/TypingSound.wav"; // classpath resource
    public static final int DEFAULT_WIDTH = 160;
    public MusicUtil music = new MusicUtil();

    public static void typewriterPrintCentered(String text, int delayMs, int width, MusicUtil musicUtil) {
        int padding = Math.max(0, (width - text.length()) / 2);

        // start typing sound
        musicUtil.startTypingSFX(TYPING_SFX_PATH);

        System.out.print(" ".repeat(padding));
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayMs);
        }
        System.out.println();

        // ensure sound stops even if an exception occurs
        musicUtil.stopTypingSFX();
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

    public static void typewriterPrint(String text, MusicUtil musicUtil) {
        typewriterPrint(text, 40, musicUtil);
    }

    public static void printCentered(String text) {
        printCentered(text, DEFAULT_WIDTH);
    }

    public static void printCentered(String text, int width) {
        System.out.println(getCenteredText(text, width));
    }

    private static String getCenteredText(String text, int width) {
        int padding = Math.max(0, (width - text.length()) / 2);
        return " ".repeat(padding) + text;
    }

    /* ---------------------- TYPEWRITER PRINTING ---------------------- */

    public static void typewriterPrint(String text) {
        typewriterPrint(text, 40);
    }

    public static void typewriterPrint(String text, int delayMs) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayMs);
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
        System.out.println(" ".repeat(spacing) + title);
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void pause(int milliseconds) {
        sleep(milliseconds);
    }

}
