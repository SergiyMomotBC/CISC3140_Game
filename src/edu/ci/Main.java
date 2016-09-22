package edu.ci;

import javax.swing.SwingUtilities;

/**
 * Class which has an entry point method main().
 */
public final class Main
{
    /**
     * Starts the execution of the game application by creating a game instance.
     *
     * @param args  An array of command-line arguments
     */
    public static void main(String[] args)
    {
        //to run the game after Swing libraries have been loaded
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Game(args);
            }
        });
    }
}
