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
        TextUtil.typewriterPrintCentered(dialogue, 40);
        System.out.println();
        Main.pause(1000);

        BattleSystem battle = new BattleSystem(player, enemy);
        boolean victory = battle.startBattle();

        if (victory) {
            player.levelUp(level);
            TextUtil.typewriterPrint("\n✨ VICTORY! ✨");
            TextUtil.typewriterPrint("+" + 100 + " Max HP");
            TextUtil.typewriterPrint("Weapon upgraded to Level " + (level + 1));
            TextUtil.typewriterPrint("Armor upgraded to Level " + (level + 1));
            Main.pause(1000);
            return true;
        } else {
            TextUtil.typewriterPrint("\n💀 DEFEAT 💀");
            Main.pause(5000);
            System.out.println("Returning to checkpoint...");
            Main.pause(5000);

            // Retry the level
            return battleLevel(player, level, title, dialogue,
                                new Enemy(enemy.getName(), enemy.getLevel(), 
                                        enemy.getMaxHp(), enemy.getDefense(), 
                                        enemy.getAttacks(), enemy.getIdleAscii(),
                                        enemy.getColor()));
        }
    }

    public void rescuePrisoner(String name) {
        Main.clearScreen();
        TextUtil.typewriterPrintCentered(" --- You rescued " + name + "! --- ", 40, 157);
        Main.pause(2000);
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

        // Level 2: Miss Mice
        String miceArt = """
  .--,       .--,
 ( (  \\.---./  ) )
  '.__/o   o\\__.'
     {=  ^  =}
      >  -  <
     /       \
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

        // Level 3: Mordog
        String mordogArt = """
            /)-_-(\\
             (o o)
     .-----__/\\o/
    /  __      /
\\__/\\ /  \\ |/
     \\     ||
     //     ||
     |\\     |\

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