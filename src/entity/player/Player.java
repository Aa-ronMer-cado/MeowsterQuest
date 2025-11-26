package entity.player;

import combat.Attack;

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
        System.out.println("Breed: " + breed.name() + " (" + breed.getWeapon() + ")");
        System.out.println("Color: " + color.name() + " (" + color.getAbility() + ")");
        System.out.println("HP: " + currentHp + "/" + maxHp);
        System.out.println("DEF: " + getTotalDefense());
        System.out.println("Energy: " + energy + "/" + maxEnergy);
        System.out.println("Weapon Level: " + weaponLevel);
        System.out.println("Armor Level: " + armorLevel);
    }

    public Attack[] getAttacks() {
        return breed.getAttacks();
    }

    public void takeDamage(int damage) {
        int totalDefense = getTotalDefense();
        int actualDamage = Math.max(0, damage - totalDefense);

        if (defendActive) {
            actualDamage /= 2;
            System.out.println(name + "'s defense halves the damage!");
            defendActive = false;
        }

        if (reflectActive) {
            System.out.println(name + "'s Reflect Shield deflects all damage!");
            actualDamage = 0;
            reflectActive = false;
        }

        currentHp -= actualDamage;
        if (currentHp < 0) currentHp = 0;

        System.out.println(name + " takes " + actualDamage + " damage! HP: " + currentHp + "/" + maxHp);
    }

    public int attack(int attackIndex) {
        Attack[] attacks = breed.getAttacks();
        if (attackIndex < 0 || attackIndex >= attacks.length) {
            return 0;
        }

        Attack attack = attacks[attackIndex];

        if (energy < attack.getEnergyCost()) {
            System.out.println("Not enough energy!");
            return 0;
        }

        energy -= attack.getEnergyCost();

        double weaponMultiplier = 1.0 + (weaponLevel * 0.5);
        int damage = (int) (attack.getDamage() * weaponMultiplier);

        System.out.println(name + " uses " + attack.getName() + "!");
        return damage;
    }

    public void defend() {
        defendActive = true;
        System.out.println(name + " takes a defensive stance!");
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
        System.out.println("\n✨ " + color.getAbility() + " activates! ✨");

        switch (color) {
            case ORANGE:
                System.out.println("Radiant energy explodes! (300 damage will be dealt)");
                break;
            case BLACK:
                System.out.println("Shadow Speed grants an extra turn!");
                break;
            case WHITE:
                heal(200);
                break;
            case TILAPIA:
                reflectActive = true;
                System.out.println("Reflect Shield activated for this turn!");
                break;
        }
    }

    public void heal(int amount) {
        currentHp = Math.min(maxHp, currentHp + amount);
        System.out.println(name + " restores " + amount + " HP! HP: " + currentHp + "/" + maxHp);
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
    public boolean isAlive() { return currentHp > 0; }
}
