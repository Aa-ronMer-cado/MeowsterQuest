package system;

import combat.BattleSystem;
import core.ConsoleIO;
import entity.Enemy;
import entity.player.Player;
import util.TextUtil;

/**
 * Tower orchestrates levels. It uses ConsoleIO for I/O and avoids recursion for retries.
 */
public class Tower {
    private final ConsoleIO io;
    private static final int MAX_RETRIES = 3;

    public Tower(ConsoleIO io) {
        this.io = io;
    }

    public boolean battleLevel(Player player, int level, String title, String dialogue, Enemy enemy) {
        io.clearScreen();
        TextUtil.printTitle(io, "LEVEL " + level + " - " + title);
        TextUtil.typewriterPrintCentered(io, dialogue, 40);
        System.out.println();
        io.sleepMillis(1000);

        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            BattleSystem battle = new BattleSystem(player, enemy, io);
            boolean victory = battle.startBattle();

            if (victory) {
                player.levelUp(level);
                TextUtil.typewriterPrint(io, "\n✨ VICTORY! ✨");
                TextUtil.typewriterPrint(io, "+" + 100 + " Max HP");
                TextUtil.typewriterPrint(io, "Weapon upgraded to Level " + (level + 1));
                TextUtil.typewriterPrint(io, "Armor upgraded to Level " + (level + 1));
                io.sleepMillis(1000);
                return true;
            } else {
                attempts++;
                TextUtil.typewriterPrint(io, "\n💀 DEFEAT 💀");
                io.sleepMillis(1500);
                if (attempts < MAX_RETRIES) {
                    TextUtil.typewriterPrint(io, "Retrying level... (" + attempts + "/" + MAX_RETRIES + ")");
                    io.sleepMillis(1000);
                    // recreate enemy fresh for retry
                    enemy = new Enemy(enemy.getName(), enemy.getLevel(), enemy.getMaxHp(), enemy.getDefense(), enemy.getAttacks(), enemy.getIdleAscii(), enemy.getColor());
                } else {
                    TextUtil.typewriterPrint(io, "Returning to checkpoint...");
                    io.sleepMillis(1500);
                    return false;
                }
            }
        }
        return false;
    }

    public void rescuePrisoner(String name) {
        io.clearScreen();
        TextUtil.typewriterPrintCentered(io, " --- You rescued " + name + "! --- ", 40, 157);
        io.sleepMillis(2000);
    }

    public boolean playTowerLevels(Player player) {
        String roachArt = """
         ,--.     .--.
        /   \\. ./    \\
       /  /\\\\ / " \\\\ /\\  \\
      / _/  {~~v~~}  \\_ \\
     /     {   |   }     \\
    ;   /\\{    |    }/\\   ;
    | _/  {    |    }  \\_ |
    |     {    |    }     |
    |    /{    |    }\\    |
    |   / {    |    } \\   |
    |  /  {    |    }  \\  |
    |  \\  \\    |    /  /  |
    |   \\  \\   |   /  /   |
     \\    \\  \\ |  /  /    /
      \\   /   ~~~~~   \\   /
    """;

        if (!battleLevel(player, 1, "Roach's Lair ",
                "The Tower rises. Roach awaits with venomous laughter.",
                new Enemy("Roach", 1, 200, 0, new int[]{90, 110, 140}, roachArt, "brown"))) {
            return false;
        }
        rescuePrisoner("Rowma");

        String miceArt = """
      .--,       .--,
     ( (  \\.---./  ) )
      '.__/o   o\\__.'
         {=  ^  =}
          >  -  <
         /       \\
        //       \\
       //|   .   |\\
       "\\       /'"_.-~^`'-.
         \\  _  /--'         `
        ___)( )(___
       (((__) (__)))
                """;
        if (!battleLevel(player, 2, "Miss Mice's Den ",
                "The tunnels whisper danger. The Rat Queen stirs within.",
                new Enemy("Miss Mice", 2, 200, 30, new int[]{80, 120, 160}, miceArt, "grey"))) {
            return false;
        }
        rescuePrisoner("Necko");

        String mordogArt = """
                /)-_-(\\
                 (o o)
         .-----__/\\o/
        /  __      /
    \\__/\\ /  \\ |/
         \\     ||
         //     ||
         |\\     |\\
    """;
        if (!battleLevel(player, 3, "Mordog's Fortress ",
                "Beyond these gates lies Mordog, the tyrant of Asonia.",
                new Enemy("Mordog", 3, 200, 80, new int[]{100, 120, 150, 200}, mordogArt, "orange"))) {
            return false;
        }
        rescuePrisoner("Cleo");

        return true;
    }
}
