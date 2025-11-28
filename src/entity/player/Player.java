package entity.player;

import combat.Attack;
import core.ConsoleIO;
import util.ColorUtil;
import util.TextUtil;

/**
 * Player model refactored to use ConsoleIO for all I/O.
 */
public class Player {
    private final String name;
    private final CatBreed breed;
    private final CatColor color;
    private int maxHp;
    private int currentHp;
    private int defense;
    private int energy;
    private int maxEnergy;
    private int weaponLevel;
    private int armorLevel;
    private int turnCount;
    private boolean defendActive;
    private boolean reflectActive;

    public Player(String name, CatBreed breed, CatColor color) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.maxHp = 1500;
        this.currentHp = maxHp;
        this.defense = 30;
        this.maxEnergy = breed.getMaxEnergy();
        this.energy = maxEnergy;
        this.weaponLevel = 1;
        this.armorLevel = 1;
        this.turnCount = 0;
        this.defendActive = false;
        this.reflectActive = false;
    }

    public void displayStats(ConsoleIO io) {
        io.println("\n--- " + name + " Stats ---");
        io.println(breed.coloredAsciiArt(color));
        io.println("Breed: " + breed.name() + " (" + breed.getWeapon() + ")");
        io.println("Color: " + color.coloredName() + " (" + color.coloredAbility() + ")");
        io.println("HP: " + currentHp + "/" + maxHp);
        io.println("DEF: " + getTotalDefense());
        io.println("Energy: " + energy + "/" + maxEnergy);
        io.println("Weapon Level: " + weaponLevel);
        io.println("Armor Level: " + armorLevel);
    }

    public void showPlayerAttackArt(ConsoleIO io) {
        String attackArt = breed.getAsciiArtAttack();

        switch (color) {
            case ORANGE -> attackArt = ColorUtil.orange(attackArt);
            case BLACK -> attackArt = ColorUtil.grey(attackArt);
            case WHITE -> { /* no color */ }
            case TILAPIA -> attackArt = ColorUtil.brown(attackArt);
        }

        io.println(attackArt);
        io.sleepMillis(500);
    }

    public Attack[] getAttacks() {
        return breed.getAttacks();
    }

    public void takeDamage(int damage, ConsoleIO io) {
        int totalDefense = getTotalDefense();
        int actualDamage = Math.max(0, damage - totalDefense);

        if (defendActive) {
            actualDamage /= 2;
            TextUtil.typewriterPrint(io, name + "'s defense halves the damage!");
            defendActive = false;
        }

        if (reflectActive) {
            TextUtil.typewriterPrint(io, name + "'s Reflect Shield deflects all damage!");
            actualDamage = 0;
            reflectActive = false;
        }

        currentHp -= actualDamage;
        if (currentHp < 0) currentHp = 0;

        TextUtil.typewriterPrint(io, name + " takes " + actualDamage + " damage! HP: " + currentHp + "/" + maxHp);
    }

    public int attack(int attackIndex, ConsoleIO io) {
        Attack[] attacks = breed.getAttacks();
        if (attackIndex < 0 || attackIndex >= attacks.length) {
            return 0;
        }

        Attack attack = attacks[attackIndex];

        if (energy < attack.getEnergyCost()) {
            TextUtil.typewriterPrint(io, "Not enough energy!");
            return 0;
        }

        energy -= attack.getEnergyCost();

        double weaponMultiplier = 1.0 + (weaponLevel * 0.5);
        int damage = (int) (attack.getDamage() * weaponMultiplier);

        TextUtil.typewriterPrint(io, name + " uses " + attack.getName() + "!");
        return damage;
    }

    public void defend(ConsoleIO io) {
        defendActive = true;
        TextUtil.typewriterPrint(io, name + " takes a defensive stance!");
    }

    public void regenerateEnergy() {
        energy = Math.min(maxEnergy, energy + 15);
    }

    public void incrementTurn() {
        turnCount++;

        if (turnCount % 3 == 0) {
            triggerSpecialAbility();
        }
    }

    private void triggerSpecialAbility() {
        // Note: this method triggers effects that require I/O in some cases.
        // For simplicity, the I/O parts are handled by callers (BattleSystem) when needed.
    }

    public void heal(int amount, ConsoleIO io) {
        currentHp = Math.min(maxHp, currentHp + amount);
        TextUtil.typewriterPrint(io, name + " restores " + amount + " HP! HP: " + currentHp + "/" + maxHp);
    }

    public void levelUp(int level) {
        maxHp += 100;
        currentHp = maxHp;
        weaponLevel = level + 1;
        armorLevel = level + 1;
    }

    public int getTotalDefense() {
        int armorBonus = armorLevel * 5;
        return defense + armorBonus;
    }

    // Getters
    public String getName() { return name; }
    public int getCurrentHp() { return currentHp; }
    public int getMaxHp() { return maxHp; }
    public int getEnergy() { return energy; }
    public int getTurnCount() { return turnCount; }
    public CatColor getColor() { return color; }
    public CatBreed getBreed() { return breed; }
    public boolean isAlive() { return currentHp > 0; }
}
