package edu.ci;

import edu.ci.engine.Engine;
import javax.sound.sampled.Clip;
import java.awt.event.KeyEvent;

public class TestScene implements IGameScene
{
    @Override
    public void onStart() {
        sound = Engine.getResourceManager().loadSound("test.wav");
        timer = 0.0;
    }

    @Override
    public void onUpdate(double deltaTime) {
        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_S))
            Engine.getAudioPlayer().playInBackground(sound);

        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_D)) {
            Engine.getAudioPlayer().setVolumeLevel(0.25);
            System.out.println(Engine.getAudioPlayer().getVolumeLevel());
        }

        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_M))
            Engine.getAudioPlayer().mute();

        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_U))
            Engine.getAudioPlayer().unmute();
    }

    @Override
    public void onRender() {

    }

    @Override
    public void onClose() {
        Engine.getResourceManager().freeResource("test.wav");
    }

    Clip sound;
    double timer;
}
