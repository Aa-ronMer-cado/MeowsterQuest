public class Game {
    private Player player;

    public void start() {
        mainMenu();
    }

    private void mainMenu() {
        Main.clearScreen();
        Main.displayTitle();
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("Background: Sunset over Pawshire fading into darkness...\n");
        System.out.println("1. Play");
        System.out.println("2. Exit");
        System.out.print("\nChoose an option: ");

        int choice = Main.getIntInput(1, 2);

        if (choice == 1) {
            System.out.println("\n🐱 *MEOW* 🐱");
            Main.pause(1000);
            startGame();
        } else {
            System.out.println("\nThank you for visiting Pawshire!");
        }
    }

    private void startGame() {
        Main.clearScreen();
        displayIntroduction();
        player = characterCreation();
        npcEncounter();
        towerLevels();
    }

    private void displayIntroduction() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    INTRODUCTION");
        System.out.println("=".repeat(60) + "\n");

        String[] intro = {
            "Long, long ago, Pawshire purred in harmony...",
            "...until darkness crept in from Asonia.",
            "The Tower of Tails now imprisons our kin.",
            "A hero must rise to save them all."
        };

        for (String line : intro) {
            Main.typewriterPrintCentered(line, 40);
            Main.pause(800);
        }
        System.out.println();
    }

    private Player characterCreation() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                 CHARACTER CREATION");
        System.out.println("=".repeat(60) + "\n");

        // Get player name
        System.out.print("Enter your cat's name: ");
        String name = Main.scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Please enter a name: ");
            name = Main.scanner.nextLine().trim();
        }

        // Choose breed
        System.out.println("\n=== Choose Your Cat Breed (Class) ===");
        System.out.println("1. Persian (Mage) - Balanced magic user");
        System.out.println("2. Ragdoll (Ranged) - Quick and agile");
        System.out.println("3. Puskal (Warrior) - Strong and tough");
        System.out.print("\nSelect breed (1-3): ");
        int breedChoice = Main.getIntInput(1, 3);

        CatBreed breed = CatBreed.values()[breedChoice - 1];

        // Choose color
        System.out.println("\n=== Choose Your Cat Color (Elemental Trait) ===");
        System.out.println("1. Orange - Radiant Burst (300 dmg every 3 turns)");
        System.out.println("2. Black - Shadow Speed (Extra turn every 3 turns)");
        System.out.println("3. White - Healing Aura (Restore 200 HP every 3 turns)");
        System.out.println("4. Tilapia - Reflect Shield (Deflect damage every 3 turns)");
        System.out.print("\nSelect color (1-4): ");
        int colorChoice = Main.getIntInput(1, 4);

        CatColor color = CatColor.values()[colorChoice - 1];

        // Create player
        Player newPlayer = new Player(name, breed, color);

        // Display summary
        Main.clearScreen();
        System.out.println("\n" + "=".repeat(60));
        Main.typewriterPrintCentered("The " + color.name().toLowerCase() + " " + breed.name().toLowerCase() +  " named " + name + 
        " is ready for battle! It leaps into action, claws flashing and eyes burning with courage.", 30);
        Main.typewriterPrintCentered("The fate of Pawshire rests on its paws!", 30);
        System.out.println("=".repeat(60));
        newPlayer.displayStats();

        Main.pause(2000);
        return newPlayer;
    }

    private void npcEncounter() {
        Main.clearScreen();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                   PAWSHIRE VILLAGE");
        System.out.println("=".repeat(60) + "\n");

        // Maximo dialogue
        NPC maximo = new NPC("Maximo", "Wise old cat mentor");
        maximo.speak("Ah, " + player.getName() + "... you've returned.");
        Main.pause(1500);
        maximo.speak("The Asonians took our kin to the Tower of Tails.");
        Main.pause(1500);

        // Wankie dialogue
        NPC wankie = new NPC("Wankie", "Maximo's curious son");
        wankie.speak("They took Rowma, Necko, and Cleo! Please, help us!");
        Main.pause(1500);

        System.out.println("\n=== Your Response ===");
        System.out.println("1. Absolutely! Asonia won't stop me!");
        System.out.println("2. Surely I will save them all!");
        System.out.println("3. I'll save them, Maximo. You have my word, Pawshire will be free again.");
        System.out.print("\nChoose (1-3): ");
        Main.getIntInput(1, 3);

        System.out.println("\n" + "=".repeat(60));
        Main.typewriterPrintCentered("With courage burning bright, " + player.getName() + " journeys to Asonia", 35);
        Main.typewriterPrintCentered("to reclaim Pawshire's freedom.", 35);
        System.out.println("=".repeat(60));
        Main.pause(2000);
    }

    private void towerLevels() {
        // Level 1 - Roach
        if (!battleLevel(1, "Roach's Lair 🪳",  "The Tower rises. Roach awaits with venomous laughter.", 
        new Enemy("Roach", 1, 1200, 0, new int[]{90, 110, 140}))) {
            return;
        }
        rescuePrisoner("Rowma");

        // Level 2 - Miss Mice
        if (!battleLevel(2, "Miss Mice's Den 🐭",
                        "The tunnels whisper danger. The Rat Queen stirs within.",
                        new Enemy("Miss Mice", 2, 1500, 30, new int[]{80, 120, 160}))) {
            return;
        }
        rescuePrisoner("Necko");

        // Boss Level - Mordog
        if (!battleLevel(3, "Mordog's Fortress 🐶",
                        "Beyond these gates lies Mordog, the tyrant of Asonia.",
                        new Enemy("Mordog", 3, 2000, 80, new int[]{100, 120, 150, 200}))) {
            return;
        }
        rescuePrisoner("Cleo");

        // Victory!
        victorySequence();
    }

    private boolean battleLevel(int level, String title, String dialogue, Enemy enemy) {
        Main.clearScreen();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    LEVEL " + level + " - " + title);
        System.out.println("=".repeat(60) + "\n");
        Main.typewriterPrintCentered(dialogue, 40);
        System.out.println();
        Main.pause(1500);

        BattleSystem battle = new BattleSystem(player, enemy);
        boolean victory = battle.startBattle();

        if (victory) {
            player.levelUp(level);
            System.out.println("\n🎉 VICTORY! 🎉");
            System.out.println("+" + 100 + " Max HP");
            System.out.println("Weapon upgraded to Level " + (level + 1));
            System.out.println("Armor upgraded to Level " + (level + 1));
            Main.pause(2000);
            return true;
        } else {
            System.out.println("\n💀 DEFEAT 💀");
            System.out.println("Returning to checkpoint...");
            Main.pause(2000);
            // In a full implementation, you could offer retry
            return battleLevel(level, title, dialogue,
                                new Enemy(enemy.getName(), enemy.getLevel(), 
                                        enemy.getMaxHp(), enemy.getDefense(), 
                                        enemy.getAttacks()));
        }
    }

    private void rescuePrisoner(String name) {
        Main.clearScreen();
        System.out.println("\n" + "=".repeat(60));
        Main.printCentered("You rescued " + name + "! 🐱", 60);
        System.out.println("=".repeat(60));
        Main.pause(2000);
    }

    private void victorySequence() {
        Main.clearScreen();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                   🏆 VICTORY! 🏆");
        System.out.println("=".repeat(60) + "\n");

        Main.typewriterPrintCentered("Pawshire is restored! Calm returns to the land...", 40);
        System.out.println();
        Main.pause(1500);

        Main.typewriterPrintCentered("The citizens cheer for " + player.getName() + "!", 35);
        Main.pause(1000);

        System.out.println();
        Main.typewriterPrintCentered("\"Because of you, our kin are free at last!\"", 35);
        Main.pause(1000);
        Main.typewriterPrintCentered("\"Pawshire will never forget your courage and sacrifice.\"", 35);
        Main.pause(1500);

        System.out.println();
        Main.printCentered("🎊 Prisoners reunite! 🎊", 60);
        Main.typewriterPrintCentered("Rowma, Necko, and Cleo are safe!", 35);
        Main.pause(2000);

        endScreen();
    }

    private void endScreen() {
        Main.clearScreen();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    GAME COMPLETE");
        System.out.println("=".repeat(60) + "\n");

        System.out.print("Rate your experience (1-5): ");
        Main.getIntInput(1, 5);

        System.out.println("\n" + "=".repeat(60));
        Main.typewriterPrintCentered("Thank you for playing!", 40);
        Main.typewriterPrintCentered("Your courage has guided Pawshire to freedom.", 40);
        Main.typewriterPrintCentered("May your journey always be filled with", 40);
        Main.typewriterPrintCentered("bravery, wonder, and kindness.", 40);
        System.out.println("=".repeat(60) + "\n");

        System.out.println("=== CREDITS ===");
        System.out.println("Game Design: MeowsterQuest Team");
        System.out.println("Programming: Java Console Edition");
        System.out.println("Thanks for playing!");
        System.out.println("\nPress Enter to return to main menu...");
        Main.scanner.nextLine();

        mainMenu();
    }
}