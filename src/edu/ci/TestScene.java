package edu.ci;

import edu.ci.ecs.*;
import edu.ci.engine.Engine;
import edu.ci.engine.Renderer;
import edu.ci.scenes.IGameScene;
import java.awt.*;
import java.util.ArrayList;

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

        objects = new ArrayList<>();

        objects.add(player);

        sr = new SpriteRendererSystem();
        is = new InputSystem(objects);
        ms = new MovementSystem();
        ts = new TransformSystem();
    }

    @Override
    public void onUpdate(double deltaTime)
    {
        for(int i = 0; i < objects.size(); i++) {
            is.update(deltaTime, objects.get(i));
            ms.update(deltaTime, objects.get(i));
            ts.update(deltaTime, objects.get(i));
            sr.update(deltaTime, objects.get(i));
        }
    }

    @Override
    public void onRender(Renderer r) {}

    @Override
    public void onClose() {}

    GameObject player;
    ArrayList<GameObject> objects;
    SpriteRendererSystem sr;
    InputSystem is;
    MovementSystem ms;
    TransformSystem ts;
}
