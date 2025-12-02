package util;

import javax.sound.sampled.*;
import java.io.File;

public class MusicUtil {

    private Clip bgmClip;
    private Clip sfxClip;
    private Clip typingClip;

    /* ---------------------- INTRO BACKGROUND MUSIC ---------------------- */

    public void playIntroBGM(String filePath) {
        stopBGM();

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath));
            bgmClip = AudioSystem.getClip();
            bgmClip.open(audio);

            //BGM volume
            FloatControl gainControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);

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

    /* ---------------------- TOWER BACKGROUND MUSIC ---------------------- */
    public void playTowerBGM(String filePath) {
        stopBGM();

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath));
            bgmClip = AudioSystem.getClip();
            bgmClip.open(audio);

            //BGM volume
            FloatControl gainControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);

            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgmClip.start();
        } catch (Exception e) {
            System.out.println("[ERROR] Unable to play BGM -> " + e.getMessage());
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

}