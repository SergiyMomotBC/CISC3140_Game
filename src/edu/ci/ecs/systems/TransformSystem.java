package edu.ci.ecs.systems;

import edu.ci.ecs.GameObject;
import edu.ci.ecs.components.SpriteComponent;
import edu.ci.ecs.components.TransformComponent;

public class TransformSystem implements System
{
    @Override
    public void update(double dt, GameObject object)
    {
        TransformComponent tc = object.getComponent(TransformComponent.class);
        SpriteComponent sc = object.getComponent(SpriteComponent.class);

        if(sc != null && tc != null)
        {
            switch(object.getType())
            {
                case Player:
                    if(tc.getPosition().x < 0)
                        tc.getPosition().x = 0;
                    if(tc.getPosition().x + sc.getImageSize().width > 1920)
                        tc.getPosition().x = 1920 - sc.getImageSize().width;
                    break;

                case Bullet:
                    if(tc.getPosition().y < 0)
                        object.destroy();
            }
        }
    }
}
