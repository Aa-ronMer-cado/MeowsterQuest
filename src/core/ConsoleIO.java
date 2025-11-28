package core;

public interface ConsoleIO {
    void println(String s);
    void print(String s);
    String readLine();
    void sleepMillis(long ms);
    void clearScreen();
}