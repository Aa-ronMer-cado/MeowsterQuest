package entity;

import core.Main;
import util.MusicUtil;
import util.TextUtil;

public class Enemy {
    private String name;
    private int level;
    private int maxHp;
    private int currentHp;
    private int defense;
    private int[] attacks;
    private int turnCount;
    private boolean canDefend;
    private String idleAscii;   
    private String color;
    public MusicUtil music = new MusicUtil();

    public Enemy(String name, int level, int maxHp, int defense, int[] attacks, String idleAscii, String color) {
    this.name = name;
    this.level = level;
    this.maxHp = maxHp;
    this.currentHp = maxHp;
    this.defense = defense;
    this.attacks = attacks;
    this.idleAscii = idleAscii;        
    this.color = color;
    this.turnCount = 0;
    this.canDefend = level >= 2;
}


    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        currentHp -= actualDamage;
        if (currentHp < 0) currentHp = 0;

        TextUtil.typewriterPrint(name + " takes " + actualDamage + " damage! HP: " + currentHp + "/" + maxHp, music);
    }

    public int performAction() {
        turnCount++;

    // Special attack
        if (level == 3 && turnCount % 3 == 0) {
            System.out.println(idleAscii);
            TextUtil.typewriterPrint(name + " unleashes a devastating special attack!", music);
            return 250;
        }

    // Defensive
        if (canDefend && Main.random.nextInt(100) < 30) {
            TextUtil.typewriterPrint(name + " takes a defensive stance!", music);
            return 0;
        }

    // Normal attack
        int attackIndex = Main.random.nextInt(attacks.length);
        int damage = attacks[attackIndex];

        System.out.println(idleAscii);
        TextUtil.typewriterPrint(name + " attacks with force!", music);

    return damage;
}


    public void displayStats() {
        System.out.println("\n--- " + name + " Stats ---");
        System.out.println(idleAscii); // SHOW ASCII EVERY TIME ENEMY APPEARS
        System.out.println("Level: " + level);
        System.out.println("HP: " + currentHp + "/" + maxHp);
        System.out.println("DEF: " + defense);
}


    // Getters
    public String getName() { return name; }
    public int getCurrentHp() { return currentHp; }
    public int getMaxHp() { return maxHp; }
    public int getLevel() { return level; }
    public int getDefense() { return defense; }
    public int[] getAttacks() { return attacks; }
    public boolean isAlive() { return currentHp > 0; }
    public String getIdleAscii() { return idleAscii; }
    public String getColor() { return color; }
}