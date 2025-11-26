package entity.player;

public enum CatColor {
    ORANGE("Radiant Burst", "300 dmg every 3 turns"),
    BLACK("Shadow Speed", "Extra turn every 3 turns"),
    WHITE("Healing Aura", "Restore 200 HP every 3 turns"),
    TILAPIA("Reflect Shield", "Deflect damage every 3 turns");

    private String ability;
    private String effect;

    CatColor(String ability, String effect) {
        this.ability = ability;
        this.effect = effect;
    }

    public String getAbility() { return ability; }
    public String getEffect() { return effect; }
}
