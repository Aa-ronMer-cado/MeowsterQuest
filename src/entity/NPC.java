package entity;

import core.ConsoleIO;

public class NPC {
    private final String name;
    private final String role;
    private final ConsoleIO io;

    public NPC(String name, String role, ConsoleIO io) {
        this.name = name;
        this.role = role;
        this.io = io;
    }

    public void speak(String dialogue) {
        io.print("\n[" + name + "]: ");
        // simple typewriter effect using io
        for (char c : dialogue.toCharArray()) {
            io.print(String.valueOf(c));
            io.sleepMillis(35);
        }
        io.println("");
    }

    public String getName() { return name; }
    public String getRole() { return role; }
}
