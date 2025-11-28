package core;


import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIOSystem implements ConsoleIO {
    private final Scanner scanner;
    private final PrintStream out;

    public ConsoleIOSystem(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    @Override
    public void println(String s) {
        out.println(s);
    }

    @Override
    public void print(String s) {
        out.print(s);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void sleepMillis(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void clearScreen() {
        out.println();
    }
}

