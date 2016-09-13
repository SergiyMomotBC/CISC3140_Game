package edu.ci.scenes;

import edu.ci.IGameScene;
import edu.ci.engine.Engine;
import edu.ci.engine.Renderer;
import edu.ci.gui.ListMenu;
import java.awt.*;

public class MainMenuScene implements IGameScene
{
    @Override
    public void onStart()
    {
        ListMenu.OptionInfo[] info =
                { new ListMenu.OptionInfo("Play", null),
                  new ListMenu.OptionInfo("Leaderboard", null),
                  new ListMenu.OptionInfo("How to play", null),
                  new ListMenu.OptionInfo("Credits", null),
                  new ListMenu.OptionInfo("Exit", (Void) -> System.exit(0))
                };

        menu = new ListMenu(new Point(700, 300), info);

        background = Engine.getResourceManager().loadImage("menu_background.png");
    }

    @Override
    public void onUpdate(double deltaTime)
    {
        menu.update();
    }

    @Override
    public void onRender(Renderer r)
    {
        r.renderSprite(background, new Point(0, 0), new Dimension(1920, 1080));
        menu.render(r);
        r.renderText("College Invaders", new Point(580, 200), 100, Color.green);
    }

    @Override
    public void onClose()
    {
        Engine.getResourceManager().freeResource("menu_background.png");
    }

    private Image           background;
    private ListMenu        menu;
}
