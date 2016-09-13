package edu.ci;

import edu.ci.engine.Engine;

import java.awt.event.KeyEvent;
import java.util.Stack;

public final class SceneManager
{
    public SceneManager(IGameScene initialScene)
    {
        scenes = new Stack<IGameScene>();
        if(initialScene != null) {
            currentScene = initialScene;
            currentScene.onStart();
        }
    }

    public void changeToScene(IGameScene newScene)
    {
        if(newScene != null) {
            currentScene.onClose();
            currentScene = newScene;
            currentScene.onStart();
        }
    }

    public void runFrame(double deltaTime)
    {
        Engine.getInputHandler().pollKeyboard();

        if(Engine.getInputHandler().isKeyPressedOnce(KeyEvent.VK_ESCAPE))
            System.exit(0);

        Engine.getRenderer().prepareFrame();
        if(currentScene != null) {
            currentScene.onUpdate(deltaTime);
            currentScene.onRender(Engine.getRenderer());
        }
        Engine.getRenderer().presentFrame();
    }

    public void pushScene(IGameScene newScene)
    {
        if(newScene != null) {
            scenes.push(currentScene);
            currentScene.onPause();
            currentScene = newScene;
            currentScene.onStart();
        }
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