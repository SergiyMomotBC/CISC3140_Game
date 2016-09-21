package edu.ci.ecs.systems;

import edu.ci.ecs.GameObject;

import java.util.ArrayList;

public interface System
{
    public void update(double dt, GameObject object);
}
