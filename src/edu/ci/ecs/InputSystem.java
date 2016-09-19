package edu.ci.ecs;

import edu.ci.engine.Engine;
import edu.ci.engine.InputHandler;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InputSystem implements System
{
    private final ArrayList<GameObject> objects;

    public InputSystem(ArrayList<GameObject> objects){
        this.objects = objects;
    }

    @Override
    public void update(double dt, GameObject object)
    {
        TransformComponent tc = object.getComponent(TransformComponent.class);
        MovementComponent mc = object.getComponent(MovementComponent.class);
        SpriteComponent sc = object.getComponent(SpriteComponent.class);

        if(tc != null && mc != null && object.getType() == GameObjectType.Player)
        {
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

            if(input.isKeyPressedOnce(KeyEvent.VK_UP)){
                GameObject bullet = new GameObject(GameObjectType.Enemy);
                SpriteComponent scb = new SpriteComponent(Engine.getResourceManager().loadImage("bullet.png"));
                MovementComponent mcb = new MovementComponent(new Point(0, -500));
                TransformComponent tcb = new TransformComponent(
                        new Point(tc.getPosition().x + sc.getImageSize().width / 2 - scb.getImageSize().width/2,
                                tc.getPosition().y - scb.getImageSize().height),
                        1.0, 0.0);
                bullet.addComponent(scb);
                bullet.addComponent(tcb);
                bullet.addComponent(mcb);

                objects.add(bullet);
            }
        }
    }
}
