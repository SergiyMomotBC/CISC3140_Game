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

        sr = new SpriteRendererSystem();
        is = new InputSystem(spawner);
        ms = new MovementSystem();
        ts = new TransformSystem();
        cs = new CollisionSystem(entitiesManager.getEntities());
        hs = new HealthSystem();
        huds = new HUDSystem();

        ebm = new EnemyBoardManager(spawner, new Point(300, 100), 1.5);
    }

    @Override
    public void onUpdate(double deltaTime)
    {
        entitiesManager.update();

        ebm.update(deltaTime);

        ArrayList<GameObject> objects = entitiesManager.getEntities();
        for(int i = 0; i < objects.size(); i++) {
            is.update(deltaTime, objects.get(i));
            ms.update(deltaTime, objects.get(i));
            ts.update(deltaTime, objects.get(i));
            cs.update(deltaTime, objects.get(i));
            sr.update(deltaTime, objects.get(i));
            hs.update(deltaTime, objects.get(i));
            huds.update(deltaTime, objects.get(i));
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
    HealthSystem hs;
    HUDSystem huds;
    MovementSystem ms;
    TransformSystem ts;
    CollisionSystem cs;
    EnemyBoardManager ebm;
}
