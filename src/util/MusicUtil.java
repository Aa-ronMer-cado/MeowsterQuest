package util;

import javax.sound.sampled.*;
import java.io.File;

public class MusicUtil {

    private Clip bgmClip;
    private Clip sfxClip;
    private Clip typingClip;

    /* ---------------------- BACKGROUND MUSIC ---------------------- */

    public void playBGM(String filePath) {
        stopBGM();

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath));
            bgmClip = AudioSystem.getClip();
            bgmClip.open(audio);
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgmClip.start();
        } catch (Exception e) {
            System.out.println("[ERROR] Unable to play BGM -> " + e.getMessage());
        }
    }

    public void stopBGM() {
        if (bgmClip != null) {
            bgmClip.stop();
            bgmClip.close();
            bgmClip = null;
        }
    }

    /* ---------------------- SOUND EFFECTS ---------------------- */

    public void playSFX(String filePath) {
        stopSFX();

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath));
            sfxClip = AudioSystem.getClip();
            sfxClip.open(audio);
            sfxClip.start();
        } catch (Exception e) {
            System.out.println("[ERROR] Unable to play SFX -> " + e.getMessage());
        }
    }

    public void stopSFX() {
        if (sfxClip != null) {
            sfxClip.stop();
            sfxClip.close();
            sfxClip = null;
        }
    }

    /* ---------------------- TYPING SOUND EFFECTS ---------------------- */

    public void startTypingSFX(String filePath) {
        stopTypingSFX();

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath));
            typingClip = AudioSystem.getClip();
            typingClip.open(audio);
            typingClip.loop(Clip.LOOP_CONTINUOUSLY);
            typingClip.start();
        } catch (Exception e) {
            System.out.println("[ERROR] Unable to play typing SFX -> " + e.getMessage());
        }
    }

    public void stopTypingSFX() {
        if (typingClip != null) {
            typingClip.stop();
            typingClip.close();
            typingClip = null;
        }
    }

    /* ---------------------- UTILITY METHODS ---------------------- */

    // Method used in Tower.java - prints with typing sound
    public void printWithTypingSFX(String title, int level, String dialogue) {
        String text = "Level " + level + " completed: " + title;
        String typingSFXPath = "src/resource/TypingSound.wav";

        startTypingSFX(typingSFXPath);

        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        stopTypingSFX();
        System.out.println();
        }
    }
