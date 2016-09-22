package edu.ci.ecs;

import edu.ci.ecs.components.TransformComponent;
import edu.ci.engine.Engine;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyBoardManager
{
    private final int WIDTH = 10;
    private final int HEIGHT = 4;
    private final int SPEED = 50;
    private final int SPACE_INTERVAL = 30;

    public EnemyBoardManager(Spawner spawner, Point position, double moveInterval)
    {
        this.spawner = spawner;
        this.position = position;
        this.enemies = new ArrayList<>();

        Image sprite = Engine.getResourceManager().loadImage("enemy.png");
        this.enemySize = new Dimension(sprite.getWidth(null), sprite.getHeight(null));

        this.boardRect = new Rectangle(position, new Dimension(
                enemySize.width * WIDTH + (WIDTH - 1) * SPACE_INTERVAL,
                enemySize.height * HEIGHT + (HEIGHT - 1) * SPACE_INTERVAL
        ));

        respawn(moveInterval);
    }

    public void respawn(double moveInterval)
    {
        this.moveInterval = moveInterval;
        this.timer = 0.0;
        this.direction = 1;
        this.shootTimer = 0.0;

        for(int i = 0; i < WIDTH; i++)
            for(int j = 0; j < HEIGHT; j++)
                enemies.add(spawner.spawnEnemy(
                        new Point(position.x + i * (SPACE_INTERVAL + enemySize.width),
                                position.y + j * (SPACE_INTERVAL + enemySize.height)),
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
        shootTimer += dt;

        if(timer >= moveInterval)
        {
            if((direction < 0 && boardRect.getX() < 70) ||
               (direction > 0 && boardRect.getX() + boardRect.getWidth() >= 1870))
            {
                for (int i = 0; i < enemies.size(); i++) {
                    TransformComponent tc = enemies.get(i).getComponent(TransformComponent.class);

                    if (tc != null)
                        tc.moveBy(0, SPEED);
                }

                direction *= -1;
            }
            else
            {
                for(int i = 0; i < enemies.size(); i++) {
                    TransformComponent tc = enemies.get(i).getComponent(TransformComponent.class);

                    if(tc != null)
                        tc.moveBy(direction * SPEED, 0);
                }

                boardRect.setLocation((int)(boardRect.getX() + direction * SPEED), (int) boardRect.getY());
            }

            timer = 0.0;
        }
        else if(shootTimer > 1.0)
        {
            Random r = new Random();
            int enemyID = r.nextInt(enemies.size());
            spawner.spawnBullet(enemies.get(enemyID), new Point(0, 600));
            shootTimer = 0.0;
        }

        for(int i = 0; i < enemies.size(); i++)
            if(enemies.get(i).shouldBeDestroyed())
                enemies.remove(i);
    }

    private Dimension               enemySize;
    private Rectangle               boardRect;
    private short                   direction;
    private double                  timer;
    private double                  shootTimer;
    private double                  moveInterval;
    private Point                   position;
    private ArrayList<GameObject>   enemies;
    private Spawner                 spawner;
}
