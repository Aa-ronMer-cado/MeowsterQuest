package entity.player;

import util.ColorUtil;

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
    
    // Get colored name
    public String ColoredName() {
        return switch (this) {
            case ORANGE -> ColorUtil.orange(this.name());
            case BLACK -> ColorUtil.grey(this.name());
            case WHITE -> this.name();  // No color
            case TILAPIA -> ColorUtil.brown(this.name());
        };
    }
    
    // Get colored ability text
    public String ColoredAbility() {
        return switch (this) {
            case ORANGE -> ColorUtil.orange(ability);
            case BLACK -> ColorUtil.grey(ability);
            case WHITE -> ability; 
            case TILAPIA -> ColorUtil.brown(ability);
        };
    }

}