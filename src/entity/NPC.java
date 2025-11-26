package entity;

import core.Main;

//ROLE?
public class NPC {
    private String name;
    private String role;

    public NPC(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public void speak(String dialogue) {
        System.out.print("\n[" + name + "]: ");
        Main.typewriterPrint(dialogue, 35);
    }

    public String getName() { return name; }
    public String getRole() { return role; }
}
