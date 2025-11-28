package combat;

import core.ConsoleIO;
import entity.Enemy;
import entity.player.CatBreed;
import entity.player.CatColor;
import entity.player.Player;
import util.ColorUtil;
import util.TextUtil;

/**
 * BattleSystem orchestrates a fight between Player and Enemy.
 * It now depends on ConsoleIO for all I/O and timing.
 */
public class BattleSystem {
    private final Player player;
    private final Enemy enemy;
    private final ConsoleIO io;
    private int radiantBurstDamage = 0;

    public BattleSystem(Player player, Enemy enemy, ConsoleIO io) {
        this.player = player;
        this.enemy = enemy;
        this.io = io;
    }

    public boolean startBattle() {
        TextUtil.printCentered(io, "\n--- BATTLE START! ---\n");
        player.displayStats(io);
        io.println("");
        enemy.displayStats(io);
        io.sleepMillis(2000);

        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();

            if (!enemy.isAlive()) {
                break;
            }
            enemyTurn();

            if (!player.isAlive()) {
                break;
            }

            io.sleepMillis(1500);
        }

        return player.isAlive();
    }

    private void playerTurn() {
        io.clearScreen();
        TextUtil.printTitle(io, "YOUR TURN");

        player.displayStats(io);
        io.println("");
        enemy.displayStats(io);

        io.println("\n=== Choose Action ===");
        Attack[] attacks = player.getAttacks();

        for (int i = 0; i < attacks.length; i++) {
            Attack a = attacks[i];
            io.println((i + 1) + ". " + a.getName() +  " (DMG: " + a.getDamage() + ", Energy: " + a.getEnergyCost() + ")");
        }
        io.println((attacks.length + 1) + ". Defend (Halve next damage)");

        io.print("\nChoose action (1-" + (attacks.length + 1) + "): ");
        int choice = util.InputUtil.getIntInput(io, 1, attacks.length + 1);

        io.println("");

        if (choice == attacks.length + 1) {
            player.defend(io);
        } else {
            // Show player ASCII art when attacking
            showPlayerAttackArt();

            int damage = player.attack(choice - 1, io);
            if (damage > 0) {
                if (radiantBurstDamage > 0) {
                    damage += radiantBurstDamage;
                    io.println(ColorUtil.orange(" Radiant Burst adds " + radiantBurstDamage + " damage!"));
                    radiantBurstDamage = 0;
                }
                enemy.takeDamage(damage);
            }
        }
        player.regenerateEnergy();
        player.incrementTurn();

        if (player.getColor() == CatColor.ORANGE && player.getTurnCount() % 3 == 0) {
            radiantBurstDamage = 300;
        }

        if (player.getColor() == CatColor.WHITE && player.getTurnCount() % 3 == 0) {
            player.heal(200, io);
        }

        if (player.getColor() == CatColor.BLACK && player.getTurnCount() % 3 == 0) {
            io.println("\n Extra turn granted by Shadow Speed! ");
            io.sleepMillis(1500);
            if (enemy.isAlive()) {
                playerTurn();
            }
        }
    }

    private void showPlayerAttackArt() {
        CatBreed breed = player.getBreed();
        CatColor color = player.getColor();

        String art = breed.coloredAsciiAttackArt(color);
        io.println(art);
        io.sleepMillis(500);
    }

    private void enemyTurn() {
        io.clearScreen();
        TextUtil.printTitle(io, "ENEMY TURN");

        int damage = enemy.performAction();

        if (damage > 0) {
            player.takeDamage(damage, io);
        }

        io.sleepMillis(500);
    }
}
