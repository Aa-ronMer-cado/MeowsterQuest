package system;

import core.ConsoleIO;
import util.InputUtil;
import entity.NPC;
import entity.player.CatBreed;
import entity.player.CatColor;
import entity.player.Player;
import util.TextUtil;

public class Characters {
    private final ConsoleIO io;

    public Characters(ConsoleIO io) {
        this.io = io;
    }

    public Player createCharacter() {
        TextUtil.printTitle(io, "CHARACTER CREATION");

        io.print("Enter your cat's name: ");
        String name = io.readLine().trim();
        while (name.isEmpty()) {
            io.print("Name cannot be empty. Please enter a name: ");
            name = io.readLine().trim();
        }

        io.println("\n=== Choose Your Cat Breed (Class) ===\n");
        io.println("1. Persian (Mage) - Balanced magic user");
        io.println(CatBreed.PERSIAN.getAsciiArtIdle());

        io.println("2. Ragdoll (Ranged) - Quick and agile");
        io.println(CatBreed.RAGDOLL.getAsciiArtIdle());

        io.println("3. Puskal (Warrior) - Strong and tough");
        io.println(CatBreed.PUSKAL.getAsciiArtIdle());

        io.print("\nSelect breed (1-3): ");
        int breedChoice = InputUtil.getIntInput(io, 1, 3);

        CatBreed breed = CatBreed.values()[breedChoice - 1];

        io.println("\n=== Choose Your Cat Color (Elemental Trait) ===");
        io.println("1. Orange - Radiant Burst (300 dmg every 3 turns)");
        io.println("2. Black - Shadow Speed (Extra turn every 3 turns)");
        io.println("3. White - Healing Aura (Restore 200 HP every 3 turns)");
        io.println("4. Tilapia - Reflect Shield (Deflect damage every 3 turns)");
        io.print("\nSelect color (1-4): ");
        int colorChoice = InputUtil.getIntInput(io, 1, 4);

        CatColor color = CatColor.values()[colorChoice - 1];

        Player newPlayer = new Player(name, breed, color);

        io.clearScreen();
        io.println("\n" + "=".repeat(157));
        TextUtil.typewriterPrintCentered(io, "The " + color.coloredName() + " " + breed.name() +  " named " + name +
            " is ready for battle! It leaps into action, claws flashing and eyes burning with courage.", 30);
        TextUtil.typewriterPrintCentered(io, "The fate of Pawshire rests on its paws!", 30);
        io.println("=".repeat(157));
        newPlayer.displayStats(io);

        io.sleepMillis(2000);
        return newPlayer;
    }

    public void showNPCEncounter(String playerName) {
        io.clearScreen();
        TextUtil.printTitle(io, "PAWSHIRE VILLAGE");

        NPC maximo = new NPC("Maximo", "Wise old cat mentor", io);
        maximo.speak("Ah, " + playerName + "... you've returned, the Asonians took our kin to the Tower of Tails.");
        io.sleepMillis(1500);

        NPC wankie = new NPC("Wankie", "Maximo's curious son", io);
        wankie.speak("They took Rowma, Necko, and Cleo! Please, help us!");
        io.sleepMillis(1500);

        io.println("\n=== Your Response ===");
        io.println("1. Absolutely! Asonia won't stop me!");
        io.println("2. Surely I will save them all!");
        io.println("3. I'll save them, Maximo. You have my word, Pawshire will be free again.");
        io.print("\nChoose (1-3): ");
        InputUtil.getIntInput(io, 1, 3);

        io.println("\n" + "=".repeat(157));
        TextUtil.typewriterPrintCentered(io, "With courage burning bright, " + playerName + " journeys to Asonia", 35);
        TextUtil.typewriterPrintCentered(io, "to reclaim Pawshire's freedom.", 35);
        io.println("=".repeat(157));
        io.sleepMillis(2000);
    }
}
