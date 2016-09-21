package edu.ci.ecs;

import edu.ci.ecs.components.MovementComponent;
import edu.ci.ecs.components.PhysicsComponent;
import edu.ci.ecs.components.SpriteComponent;
import edu.ci.ecs.components.TransformComponent;
import edu.ci.engine.Engine;

import java.awt.*;

public class Spawner
{
    public Spawner(EntitiesManager manager)
    {
        this.manager = manager;
    }

    public void spawnPlayer(Point position, Image sprite)
    {
        GameObject player = new GameObject(GameObjectType.Player);

        player.addComponent(new TransformComponent(position, 1.0, 0.0));
        player.addComponent(new SpriteComponent(sprite));
        player.addComponent(new MovementComponent(new Point(0, 0)));

        manager.addEntity(player);
    }

    public void spawnBullet(GameObject spawner, Point velocity)
    {
        GameObject bullet = new GameObject(GameObjectType.Bullet);

        TransformComponent tc = spawner.getComponent(TransformComponent.class);
        SpriteComponent sc = spawner.getComponent(SpriteComponent.class);

        if(tc == null || sc == null)
            return;

        SpriteComponent scb = new SpriteComponent(Engine.getResourceManager().loadImage("bullet.png"));

        TransformComponent tcb = new TransformComponent(
                new Point(tc.getPosition().x + sc.getImageSize().width / 2 - scb.getImageSize().width/2,
                        tc.getPosition().y - scb.getImageSize().height),
                1.0, 0.0);

        bullet.addComponent(scb);
        bullet.addComponent(new MovementComponent(velocity));
        bullet.addComponent(tcb);

        manager.addEntity(bullet);
    }

    public void spawnBackground(Image background)
    {
        GameObject bg = new GameObject(GameObjectType.Enemy);

        bg.addComponent(new TransformComponent(new Point(0, 0), 1.0, 0.0));
        bg.addComponent(new SpriteComponent(background));

        manager.addEntity(bg);
    }

    public void spawnEnemy(Point position, Image sprite)
    {
        GameObject enemy = new GameObject(GameObjectType.Enemy);

        enemy.addComponent(new TransformComponent(position, 1.0, 0.0));
        enemy.addComponent(new SpriteComponent(sprite));
        enemy.addComponent(new PhysicsComponent(new CollisionRespondAction() {
            @Override
            public void respondToCollisionWith(GameObject entity) {
                if(entity.getType() == GameObjectType.Bullet) {
                    enemy.destroy();
                    entity.destroy();
                }
            }
        }));

        manager.addEntity(enemy);
    }

    private EntitiesManager         manager;
}
