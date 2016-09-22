package edu.ci;

import edu.ci.engine.Engine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Delegate of the Game class which performs setup, initialization and termination of the game.
 */
public final class GameDelegate
{
    /**
     * Creates a delegate for a specified game object.
     *
     * @param game  Game object it controls
     */
    public GameDelegate(Game game)
    {
        this.game = game;
    }

    /**
     * Starts the engine.
     *
     * @param window    Main window of the game
     */
    public void initialize(JFrame window)
    {
        Engine.start(window, "assets/");
    }

    public void deinitialize() {}

    /**
     * Processes command-line arguments.
     *
     * @param arguments     An array of command-line arguments
     */
    public void processArguments(String[] arguments)
    {}

    /**
     * Setups given window.
     *
     * @param window    Window to setup
     */
    public void setupWindow(JFrame window)
    {
        //prepare for fullscreen mode
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIgnoreRepaint(true);
        window.setResizable(false);
        window.setUndecorated(true);

        //add listener for handling closing of the window
        window.addWindowListener( new WindowAdapter()
        {
            public void windowClosing( WindowEvent e ) {
                game.terminate();
            }
        });

        //enable fullcreen mode
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(window);

        //create an empty mouse cursor image to hide the cursor
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "");
        window.setCursor(blankCursor);

        //if window looses focus, then regain it
        JFrame finalWindow = window;
        window.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {}

            @Override
            public void focusLost(FocusEvent focusEvent) {
                finalWindow.requestFocus();
            }
        });

        //present the window to the user
        window.setVisible(true);
    }

    /**
     * Presents an error dialog with an error message.
     *
     * @param message   Error message to display
     */
    public void reportFatalError(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Fatal error", JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
    }

    private Game game;
}
