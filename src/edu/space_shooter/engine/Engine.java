package edu.space_shooter.engine;

import javax.swing.JFrame;
import java.awt.*;

public class Engine
{
    public static void start(JFrame window, String assetsRootFolder)
    {
        inputHandler = new InputHandler(window);
        renderer = new Renderer(window, new Dimension(1920, 1080));
    }

    public static InputHandler getInputHandler()
    {
        return inputHandler;
    }
    public static Renderer getRenderer() { return renderer; }

    private static InputHandler inputHandler;
    private static Renderer     renderer;
}
