package combat;

import core.Main;
import entity.Enemy;
import entity.player.CatBreed;
import entity.player.Player;
import util.ColorUtil;
import util.MusicUtil;
import util.TextUtil;

public class BattleSystem {
    public MusicUtil music = new MusicUtil();
    private final Player player;
    private final Enemy enemy;
    private int radiantBurstBonus = 0;

    public BattleSystem(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public boolean startBattle() {
        TextUtil.printCentered("\n--- BATTLE START! ---\n");
        player.displayStats();
        System.out.println();
        enemy.displayStats();
        TextUtil.pause(2000);

        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();

            if (!enemy.isAlive()) break;

            enemyTurn();

            if (!player.isAlive()) break;

            TextUtil.pause(1500);
        }
        return player.isAlive();
    }

    private void playerTurn() {
        TextUtil.clearScreen();
        TextUtil.typewriterBlip("---YOUR TURN---", 100, music);

        player.displayStats();
        System.out.println();
        enemy.displayStats();

        Attack[] attacks = player.getAttacks();

        System.out.println("\n=== Choose Action ===");
        for (int i = 0; i < attacks.length; i++) {
            var a = attacks[i];
            System.out.println((i + 1) + ". " + a.getName() +
                    " (DMG: " + a.getDamage() + ", Energy: " + a.getEnergyCost() + ")");
        }
        System.out.println((attacks.length + 1) + ". Defend");

        System.out.print("\nChoose (1-" + (attacks.length + 1) + "): ");
        int choice = Main.getIntInput(1, attacks.length + 1);
        System.out.println();

        if (choice == attacks.length + 1) {
            player.defend();
        } else {
            showPlayerAttackArt();
            int damage = player.attack(choice - 1);

            if (radiantBurstBonus > 0 && damage > 0) {
                damage += radiantBurstBonus;
                System.out.println(ColorUtil.orange(" Radiant Burst adds " + radiantBurstBonus + " bonus damage!"));
                radiantBurstBonus = 0;
            }

            enemy.takeDamage(damage);
        }

        player.regenerateEnergy();
        player.incrementTurn();

        // Only ORANGE uses bonus damage
        if (player.getColor().name().equals("ORANGE") && player.getTurnCount() % 3 == 0) {
            radiantBurstBonus = 300;
        }
    }

    private void showPlayerAttackArt() {
        CatBreed breed = player.getBreed();
        String art = breed.ColoredAsciiAttackArt(player.getColor());
        System.out.println(art);
        TextUtil.pause(500);
    }

    private void enemyTurn() {
        TextUtil.clearScreen();
        TextUtil.typewriterBlip("---ENEMY TURN---",100, music);

        int dmg = enemy.performAction();
        if (dmg > 0) player.takeDamage(dmg);

        TextUtil.pause(500);
    }
}