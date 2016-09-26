package edu.ci.scenes;

import edu.ci.Game;
import edu.ci.engine.Renderer;
import edu.ci.gui.ListMenu;
import java.awt.*;

public final class PauseScene implements IGameScene
{
    public PauseScene(Image background)
    {
        this.snapshot = background;
    }

    @Override
    public void onStart()
    {
        ListMenu.OptionInfo[] options = new ListMenu.OptionInfo[]
                {
                    new ListMenu.OptionInfo("Continue", (Void) -> Game.getSceneManager().popScene()),
                    new ListMenu.OptionInfo("Main menu", (Void) -> Game.getSceneManager().changeToScene(new MainMenuScene())),
                    new ListMenu.OptionInfo("Exit", (Void) -> java.lang.System.exit(0))
                };

        menu = new ListMenu(new Point(700, 300), options);
    }

    @Override
    public void onUpdate(double deltaTime)
    {
        menu.update();
    }

    @Override
    public void onRender(Renderer r)
    {
        r.renderSprite(snapshot, new Point(0, 0), new Dimension(1920, 1080));
        menu.render(r);
        r.renderText("Pause", new Point(780, 200), 120, Color.green);
    }

    @Override
    public void onClose() {}

    private Image snapshot;
    private ListMenu menu;
}
