package edu.ci;

import edu.ci.ecs.*;
import edu.ci.ecs.systems.*;
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
        entitiesManager = new EntitiesManager();
        spawner = new Spawner(entitiesManager);

        spawner.spawnBackground(Engine.getResourceManager().loadImage("gameplay_background.png"));
        spawner.spawnPlayer(new Point(900, 1000), Engine.getResourceManager().loadImage("player.png"));
        spawner.spawnEnemy(new Point(500, 100), Engine.getResourceManager().loadImage("enemy.png"));

        sr = new SpriteRendererSystem();
        is = new InputSystem(spawner);
        ms = new MovementSystem();
        ts = new TransformSystem();
        cs = new CollisionSystem(entitiesManager.getEntities());
    }

    @Override
    public void onUpdate(double deltaTime)
    {
        entitiesManager.update();

        ArrayList<GameObject> objects = entitiesManager.getEntities();
        for(int i = 0; i < objects.size(); i++) {
            is.update(deltaTime, objects.get(i));
            ms.update(deltaTime, objects.get(i));
            ts.update(deltaTime, objects.get(i));
            cs.update(deltaTime, objects.get(i));
            sr.update(deltaTime, objects.get(i));
        }
    }

    @Override
    public void onRender(Renderer r) {}

    @Override
    public void onClose() {}

    EntitiesManager entitiesManager;
    Spawner spawner;

    SpriteRendererSystem sr;
    InputSystem is;
    MovementSystem ms;
    TransformSystem ts;
    CollisionSystem cs;
}
