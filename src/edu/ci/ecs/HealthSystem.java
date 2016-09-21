package edu.ci.ecs;

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
