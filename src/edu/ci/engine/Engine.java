package edu.ci.engine;

import javax.swing.JFrame;
import java.awt.Dimension;

public final class Engine
{
    public static void start(JFrame window, String assetsRootFolder)
    {
        inputHandler = new InputHandler(window);
        renderer = new Renderer(window, new Dimension(1920, 1080));
        resourceManager = new ResourceManager(assetsRootFolder);
        audioPlayer = new AudioPlayer();
    }

    public static InputHandler getInputHandler()
    {
        return inputHandler;
    }
    public static Renderer getRenderer() { return renderer; }
    public static ResourceManager getResourceManager() { return resourceManager; }
    public static AudioPlayer getAudioPlayer() { return audioPlayer; }

    private static InputHandler         inputHandler;
    private static Renderer             renderer;
    private static ResourceManager      resourceManager;
    private static AudioPlayer          audioPlayer;
}
