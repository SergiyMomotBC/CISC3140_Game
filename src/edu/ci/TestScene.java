package edu.ci;

import edu.ci.ecs.*;
import edu.ci.ecs.systems.*;
import edu.ci.engine.Engine;
import edu.ci.engine.Renderer;
import edu.ci.scenes.IGameScene;
import edu.ci.scenes.PauseScene;
import java.awt.*;
import java.awt.event.KeyEvent;
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

        justStarted = true;
        timer = 0.0;
        preparationImage = Engine.getResourceManager().loadImage("text_ready.png");
    }

    @Override
    public void onUpdate(double deltaTime)
    {
        if(justStarted) {
            timer += deltaTime;

            if( timer >= 1.5 )
                preparationImage = Engine.getResourceManager().loadImage("text_go.png");

            if( timer >= 3.0)
                justStarted = false;

            ArrayList<GameObject> objects = entitiesManager.getEntities();
            for(int i = 0; i < objects.size(); i++)
                sr.update(deltaTime, objects.get(i));

            return;
        }

        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_SPACE))
            Game.getSceneManager().pushScene(new PauseScene(Engine.getResourceManager().loadImage("gameplay_background.png")));

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
    public void onRender(Renderer r)
    {
        if(justStarted) {
            r.renderTintedBackground();
            r.renderSprite(preparationImage, new Point(timer < 1.5 ? 850 : 900, 500));
        }
    }

    @Override
    public void onClose() {}

    private boolean     justStarted;
    private double      timer;
    private Image       preparationImage;

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
