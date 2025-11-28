package core;

import system.Characters;
import system.Menu;
import system.Tower;
import java.util.*;

public class Main {
    public static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleIO io = new ConsoleIOSystem(scanner, System.out);


        Menu menu = new Menu(io);
        Characters characters = new Characters(io);
        Tower tower = new Tower(io);

        Game game = new Game(menu, characters, tower, io);
        game.start();
    }
}
