package edu.ci.ecs;

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
                            (int)(sc.getImageSize().width * tc.getScale()),
                            (int)(sc.getImageSize().height * tc.getScale())
                    )
            );
    }
}
