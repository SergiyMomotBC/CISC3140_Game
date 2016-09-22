package edu.ci;

import edu.ci.scenes.IGameScene;
import javax.swing.*;

/**
 * Central class of the game which controls the flow of the game.
 */
public final class Game implements Runnable
{
    public final static int TARGET_FPS = 60;

    /**
     * Presents a new scene to the user.
     *
     * @param newScene      Scene to be presented
     * @param pauseCurrent  To save the current scene for later restoring
     */
    public static void presentScene(IGameScene newScene, boolean pauseCurrent)
    {
        if(pauseCurrent)
            uniqueInstance.sceneManager.pushScene(newScene);
        else
            uniqueInstance.sceneManager.changeToScene(newScene);
    }

    /**
     * Game's constructor which initializes necessary subsystems and components.
     *
     * @param args  An array of command-line arguments
     */
    public Game(String[] args)
    {
        //only one instance of Game class can exist
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

        //create and start game's main thread
        mainThread = new Thread(this);
        mainThread.start();
    }

    /**
     * Runs the game loop.
     */
    @Override
    public void run()
    {
        isRunning = true;

        long currentTime, previousTime = System.nanoTime();
        double accumTime = 0;

        while(isRunning)
        {
            //calculate time delta and add it to accumulative time
            currentTime = System.nanoTime();
            accumTime += (currentTime - previousTime) / 1.0e9;
            previousTime = currentTime;

            //run frames with a fixed framerate
            while(accumTime > 1.0/TARGET_FPS) {
                sceneManager.runFrame(1.0/TARGET_FPS);
                accumTime -= 1.0/TARGET_FPS;
            }
        }
    }

    /**
     * Stops the execution of the game.
     */
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
