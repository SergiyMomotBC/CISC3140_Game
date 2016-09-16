package edu.ci;

import edu.ci.ecs.*;
import edu.ci.engine.Engine;
import edu.ci.engine.Renderer;
import edu.ci.scenes.IGameScene;
import java.awt.*;

public class TestScene implements IGameScene
{
    @Override
    public void onStart()
    {
        TransformComponent t = new TransformComponent(new Point(900, 1000), 1.0, 0.0);
        SpriteComponent s = new SpriteComponent(Engine.getResourceManager().loadImage("player.png"));
        MovementComponent m = new MovementComponent(new Point(0, 0));

        player = new GameObject(GameObjectType.Player);
        player.addComponent(t);
        player.addComponent(s);
        player.addComponent(m);

        sr = new SpriteRendererSystem();
        is = new InputSystem();
        ms = new MovementSystem();
    }

    @Override
    public void onUpdate(double deltaTime)
    {
        is.update(deltaTime, player);
        ms.update(deltaTime, player);
        sr.update(deltaTime, player);
    }

    @Override
    public void onRender(Renderer r) {}

    @Override
    public void onClose() {}

    GameObject player;
    SpriteRendererSystem sr;
    InputSystem is;
    MovementSystem ms;

}
