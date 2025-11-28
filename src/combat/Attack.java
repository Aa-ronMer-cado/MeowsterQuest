package combat;

public class Attack {
    private final String name;
    private final int damage;
    private final int energyCost;

    public Attack(String name, int damage, int energyCost) {
        this.name = name;
        this.damage = damage;
        this.energyCost = energyCost;
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getEnergyCost() { return energyCost; }
}
