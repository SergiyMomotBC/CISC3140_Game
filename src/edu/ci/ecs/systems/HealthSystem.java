package edu.ci.ecs.systems;

import edu.ci.ecs.GameObject;
import edu.ci.ecs.components.HealthComponent;

public class HealthSystem implements System
{
    @Override
    public void update(double dt, GameObject object)
    {
        HealthComponent hc = object.getComponent(HealthComponent.class);

        if(hc != null)
            if(hc.getHp() <= 0)
                object.destroy();
    }
}
