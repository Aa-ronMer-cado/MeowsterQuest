package entity;

import util.MusicUtil;
import util.TextUtil;

public class NPC {
    public MusicUtil music = new MusicUtil();
    private String name;
    private String role;

    public NPC(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public void speak(String dialogue) {
        TextUtil.typewriterBlip("\n[" + name + "]: ", 100, music);
        TextUtil.typewriterBlip(dialogue, 100, music);
    }

    public String getName() { return name; }
    public String getRole() { return role; }
}