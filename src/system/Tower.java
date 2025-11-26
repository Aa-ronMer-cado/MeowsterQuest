package system;

import combat.BattleSystem;
import core.Main;
import entity.Enemy;
import entity.player.Player;
import util.TextUtil;

public class Tower {
    public boolean battleLevel(Player player, int level, String title, String dialogue, Enemy enemy) {
        Main.clearScreen();
        TextUtil.printTitle("LEVEL " + level + " - " + title);
        TextUtil.typewriterPrintCentered(dialogue, 40); //FIX SOON
        System.out.println();
        Main.pause(1500);

        BattleSystem battle = new BattleSystem(player, enemy);
        boolean victory = battle.startBattle();

        if (victory) {
            player.levelUp(level);
            System.out.println("\n VICTORY! "); //EMOJI?
            System.out.println("+" + 100 + " Max HP");
            System.out.println("Weapon upgraded to Level " + (level + 1));
            System.out.println("Armor upgraded to Level " + (level + 1));
            Main.pause(2000);
            return true;
        } else {
            System.out.println("\n DEFEAT "); //EMOJI?
            System.out.println("Returning to checkpoint...");
            Main.pause(2000);

            // Retry the level
            return battleLevel(player, level, title, dialogue,
                                new Enemy(enemy.getName(), enemy.getLevel(), 
                                        enemy.getMaxHp(), enemy.getDefense(), 
                                        enemy.getAttacks()));
        }
    }

    public void rescuePrisoner(String name) {
        Main.clearScreen();
        System.out.println("\n" + "=".repeat(60));
        TextUtil.printCentered("You rescued " + name + "!", 60);
        System.out.println("=".repeat(60));
        Main.pause(2000);
    }

    /**
    REMEMBER TO PUT HP BACK
    Roach = 1200
    Mice = 1500
    Mordog = 2000
    ADD EMOJIS?
    **/
    public boolean playTowerLevels(Player player) {
        if (!battleLevel(player, 1, "Roach's Lair 🪳",
                        "The Tower rises. Roach awaits with venomous laughter.",
                        new Enemy("Roach", 1, 200, 0, new int[]{90, 110, 140}))) {
            return false;
        }
        rescuePrisoner("Rowma");

        if (!battleLevel(player, 2, "Miss Mice's Den ",
                        "The tunnels whisper danger. The Rat Queen stirs within.",
                        new Enemy("Miss Mice", 2, 200, 30, new int[]{80, 120, 160}))) {
            return false;
        }
        rescuePrisoner("Necko");

        if (!battleLevel(player, 3, "Mordog's Fortress ",
                        "Beyond these gates lies Mordog, the tyrant of Asonia.",
                        new Enemy("Mordog", 3, 200, 80, new int[]{100, 120, 150, 200}))) {
            return false;
        }
        rescuePrisoner("Cleo");

        return true;
    }
}
