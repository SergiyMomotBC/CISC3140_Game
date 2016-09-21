package edu.ci;

import edu.ci.scenes.IGameScene;
import edu.ci.scenes.MainMenuScene;

import javax.swing.*;

public final class Game implements Runnable
{
    public final static int TARGET_FPS = 60;

    public static void presentScene(IGameScene newScene, boolean pauseCurrent)
    {
        if(pauseCurrent)
            uniqueInstance.sceneManager.pushScene(newScene);
        else
            uniqueInstance.sceneManager.changeToScene(newScene);
    }

    public Game(String[] args)
    {
        if(wasInstantiated)
            throw new RuntimeException("Only one instance of Game class can be created...");

        uniqueInstance = this;
        wasInstantiated = true;

        delegate = new GameDelegate(this);

        delegate.processArguments(args);

        mainWindow = new JFrame();
        delegate.setupWindow(mainWindow);

        delegate.initialize(mainWindow);

        sceneManager = new SceneManager(new TestScene());

        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run()
    {
        isRunning = true;

        long currentTime, previousTime = System.nanoTime();
        double dt;

        while(isRunning)
        {
            currentTime = System.nanoTime();
            dt = (currentTime - previousTime) / 1.0e9;
            previousTime = currentTime;

            sceneManager.runFrame(dt);


            if(dt < 1.0/TARGET_FPS)
                try {
                    Thread.sleep((long)((1.0 / TARGET_FPS - dt) * 1000));
                } catch (InterruptedException e) {
                    delegate.reportFatalError("Thread execution error...");
                }
        }
    }

    public void terminate()
    {
        try {
            isRunning = false;
            mainThread.join();
            delegate.deinitialize();
            System.exit(0);
        } catch (InterruptedException e) {
            delegate.reportFatalError("Thread execution error...");
        }
    }

    private GameDelegate        delegate;
    private Thread              mainThread;
    private JFrame              mainWindow;
    private SceneManager        sceneManager;
    private boolean             isRunning;

    private static Game         uniqueInstance;
    private static boolean      wasInstantiated = false;
}
