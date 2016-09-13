package edu.ci.engine;

import javax.sound.sampled.*;

public final class AudioPlayer
{
    public AudioPlayer(){}

    public void playOnce(Clip sound)
    {
        sound.setFramePosition(0);
        sound.start();
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

    public void playRepeated(Clip sound, int timesToRepeat)
    {
        sound.setFramePosition(0);
        sound.loop(timesToRepeat - 1);
    }

    public void playInBackground(Clip sound)
    {
        backgroundSound = sound;
        new Thread(() -> {
            sound.setFramePosition(0);
            sound.loop(Clip.LOOP_CONTINUOUSLY);
        }).start();
    }

    public void mute()
    {
        setVolume(0.0);
    }

    public void unmute()
    {
        setVolume(volumeLevel);
    }

    public void stopBackgroundSound()
    {
        if(backgroundSound != null &&  backgroundSound.isRunning())
            backgroundSound.stop();
    }

    private void setVolume(double volume)
    {
        Line outputLine = AudioSystem.getMixer(null).getSourceLines()[0];
        FloatControl gainControl = (FloatControl) outputLine.getControl(FloatControl.Type.MASTER_GAIN);
        float db = (float) (Math.log(volumeLevel) / Math.log(10.0) * 20.0);
        gainControl.setValue(db);
    }

    private Clip    backgroundSound;
    private double  volumeLevel;
}
