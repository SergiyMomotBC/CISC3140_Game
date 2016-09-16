package edu.ci.scenes;

import edu.ci.engine.Renderer;

public interface IGameScene
{
    public void onStart();

    public default void onPause() {};

    public void onUpdate(double deltaTime);

    public void onRender(Renderer r);

    public default void onResume() {};

    public void onClose();
}
