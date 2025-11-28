package util;

public final class ColorUtil {
    public static final String RESET = "\u001B[0m";
    public static final String GREY = "\u001B[90m";
    public static final String ORANGE = "\u001B[38;5;208m";
    public static final String BROWN = "\u001B[38;5;94m";

    private ColorUtil() {}

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
