package edu.ci.ecs;

import java.util.ArrayList;

public class EntitiesManager
{
    public EntitiesManager() {}

    public void addEntity(GameObject entity)
    {
        entities.add(entity);
    }

    public void removeEntity(GameObject entity)
    {
        entities.remove(entity);
    }

    private ArrayList<GameObject> entities;
}
