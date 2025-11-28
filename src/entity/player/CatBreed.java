package entity.player;

import combat.Attack;
import util.ColorUtil;

public enum CatBreed {
    PERSIAN("Enchanted Book", "Mystic Robe", 150,
        new Attack[]{
            new Attack("Fin Swipe", 90, 0),
            new Attack("Water Whip", 150, 30),
            new Attack("Tidal Slam", 220, 80)
        },
        """
             /\\_____ /\\
            /  o   o  \\
           ( ==  ^  == )
            )         (
           (           )
         ( (  )     (  ) )
        (__ (__ )___(__)__)
        """,
        """
        /|  /\\
        ( ΦωΦ ) ﾉﾉ
        /  つ つ
        """
    ),

    RAGDOLL(
        "Chakrams",
        "Hunter's Vest",
        120,
        new Attack[]{
            new Attack("Scratch", 90, 5),
            new Attack("Pounce", 130, 25),
            new Attack("Roaring Swipe", 180, 60)
        },
        """
             |\\---/|
             | ,_, |
              \\_`_/-..----.
           ___/ `   ' ,""+ \\ 
          (__...'   __\\    |`.___.';
           (_,...'(_,.`__)/'..
        """,
        """
        /|  /\\
        ( ΦωΦ ) ﾉﾉ
        /  つ つ
        """
    ),

    PUSKAL("Shield", "Gauntlets of Valor", 100,
        new Attack[]{
            new Attack("Heavy Hit", 80, 0),
            new Attack("Iron Paw", 120, 20),
            new Attack("Crushing Tail", 170, 50)
        },
        """
            __._     _,-'""`-._
            (,-.`._,'(       |\\`-/|
                `-.-' \\ )-`( , o o)
                    `-    \\`_`"'-
        """,
        """
        /|  /\\
        ( ΦωΦ ) ﾉﾉ
        /  つ つ
        """
    );

    private final String weapon;
    private final String armor;
    private final int maxEnergy;
    private final Attack[] attacks;
    private final String asciiArtIdle;
    private final String asciiArtAttack;

    CatBreed(String weapon, String armor, int maxEnergy, Attack[] attacks, String asciiArtIdle, String asciiArtAttack) {
        this.weapon = weapon;
        this.armor = armor;
        this.maxEnergy = maxEnergy;
        this.attacks = attacks.clone();
        this.asciiArtIdle = asciiArtIdle;
        this.asciiArtAttack = asciiArtAttack;
    }

    public String getWeapon() { return weapon; }
    public String getArmor() { return armor; }
    public int getMaxEnergy() { return maxEnergy; }
    public Attack[] getAttacks() { return attacks.clone(); }
    public String getAsciiArtIdle() { return asciiArtIdle; }
    public String getAsciiArtAttack() { return asciiArtAttack; }

    public String coloredAsciiArt(CatColor color) {
        return switch (color) {
            case ORANGE -> ColorUtil.orange(asciiArtIdle);
            case BLACK -> ColorUtil.grey(asciiArtIdle);
            case WHITE -> asciiArtIdle;
            case TILAPIA -> ColorUtil.brown(asciiArtIdle);
        };
    }

    public String coloredAsciiAttackArt(CatColor color) {
        return switch (color) {
            case ORANGE -> ColorUtil.orange(asciiArtAttack);
            case BLACK -> ColorUtil.grey(asciiArtAttack);
            case WHITE -> asciiArtAttack;
            case TILAPIA -> ColorUtil.brown(asciiArtAttack);
        };
    }
}
