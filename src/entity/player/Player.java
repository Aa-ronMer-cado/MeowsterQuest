package entity.player;

import combat.Attack;
import util.ColorUtil;
import util.MusicUtil;
import util.TextUtil;

public class Player {
    private String name;
    private CatBreed breed;
    private CatColor color;
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
    public MusicUtil music = new MusicUtil();

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

    public void displayStats() {
    System.out.println("\n--- " + name + " Stats ---");
    System.out.println(breed.ColoredAsciiArt(color));
    System.out.println("Breed: " + breed.name() + " (" + breed.getWeapon() + ")");
    System.out.println("Color: " + color.ColoredName() + " (" + color.ColoredAbility() + ")");
    System.out.println("HP: " + currentHp + "/" + maxHp);
    System.out.println("DEF: " + getTotalDefense());
    System.out.println("Energy: " + energy + "/" + maxEnergy);
    System.out.println("Weapon Level: " + weaponLevel);
    System.out.println("Armor Level: " + armorLevel);
    }

    public void showPlayerAttackArt() {
    String attackArt = breed.getAsciiArtAttack();

    switch (color) {
        case ORANGE -> attackArt = ColorUtil.orange(attackArt);
        case BLACK -> attackArt = ColorUtil.grey(attackArt);
        case WHITE -> { /* no color */ }
        case TILAPIA -> attackArt = ColorUtil.brown(attackArt);
    }

    System.out.println(attackArt);
    TextUtil.pause(500);
}

    public Attack[] getAttacks() {
        return breed.getAttacks();
    }

    public void takeDamage(int damage) {
        int totalDefense = getTotalDefense();
        int actualDamage = Math.max(0, damage - totalDefense);

        if (defendActive) {
            actualDamage /= 2;
            TextUtil.typewriterBlip(name + "'s defense halves the damage!",100, music);
            defendActive = false;
        }

        if (reflectActive) {
            TextUtil.typewriterBlip(name + "'s Reflect Shield deflects all damage!",100, music);
            actualDamage = 0;
            reflectActive = false;
        }

        currentHp -= actualDamage;
        if (currentHp < 0) currentHp = 0;

        TextUtil.typewriterBlip(name + " takes " + actualDamage + " damage! HP: " + currentHp + "/" + maxHp, 100, music);
    }

    public int attack(int attackIndex) {
        Attack[] attacks = breed.getAttacks();
        if (attackIndex < 0 || attackIndex >= attacks.length) {
            return 0;
        }

        Attack attack = attacks[attackIndex];

        if (energy < attack.getEnergyCost()) {
        TextUtil.typewriterBlip("Not enough energy!",100, music);
            return 0;
        }

        energy -= attack.getEnergyCost();

        double weaponMultiplier = 1.0 + (weaponLevel * 0.5);
        int damage = (int) (attack.getDamage() * weaponMultiplier);

        TextUtil.typewriterBlip(name + " uses " + attack.getName() + "!",100, music);
        return damage;
    }

    public void defend() {
        defendActive = true;
        TextUtil.typewriterBlip(name + " takes a defensive stance!", 100, music);
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
        TextUtil.typewriterBlip("\n " + color.getAbility() + " activates! ", 100,music);

        switch (color) {
            case ORANGE:
                TextUtil.typewriterBlip("Radiant energy explodes! (300 damage will be dealt)", 100,music);
                break;
            case BLACK:
                TextUtil.typewriterBlip("Shadow Speed grants an extra turn!", 100,music);
                break;
            case WHITE:
                heal(200);
                break;
            case TILAPIA:
                reflectActive = true;
                TextUtil.typewriterBlip("Reflect Shield activated for this turn!", 100, music);
                break;
        }
    }

    public void heal(int amount) {
        currentHp = Math.min(maxHp, currentHp + amount);
        TextUtil.typewriterBlip(name + " restores " + amount + " HP! HP: " + currentHp + "/" + maxHp, 100,music);
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

    public void restoreToFull() {
    currentHp = maxHp;
    energy = maxEnergy;
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
