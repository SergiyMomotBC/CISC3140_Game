package edu.ci.ecs;

import edu.ci.Game;

import java.util.ArrayList;

public class EntitiesManager
{
    public EntitiesManager()
    {
        entities = new ArrayList<>();
    }

    public void update()
    {
        for(int i = 0; i < entities.size(); i++)
            if(entities.get(i).shouldBeDestroyed())
                entities.remove(i);
    }

    public ArrayList<GameObject> getAllObjectsOfType(GameObjectType type)
    {
        ArrayList<GameObject> filter = new ArrayList<>();

        for(GameObject go : entities)
            if(go.getType() == type)
                filter.add(go);

        return filter;
    }

    public ArrayList<GameObject> getEntities()
    {
        return entities;
    }

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
