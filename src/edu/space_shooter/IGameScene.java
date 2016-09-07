package edu.space_shooter;

public interface IGameScene
{
    public void onStart();

    public default void onPause() {};

    public void onUpdate(double deltaTime);

    public void onRender();

    public default void onResume() {};

    public void onClose();
}
