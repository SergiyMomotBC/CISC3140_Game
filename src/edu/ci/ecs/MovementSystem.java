package edu.ci.ecs;

public class MovementSystem implements System
{
    @Override
    public void update(double dt, GameObject object)
    {
        TransformComponent tc = object.getComponent(TransformComponent.class);
        MovementComponent mc = object.getComponent(MovementComponent.class);

        if(tc != null && mc != null)
            tc.moveBy((int) (mc.getVelocity().x * dt), (int) (mc.getVelocity().y * dt));
    }
}
