package entity.player;

import util.ColorUtil;

public enum CatColor {
    ORANGE("Radiant Burst", "300 dmg every 3 turns"),
    BLACK("Shadow Speed", "Extra turn every 3 turns"),
    WHITE("Healing Aura", "Restore 200 HP every 3 turns"),
    TILAPIA("Reflect Shield", "Deflect damage every 3 turns");

    private final String ability;
    private final String effect;

    CatColor(String ability, String effect) {
        this.ability = ability;
        this.effect = effect;
    }

    public String getAbility() { return ability; }
    public String getEffect() { return effect; }

    public String coloredName() {
        return switch (this) {
            case ORANGE -> ColorUtil.orange(this.name());
            case BLACK -> ColorUtil.grey(this.name());
            case WHITE -> this.name();
            case TILAPIA -> ColorUtil.brown(this.name());
        };
    }

    public String coloredAbility() {
        return switch (this) {
            case ORANGE -> ColorUtil.orange(ability);
            case BLACK -> ColorUtil.grey(ability);
            case WHITE -> ability;
            case TILAPIA -> ColorUtil.brown(ability);
        };
    }
}
