package edu.ci.engine;

import javax.sound.sampled.Clip;

public final class AudioPlayer
{
    public AudioPlayer(double initialVolume)
    {
        volumeLevel = initialVolume;
    }

    public void playOnce(Clip sound)
    {
        sound.setFramePosition(0);
        sound.start();
    }

    public void setVolumeLevel(double newLevel)
    {
        volumeLevel = newLevel;
    }

    public void playRepeated(Clip sound, int timesToRepeat)
    {
        sound.setFramePosition(0);
        sound.loop(timesToRepeat);
    }

    public void playInBackground(Clip sound)
    {
        backgroundSound = sound;
        new Thread(new Runnable() {
            @Override
            public void run() {
                sound.setFramePosition(0);
                sound.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }).start();
    }

    public void stopBackgroundSound()
    {
        if(backgroundSound != null &&  backgroundSound.isRunning())
            backgroundSound.stop();
    }

    private Clip    backgroundSound;
    private double  volumeLevel;
}
