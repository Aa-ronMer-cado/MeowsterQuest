package util;

public class ColorUtil {

    public static final String RESET = "\u001B[0m";
    public static final String GREY = "\u001B[90m";
    public static final String ORANGE = "\u001B[38;5;208m";
    public static final String BROWN = "\u001B[38;5;94m";

    public static void printGrey(String text) {
        System.out.println(GREY + text + RESET);
    }

    public static void printOrange(String text) {
        System.out.println(ORANGE + text + RESET);
    }

    public static void printBrown(String text) {
        System.out.println(BROWN + text + RESET);
    }

    public static String grey(String text) {
        return GREY + text + RESET;
    }

    public static String orange(String text) {
        return ORANGE + text + RESET;
    }

    public static String brown(String text) {
        return BROWN + text + RESET;
    }
}