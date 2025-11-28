package entity;

import core.ConsoleIO;
import java.util.Random;


public class Enemy {
    private final String name;
    private final int level;
    private final int maxHp;
    private int currentHp;
    private final int defense;
    private final int[] attacks; // damage values for simple AI
    private final String idleAscii;
    private final String color; // textual color name for display

    public Enemy(String name, int level, int maxHp, int defense, int[] attacks, String idleAscii, String color) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.defense = defense;
        this.attacks = attacks.clone();
        this.idleAscii = idleAscii;
        this.color = color;
    }

    public void displayStats(ConsoleIO io) {
        io.println("\n--- " + name + " Stats ---");
        if (idleAscii != null && !idleAscii.isEmpty()) {
            io.println(idleAscii);
        }
        io.println("Level: " + level);
        io.println("HP: " + currentHp + "/" + maxHp);
        io.println("DEF: " + defense);
    }

    public int performAction() {
        // Simple AI: pick a random attack damage from attacks array
        if (attacks.length == 0) return 0;
        int idx = new Random().nextInt(attacks.length);
        return attacks[idx];
    }

    public void takeDamage(int damage) {
        int actual = Math.max(0, damage - defense);
        currentHp -= actual;
        if (currentHp < 0) currentHp = 0;
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    // Getters used by Tower when recreating enemy
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getMaxHp() { return maxHp; }
    public int getDefense() { return defense; }
    public int[] getAttacks() { return attacks.clone(); }
    public String getIdleAscii() { return idleAscii; }
    public String getColor() { return color; }
}
