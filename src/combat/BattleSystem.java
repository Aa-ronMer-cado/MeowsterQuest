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

    public BattleSystem(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public boolean startBattle() {
        TextUtil.clearScreen();
        TextUtil.typewriterPrintCentered("--- BATTLE START! ---", music);
        TextUtil.clearScreen();
        enemy.displayStats();
        TextUtil.pause(2000);
        System.out.println("\n");
        player.displayStats();
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
        TextUtil.typewriterPrintCentered("---YOUR TURN---", music);
        player.displayStats();
        TextUtil.pause(1000);
        System.out.println();

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
            TextUtil.pause(800);
            enemy.takeDamage(damage);
            TextUtil.pause(1200);
        }

        player.regenerateEnergy();
        player.incrementTurn();

        // Check if ORANGE's Radiant Burst should trigger
        if (player.getColor().name().equals("ORANGE") && player.getTurnCount() % 3 == 0) {
            TextUtil.pause(500);
            showPlayerAttackArt();
            TextUtil.pause(500);
            System.out.println(ColorUtil.orange("\nRadiant Burst explodes for 300 damage!"));
            enemy.takeDamage(300);
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
        TextUtil.typewriterBlipCentered("---ENEMY TURN---",100, music);
        TextUtil.pause(500);

        int dmg = enemy.performAction();
        TextUtil.pause(800);
        if (dmg > 0) player.takeDamage(dmg);
        TextUtil.pause(400);
    }
}