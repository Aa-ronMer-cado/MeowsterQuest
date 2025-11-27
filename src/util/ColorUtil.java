package util;

public class ColorUtil {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String WHITE = "\u001B[37m";
    public static final String GREY = "\u001B[90m";
    public static final String ORANGE = "\u001B[38;5;208m";

    public static void printGrey(String text) {
        System.out.println(GREY + text + RESET);
    }

    public static void printOrange(String text) {
        System.out.println(ORANGE + text + RESET);
    }

    public static void printBlack(String text) {
        System.out.println(BLACK + text + RESET);
    }

    public static void printWhite(String text) {
        System.out.println(WHITE + text + RESET);
    }

    // Return colored string (for use with other methods)
    public static String grey(String text) {
        return GREY + text + RESET;
    }

    public static String orange(String text) {
        return ORANGE + text + RESET;
    }

    public static String black(String text) {
        return BLACK + text + RESET;
    }

    public static String white(String text) {
        return WHITE + text + RESET;
    }
}
