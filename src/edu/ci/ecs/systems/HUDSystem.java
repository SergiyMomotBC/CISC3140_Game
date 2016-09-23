package edu.ci.ecs.systems;

import edu.ci.ecs.GameObject;
import edu.ci.ecs.GameObjectType;
import edu.ci.ecs.components.HealthComponent;
import edu.ci.ecs.components.ScoreComponent;
import edu.ci.engine.Engine;
import java.awt.*;

public class HUDSystem implements System
{
    @Override
    public void update(double dt, GameObject object)
    {
        if(object.getType() != GameObjectType.Player)
            return;

        HealthComponent hc = object.getComponent(HealthComponent.class);

        if(hc != null)
            renderLivesLeft(hc);

        ScoreComponent sc = object.getComponent(ScoreComponent.class);

        if(sc != null)
        {

        }
    }

    private void renderLivesLeft(HealthComponent hc)
    {
        Image life = Engine.getResourceManager().loadImage("player_life_ui.png");
        Image x = Engine.getResourceManager().loadImage("x_ui.png");
        String name = "numeral" + String.valueOf(hc.getHp()) + ".png";
        Image number = Engine.getResourceManager().loadImage(name);

        Engine.getRenderer().renderSprite(
                life,
                new Point(30, 20),
                new Dimension(life.getWidth(null), life.getHeight(null))
        );

        Engine.getRenderer().renderSprite(
                x,
                new Point(75, 24),
                new Dimension(x.getWidth(null), x.getHeight(null))
        );

        Engine.getRenderer().renderSprite(
                number,
                new Point(105, 23),
                new Dimension(number.getWidth(null), number.getHeight(null))
        );
    }
}
