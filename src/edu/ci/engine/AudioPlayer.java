package edu.ci.engine;

import javax.sound.sampled.*;

public final class AudioPlayer
{
    public AudioPlayer()
    {
        volumeLevel = 1.0;
    }

    public void playOnce(Clip sound)
    {
        sound.setFramePosition(0);
        sound.start();
    }

    public void playRepeated(Clip sound, int timesToPlay)
    {
        sound.setFramePosition(0);
        sound.loop(timesToPlay - 1);
    }

    public void playInBackground(Clip sound)
    {
        backgroundSound = sound;
        new Thread(() -> {
            sound.setFramePosition(0);
            sound.loop(Clip.LOOP_CONTINUOUSLY);
        }).start();
    }

    public void stopBackgroundSound()
    {
        if(backgroundSound != null &&  backgroundSound.isRunning())
            backgroundSound.stop();
    }

    public void setVolumeLevel(double newLevel)
    {
        volumeLevel = newLevel;
        setVolume(volumeLevel);
    }

    public double getVolumeLevel()
    {
        return volumeLevel;
    }

    public void mute()
    {
        setVolume(0.0);
        System.out.println("muted");
    }

    public void unmute()
    {
        setVolume(volumeLevel);
    }

    private void setVolume(double volume)
    {
        Line outputLine = AudioSystem.getMixer(null).getSourceLines()[0];
        FloatControl gainControl = (FloatControl) outputLine.getControl(FloatControl.Type.MASTER_GAIN);
        float db = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        gainControl.setValue(db);
    }

    private Clip        backgroundSound;
    private double      volumeLevel;
}
