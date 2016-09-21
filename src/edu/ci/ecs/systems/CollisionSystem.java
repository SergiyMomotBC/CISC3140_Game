package edu.ci.ecs.systems;

import edu.ci.ecs.*;
import edu.ci.ecs.components.PhysicsComponent;
import edu.ci.ecs.components.SpriteComponent;
import edu.ci.ecs.components.TransformComponent;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class CollisionSystem implements System
{
    public CollisionSystem(ArrayList<GameObject> objects)
    {
        this.objects = objects;
    }

    @Override
    public void update(double dt, GameObject object)
    {
        if(object.getType() != GameObjectType.Player && object.getType() != GameObjectType.Enemy)
            return;

        TransformComponent tc = object.getComponent(TransformComponent.class);
        SpriteComponent sc = object.getComponent(SpriteComponent.class);
        PhysicsComponent pc = object.getComponent(PhysicsComponent.class);

        if(tc != null && sc != null && pc != null)
        {
            Rectangle2D aabb = new Rectangle(tc.getPosition(), sc.getImageSize());

            for(int i = 0; i < objects.size(); i++)
            {
                if(object.getType() == objects.get(i).getType())
                    continue;;

                TransformComponent otc = objects.get(i).getComponent(TransformComponent.class);
                SpriteComponent osc = objects.get(i).getComponent(SpriteComponent.class);

                if(otc != null && osc != null)
                {
                    Rectangle2D oaabb = new Rectangle(otc.getPosition(), osc.getImageSize());

                    if(aabb.intersects(oaabb))
                        pc.respondToCollisionWith(objects.get(i));
                }
            }
        }
    }

    private ArrayList<GameObject> objects;
}
