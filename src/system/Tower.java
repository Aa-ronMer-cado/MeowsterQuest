package system;

import combat.BattleSystem;
import core.Main;
import entity.Enemy;
import entity.player.Player;
import util.TextUtil;
import util.MusicUtil;

public class Tower {
    public MusicUtil music = new MusicUtil();
    
    public boolean battleLevel(Player player, int level, String title, String dialogue, Enemy enemy) {
        Main.clearScreen();
        music.playBGM("src/resource/TowerMusic.wav");
        TextUtil.printTitle("LEVEL " + level + " - " + title);
        TextUtil.typewriterPrintCentered(dialogue, 40);
        System.out.println();
        TextUtil.pause(1000);

        BattleSystem battle = new BattleSystem(player, enemy);
        boolean victory = battle.startBattle();

        if (victory) {
            player.levelUp(level);
            music.playSFX("src/resource/VictorySound.wav");
            music.printWithTypingSFX(title, level, dialogue);
            TextUtil.typewriterPrint("\n✨ VICTORY! ✨", music);
            TextUtil.typewriterPrint("+" + 100 + " Max HP", music);
            TextUtil.typewriterPrint("Weapon upgraded to Level " + (level + 1), music);
            TextUtil.typewriterPrint("Armor upgraded to Level " + (level + 1),music);
            TextUtil.pause(1000);
            return true;
        } else {
            music.stopBGM();
            music.playSFX("src/resource/Defeat.wav");
            TextUtil.typewriterPrint("\n------ DEFEAT ------",500, music);
            TextUtil.pause(3000);
            TextUtil.typewriterPrint("Returning to checkpoint...........",500, music);
            TextUtil.pause(2000);

            // Retry the level
            player.restoreToFull();
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
        TextUtil.pause(2000);
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

        if (!battleLevel(player, 1, "Roach's Lair",
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
     /       \\
    //       \\\\
   //|   .   |\\\\
   "\\\\       /'"_.-~^`'-.
     \\\\  _  /--'         `
    ___)( )(___
   (((__) (__)))
            """;
        if (!battleLevel(player, 2, "Miss Mice's Den",
                        "The tunnels whisper danger. The Rat Queen stirs within.",
                        new Enemy("Miss Mice", 2, 200, 30, new int[]{8000, 12000, 16000}, miceArt, "grey"))) {
            return false;
        }
        rescuePrisoner("Necko");

        // Level 3: Mordog
        String mordogArt = """
    ,_____ ,
  ,._ ,_. 7\\\\
 j `-'     //
 |o_, o    \\\\
.`_y_`-,'   !
//   `, `._ `-,
|_     \\\\   _.'*\\\\
  >--,-'`-'*_*'``---.
  |\\\\_* _*'-'         '
 //    `               \\\\
 \\\\         _ .       //
  '`._     //   )     //
   \\\\  |`-,-|  //c-'7 //
    ) \\\\ (_,| |   // (_
   ((_//   ((_;)  \\\\_)))


            """;
        if (!battleLevel(player, 3, "Mordog's Fortress",
                        "Beyond these gates lies Mordog, the tyrant of Asonia.",
                        new Enemy("Mordog", 3, 200, 80, new int[]{100, 120, 150, 200}, mordogArt, "orange"))) {
            return false;
        }
        rescuePrisoner("Cleo");

        return true;
    }
}