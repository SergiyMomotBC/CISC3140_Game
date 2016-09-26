package edu.ci.ecs.systems;

import edu.ci.Game;
import edu.ci.ecs.GameObject;
import edu.ci.ecs.GameObjectType;
import edu.ci.ecs.components.HealthComponent;
import edu.ci.ecs.components.ScoreComponent;
import edu.ci.scenes.GameOverScene;

public class HealthSystem implements System
{
    @Override
    public void update(double dt, GameObject object)
    {
        HealthComponent hc = object.getComponent(HealthComponent.class);

        if(hc != null)
            if(hc.getHp() <= 0) {
                if(object.getType() == GameObjectType.Player) {
                    ScoreComponent sc = object.getComponent(ScoreComponent.class);
                    if(sc != null)
                        Game.getSceneManager().changeToScene(new GameOverScene((int)sc.getScore()));
                }
            }
    }
}
