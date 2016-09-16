package edu.ci.ecs;

import java.awt.*;

public class TransformComponent implements Component
{
    public TransformComponent(Point position, double scale, double rotation)
    {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

    public void moveTo(Point newPosition) { position = newPosition; }
    public void moveBy(int dx, int dy) { position.x += dx; position.y += dy; }
    public void scaleTo(double factor) { scale = factor; }
    public void scaleBy(double df) { scale += df; }
    public void rotateTo(double newAngle) { rotation = newAngle; }
    public void rotateBy(double da) { rotation += da; }
    public Point getPosition() { return position; }
    public double getScale() { return scale; }
    public double getRotation() { return rotation; }

    private Point       position;
    private double      scale;
    private double      rotation;
}
