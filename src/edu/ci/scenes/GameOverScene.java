package edu.ci.scenes;

import edu.ci.Game;
import edu.ci.engine.Engine;
import edu.ci.engine.LeaderboardManager;
import edu.ci.engine.Renderer;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScene implements IGameScene
{
    public GameOverScene(int score)
    {
        this.score = score;
        name = "";
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
        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_ENTER)) {
            if(!name.isEmpty()) {
                LeaderboardManager manager = new LeaderboardManager();
                manager.tryAddEntry(name, score);
            }
            Game.getSceneManager().changeToScene(new MainMenuScene());
        }

        char l = Engine.getInputHandler().getLetterKeyPressed();
        if(l != '\0' && name.length() <= 10)
            name += l;

        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_BACK_SPACE) && name.length() > 0)
            name = name.substring(0, name.length() - 1);
    }

    @Override
    public void onRender(Renderer r)
    {
        r.renderSprite(background, new Point(0, 0), new Dimension(1920, 1080));
        r.renderSprite(gameOverImage, new Point(450, 100), new Dimension(1000, 400));
        r.renderText("Your score is " + String.valueOf(score), new Point(700, 650), 64, Color.white);
        r.renderText("Enter your nickname: ", new Point(520, 800), 48, Color.cyan);
        r.renderText(name, new Point(1020, 800), 48, Color.cyan);
    }

    @Override
    public void onClose() {}

    private Image gameOverImage;
    private Image background;
    private int score;
    private String name;
}
