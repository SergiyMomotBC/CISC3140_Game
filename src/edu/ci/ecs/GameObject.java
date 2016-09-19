package edu.ci.ecs;

import java.util.ArrayList;

public final class GameObject
{
    public GameObject(GameObjectType type)
    {
        this.type = type;
        this.components = new ArrayList<>();
        this.isAlive = true;
    }

    public void addComponent(Component component)
    {
        for(Component c : components)
            if(c.equals(component))
                return;

        components.add(component);
    }

    public <T extends Component> void removeComponent(Class<T> componentClass)
    {
        for(int i = 0; i < components.size(); i++)
            if(componentClass.isInstance(components.get(i))) {
                components.remove(i);
                return;
            }
    }

    public <T extends Component> T getComponent(Class<T> componentClass)
    {
        for(Component c : components)
            if(componentClass.isInstance(c))
                return (T)c;

        return null;
    }

    public GameObjectType getType()
    {
        return type;
    }

    public void destroy()
    {
        isAlive = false;
    }

    public boolean shouldBeDestroyed()
    {
        return !isAlive;
    }

    private ArrayList<Component>    components;
    private GameObjectType          type;
    private boolean                 isAlive;
}
