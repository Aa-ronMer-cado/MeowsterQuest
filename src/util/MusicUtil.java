    package util;

    import javax.sound.sampled.*;
    import java.io.File;

    public class MusicUtil {

        private Clip bgmClip;
        private Clip sfxClip;
        private Clip typingClip;

        // Play looping background music
        public void playBGM(String filePath) {
            stopBGM(); // Stop previous BGM if playing

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

        // Stop background music
        public void stopBGM() {
            if (bgmClip != null) {
                bgmClip.stop();
                bgmClip.close();
                bgmClip = null;
            }
        }

        // Play one-time sound effect
        public void playSFX(String filePath) {
            stopSFX(); // Prevent overlapping sounds

            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath));
                sfxClip = AudioSystem.getClip();
                sfxClip.open(audio);
                sfxClip.start();
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to play SFX -> " + e.getMessage());
            }
        }

        // Stop current SFX
        public void stopSFX() {
            if (sfxClip != null) {
                sfxClip.stop();
                sfxClip.close();
                sfxClip = null;
            }
        }

        // Start looping typing sound
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

        // Stop typing sound
        public void stopTypingSFX() {
            if (typingClip != null) {
                typingClip.stop();
                typingClip.close();
                typingClip = null;
            }
        }

        // Print text with typing sound effect automatically
        public void printWithTypingSFX(String text, int delayMs, String typingSFXPath) {
            startTypingSFX(typingSFXPath);

            for (char c : text.toCharArray()) {
                System.out.print(c);

                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            stopTypingSFX();
            System.out.println();
        }
    }