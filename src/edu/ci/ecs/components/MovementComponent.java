package edu.ci.ecs.components;

import java.awt.*;

public class MovementComponent implements Component
{
    public MovementComponent(Point velocity)
    {
        this.velocity = velocity;
    }

    public Point getVelocity() { return velocity; }
    public void changeVelocity(Point newVelocity) { velocity = newVelocity; }

    private Point velocity;
}
