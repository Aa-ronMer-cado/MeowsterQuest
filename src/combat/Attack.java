package combat;

public class Attack {
    private String name;
    private int damage;
    private int energyCost;

    public Attack(String name, int damage, int energyCost) {
        this.name = name;
        this.damage = damage;
        this.energyCost = energyCost;
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getEnergyCost() { return energyCost; }
}
