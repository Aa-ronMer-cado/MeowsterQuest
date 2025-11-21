public class BattleSystem {
    private Player player;
    private Enemy enemy;
    private int radiantBurstDamage = 0; // Store damage from Orange ability

    public BattleSystem(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public boolean startBattle() {
        System.out.println("\n⚔️  BATTLE START! ⚔️\n");
        player.displayStats();
        System.out.println();
        enemy.displayStats();
        Main.pause(2000);

        while (player.isAlive() && enemy.isAlive()) {
            // Player turn
            playerTurn();

            if (!enemy.isAlive()) {
                break;
            }

            // Enemy turn
            enemyTurn();

            if (!player.isAlive()) {
                break;
            }

            Main.pause(1500);
        }

        return player.isAlive();
    }

    private void playerTurn() {
        Main.clearScreen();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    YOUR TURN");
        System.out.println("=".repeat(60));

        player.displayStats();
        System.out.println();
        enemy.displayStats();

        System.out.println("\n=== Choose Action ===");
        Attack[] attacks = player.getAttacks();

        for (int i = 0; i < attacks.length; i++) {
            Attack a = attacks[i];
            System.out.println((i + 1) + ". " + a.getName() +  " (DMG: " + a.getDamage() + ", NRG: " + a.getEnergyCost() + ")");
        }
        System.out.println((attacks.length + 1) + ". Defend (Halve next damage)");

        System.out.print("\nChoose action (1-" + (attacks.length + 1) + "): ");
        int choice = Main.getIntInput(1, attacks.length + 1);

        System.out.println();

        if (choice == attacks.length + 1) {
            player.defend();
        } else {
            int damage = player.attack(choice - 1);
            if (damage > 0) {
                // Apply Radiant Burst damage if stored
                if (radiantBurstDamage > 0) {
                    damage += radiantBurstDamage;
                    System.out.println("Radiant Burst adds " + radiantBurstDamage + " damage!");
                    radiantBurstDamage = 0;
                }
                enemy.takeDamage(damage);
            }
        }
        // End of turn effects
        player.regenerateEnergy();
        player.incrementTurn();

        // Check for Orange ability (store damage for next attack)
        if (player.getColor() == CatColor.ORANGE && player.getTurnCount() % 3 == 0) {
            radiantBurstDamage = 300;
        }
        // White ability - heal
        if (player.getColor() == CatColor.WHITE && player.getTurnCount() % 3 == 0) {
            player.heal(200);
        }

        // Black ability - extra turn
        if (player.getColor() == CatColor.BLACK && player.getTurnCount() % 3 == 0) {
            System.out.println("\n🌑 Extra turn granted by Shadow Speed! 🌑");
            Main.pause(1500);
            if (enemy.isAlive()) {
                playerTurn();
            }
        }
    }

    private void enemyTurn() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                   ENEMY TURN");
        System.out.println("=".repeat(60) + "\n");

        int damage = enemy.performAction();

        if (damage > 0) {
            player.takeDamage(damage);
        }

        Main.pause(1500);
    }
}