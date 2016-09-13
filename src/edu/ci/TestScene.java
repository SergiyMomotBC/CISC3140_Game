package edu.ci;

import edu.ci.engine.Engine;
import edu.ci.engine.Renderer;
import edu.ci.gui.ListMenu;

public class TestScene implements IGameScene
{
    @Override
    public void onStart() {
        String[] options = {"New game", "Leaderboard", "How to play", "Credits", "Exit"};

    }

    @Override
    public void onUpdate(double deltaTime) {
        listMenu.update();
    }

    @Override
    public void onRender(Renderer r) {
        listMenu.render(Engine.getRenderer());
    }

    @Override
    public void onClose() {
        Engine.getResourceManager().freeResource("test.wav");
    }

    ListMenu listMenu;
}
