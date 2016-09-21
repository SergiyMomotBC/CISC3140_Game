package edu.ci.ecs;

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
