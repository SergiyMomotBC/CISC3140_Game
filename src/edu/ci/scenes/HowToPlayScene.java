package edu.ci.scenes;

import edu.ci.Game;
import edu.ci.engine.Engine;
import edu.ci.engine.Renderer;
import java.awt.*;
import java.awt.event.KeyEvent;

public class HowToPlayScene implements IGameScene
{
    @Override
    public void onStart()
    {
        background = Engine.getResourceManager().loadImage("gameplay_background.png");
    }

    @Override
    public void onUpdate(double deltaTime)
    {
        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_ESCAPE))
            Game.getSceneManager().popScene();
    }

    @Override
    public void onRender(Renderer r)
    {
        r.renderSprite(background, new Point(0, 0));
        r.renderText("HOW TO PLAY", new Point(550, 200), 120, Color.green);

        //controls
    }

    @Override
    public void onClose() {}

    private Image background;
}
