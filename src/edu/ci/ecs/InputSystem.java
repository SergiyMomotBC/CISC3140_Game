package edu.ci.ecs;

import edu.ci.engine.Engine;
import edu.ci.engine.InputHandler;
import java.awt.*;
import java.awt.event.KeyEvent;

public class InputSystem implements System
{
    @Override
    public void update(double dt, GameObject object)
    {
        TransformComponent tc = object.getComponent(TransformComponent.class);
        MovementComponent mc = object.getComponent(MovementComponent.class);
        SpriteComponent sc = object.getComponent(SpriteComponent.class);

        if(tc != null && mc != null){
            InputHandler input = Engine.getInputHandler();

            if(input.isKeyPressedOnce(KeyEvent.VK_RIGHT)) {
                mc.changeVelocity(new Point(400, 0));
                sc.changeSprite(Engine.getResourceManager().loadImage("playerRight.png"));
            }

            if(input.isKeyPressedOnce(KeyEvent.VK_LEFT)) {
                mc.changeVelocity(new Point(-400, 0));
                sc.changeSprite(Engine.getResourceManager().loadImage("playerLeft.png"));
            }

            if(input.isKeyReleased(KeyEvent.VK_LEFT) || input.isKeyReleased(KeyEvent.VK_RIGHT)) {
                mc.changeVelocity(new Point(0, 0));
                sc.changeSprite(Engine.getResourceManager().loadImage("player.png"));
            }
        }

    }
}
