package edu.ci.ecs.components;

import edu.ci.ecs.CollisionRespondAction;
import edu.ci.ecs.GameObject;

public class PhysicsComponent implements Component
{
    public PhysicsComponent(CollisionRespondAction respondAction)
    {
        this.respondAction = respondAction;
    }

    public void respondToCollisionWith(GameObject entity)
    {
        respondAction.respondToCollisionWith(entity);
    }

    private CollisionRespondAction respondAction;
}
