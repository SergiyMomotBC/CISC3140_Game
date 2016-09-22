package edu.ci.ecs.systems;

import edu.ci.ecs.GameObject;
import edu.ci.ecs.GameObjectType;
import edu.ci.ecs.components.HealthComponent;
import edu.ci.ecs.components.SpriteComponent;
import edu.ci.ecs.components.TransformComponent;
import edu.ci.engine.Engine;
import java.awt.*;

public class SpriteRendererSystem implements System
{
    @Override
    public void update(double dt, GameObject object)
    {
        SpriteComponent sc = object.getComponent(SpriteComponent.class);
        TransformComponent tc = object.getComponent(TransformComponent.class);

        if(sc != null && tc != null)
            Engine.getRenderer().renderSprite(
                    sc.getSpriteImage(),
                    tc.getPosition(),
                    new Dimension(
                            (int) (sc.getImageSize().width * tc.getScale()),
                            (int) (sc.getImageSize().height * tc.getScale())
                    )
            );
    }
}
