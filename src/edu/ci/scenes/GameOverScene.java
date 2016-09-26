package edu.ci.scenes;

import edu.ci.Game;
import edu.ci.engine.Engine;
import edu.ci.engine.Renderer;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScene implements IGameScene
{
    public GameOverScene(int score)
    {
        this.score = score;
    }

    @Override
    public void onStart()
    {
        background = Engine.getResourceManager().loadImage("gameplay_background.png");
        gameOverImage = Engine.getResourceManager().loadImage("game_over.png");
    }

    @Override
    public void onUpdate(double deltaTime)
    {
        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_ENTER))
            Game.getSceneManager().changeToScene(new MainMenuScene());
    }

    @Override
    public void onRender(Renderer r)
    {
        r.renderSprite(background, new Point(0, 0), new Dimension(1920, 1080));
        r.renderSprite(gameOverImage, new Point(450, 100), new Dimension(1000, 400));
        r.renderText("Your score is " + String.valueOf(score), new Point(600, 650), 64, Color.white);
    }

    @Override
    public void onClose() {}

    private Image gameOverImage;
    private Image background;
    private int score;
    private String name;
}
