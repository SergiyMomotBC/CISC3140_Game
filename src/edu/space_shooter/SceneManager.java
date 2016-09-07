package edu.space_shooter;

import java.util.Stack;

public final class SceneManager
{
    public SceneManager(IGameScene initialScene)
    {
        scenes = new Stack<IGameScene>();
        currentScene = initialScene;
        currentScene.onStart();
    }

    public void changeToScene(IGameScene newScene)
    {
        currentScene.onClose();
        currentScene = newScene;
        currentScene.onStart();
    }

    public void runFrame(double deltaTime)
    {
        //input update
        currentScene.onUpdate(deltaTime);
        currentScene.onRender();
    }

    public void pushScene(IGameScene newScene)
    {
        scenes.push(currentScene);
        currentScene.onPause();
        currentScene = newScene;
        currentScene.onStart();
    }

    public void popScene()
    {
        if(!scenes.isEmpty()){
            currentScene.onClose();
            currentScene = scenes.pop();
            currentScene.onResume();
        }
    }

    private IGameScene currentScene;
    private Stack<IGameScene> scenes;
}