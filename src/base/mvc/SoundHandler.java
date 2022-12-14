package base.mvc;

import javax.sound.sampled.*;
import java.io.File;

/** A utility class used for running the background music
 *
 */
public class SoundHandler {

    /** Runs the music.
     * @param path the path of the .wav file
     */
    public static void RunMusic(String path) {
        File fl = new File(path);
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(fl);
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(99);

            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = control.getMinimum();
            float result = range * (1 - 60 / 100.0f);
            control.setValue(result);
        } catch (Exception e) { e.getStackTrace(); }
    }
}
