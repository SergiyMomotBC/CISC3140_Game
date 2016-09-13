package edu.ci;

import edu.ci.engine.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public final class GameDelegate
{
    public GameDelegate(Game game)
    {
        this.game = game;
    }

    public void initialize(JFrame window)
    {
        Engine.start(window, "assets/");
    }

    public void deinitialize() {}

    public void processArguments(String[] arguments) {}

    public void setupWindow(JFrame window)
    {
        //prepare for fullscreen mode
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIgnoreRepaint(true);
        window.setResizable(false);
        window.setUndecorated(true);

        window.addWindowListener( new WindowAdapter()
        {
            public void windowClosing( WindowEvent e ) {
                onWindowClosing();
            }
        });

        //enable fullcreen mode
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(window);

        //create an empty mouse cursor image to hide cursor
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

        window.setVisible(true);
    }

    public void reportFatalError(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Fatal error", JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
    }

    public void onWindowClosing()
    {
        game.terminate();
    }

    private Game game;
}
