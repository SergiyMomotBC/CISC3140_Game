package edu.ci.ecs.systems;

import edu.ci.ecs.GameObject;
import edu.ci.ecs.Spawner;
import edu.ci.ecs.components.TransformComponent;
import edu.ci.engine.Engine;
import java.awt.*;
import java.util.ArrayList;

public class EnemyBoardManager
{
    public final int WIDTH = 10;
    public final int HEIGHT = 4;

    public EnemyBoardManager(Spawner spawner, Point position, double moveInterval)
    {
        this.spawner = spawner;
        this.position = position;
        this.enemies = new ArrayList<>();
        this.spaceInterval = 30;

        Image sprite = Engine.getResourceManager().loadImage("enemy.png");
        this.enemySize = new Dimension(sprite.getWidth(null), sprite.getHeight(null));

        this.boardRect = new Rectangle(position, new Dimension(
                enemySize.width * WIDTH + (WIDTH - 1) * spaceInterval,
                enemySize.height * HEIGHT + (HEIGHT - 1) * spaceInterval
        ));

        respawn(moveInterval);
    }

    public void respawn(double moveInterval)
    {
        this.moveInterval = moveInterval;
        this.timer = 0.0;
        this.direction = 1;

        for(int i = 0; i < WIDTH; i++)
            for(int j = 0; j < HEIGHT; j++)
                enemies.add(spawner.spawnEnemy(new Point(position.x + i * (spaceInterval + enemySize.width),
                                position.y + j * (spaceInterval + enemySize.height)),
                                   Engine.getResourceManager().loadImage("enemy.png")
                        )
                );
    }

    public void update(double dt)
    {
        if(enemies.isEmpty()) {
            respawn(moveInterval * 0.9);
            return;
        }

        timer += dt;

        if(timer >= moveInterval)
        {
            if((direction < 0 && boardRect.getX() < 50) ||
               (direction > 0 && boardRect.getX() + boardRect.getWidth() >= 1870))
            {
                for (int i = 0; i < enemies.size(); i++) {
                    TransformComponent tc = enemies.get(i).getComponent(TransformComponent.class);

                    if (tc != null)
                        tc.moveBy(0, 50);
                }

                direction *= -1;
            }
            else
            {
                for(int i = 0; i < enemies.size(); i++) {
                    TransformComponent tc = enemies.get(i).getComponent(TransformComponent.class);

                    if(tc != null)
                        tc.moveBy(direction * 50, 0);
                }

                boardRect.setLocation((int)(boardRect.getX() + direction * 50), (int) boardRect.getY());
            }

            timer = 0.0;
        }

        for(int i = 0; i < enemies.size(); i++)
            if(enemies.get(i).shouldBeDestroyed())
                enemies.remove(i);
    }

    private Dimension               enemySize;
    private Rectangle               boardRect;
    private int                     spaceInterval;
    private short                   direction;
    private double                  timer;
    private double                  moveInterval;
    private Point                   position;
    private ArrayList<GameObject>   enemies;
    private Spawner                 spawner;
}
